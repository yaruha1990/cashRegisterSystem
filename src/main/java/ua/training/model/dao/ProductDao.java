package ua.training.model.dao;

import ua.training.model.entity.Product;
import ua.training.model.entity.User;

import java.util.List;

public interface ProductDao {
    void createProduct(Product product);
    Product findProductById(int id);
    Product findProductByVendorCode(String vendorCode);
    List<Product> findAll();
    void updateProduct(Product product);
}
