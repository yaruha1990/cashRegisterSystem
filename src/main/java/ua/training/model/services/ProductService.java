package ua.training.model.services;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.dao.impl.ProductDaoImpl;
import ua.training.model.entity.Product;

import java.sql.Connection;

public class ProductService {

    private ProductDao productDao;

    public ProductService(){
        productDao = DaoFactory.getInstance().getProductDao();
    }

    /**
     * Check does product's quantity in stock is sufficient for sale
     * @param vendorCode
     * @param quantity
     * @return true if quantity in stock is enough and false if not
     */
    public boolean isProductAvailableByQuantity(String vendorCode, int quantity){
        Product product = productDao.findProductByVendorCode(vendorCode);
        if (product.getQuantityInStock() >= quantity){
            return true;
        }
        return false;
    }

    /**
     * Check does product's vendor code is correct
     * @param vendorCode
     * @return true if vendor code is correct and false if not
     */
    public boolean isProductAvailableByVendorCode(String vendorCode){
        Product product = productDao.findProductByVendorCode(vendorCode);
        if (product != null){
            return true;
        }
        return false;
    }

    /**
     * Get product quantity in stock by vendor code
     * @param vendorCode
     * @return
     */
    public int getProductQuantityInStock(String vendorCode){
        Product product = productDao.findProductByVendorCode(vendorCode);
        return product.getQuantityInStock();
    }

}
