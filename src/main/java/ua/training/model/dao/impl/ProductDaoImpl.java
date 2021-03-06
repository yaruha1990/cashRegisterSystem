package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.model.dao.ProductDao;
import ua.training.model.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    final static Logger logger = Logger.getLogger(ProductDaoImpl.class);

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void createProduct(Product product) {
    try {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO product (vendor_code , product_name, price, quantity_in_stock ) VALUES (?, ?, ?, ?)");
            ps.setString(1 , product.getVendorCode());
            ps.setString(2 ,product.getProductName());
            ps.setInt(3,product.getPrice());
            ps.setInt(4,product.getQuantityInStock());
            ps.execute();
            connectionPool.closeConnection(connection);
            logger.info("Product with vendor code "+product.getVendorCode()+" has been created successfully");
        } catch (SQLException e) {
            logger.error("Attempt to create duplicate of product with vendor code "+product.getVendorCode());
            throw new RuntimeException("Such vendor code already exists");
        }
    }

    @Override
    public Product findProductById(int id) {
        Product product = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                product = extractFromResultSet(resultSet);
            }
            connectionPool.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private Product extractFromResultSet(ResultSet resultSet)
            throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setVendorCode(resultSet.getString("vendor_code"));
        product.setProductName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getInt("price"));
        product.setQuantityInStock(resultSet.getInt("quantity_in_stock"));
        return product;
    }

    @Override
    public Product findProductByVendorCode(String vendorCode) {
        Product product = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from product where vendor_code = ?");
            ps.setString(1,vendorCode);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                product = extractFromResultSet(resultSet);
            }
            connectionPool.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try{
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from product");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Product product = extractFromResultSet(resultSet);
                productList.add(product);
            }
            connectionPool.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void updateProduct(Product product) {
        try{
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("update product set vendor_code=?,product_name=?,price=?,quantity_in_stock=? where id=?");
            ps.setString(1,product.getVendorCode());
            ps.setString(2,product.getProductName());
            ps.setInt(3,product.getPrice());
            ps.setInt(4,product.getQuantityInStock());
            ps.setInt(5,product.getId());
            ps.executeUpdate();
            connectionPool.closeConnection(connection);
            logger.info("Product "+product.getVendorCode()+" has been updated");
        }catch (SQLException e){
            throw new RuntimeException("Such vendor code already exists");
        }
    }

}
