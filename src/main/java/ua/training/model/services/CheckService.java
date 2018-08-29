package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;

import java.sql.Connection;
import java.util.Map;

public class CheckService {
    final static Logger logger = Logger.getLogger(CheckService.class);

    public void addProductToCheck(Check check, Product product, int quantity){
        check.getProducts().put(product,quantity);
        logger.info("Product "+product.getVendorCode()+" has been added to check with id "+check.getId()+" in the amount of "+quantity+" pieces");
    }

    public int calculateCheckSum(Check check){
        int checkSum = 0;
        for(Map.Entry<Product, Integer> entry : check.getProducts().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            int productSum = product.getPrice()*quantity;
            checkSum+=productSum;
        }
        return checkSum;
    }

    public int getLatestId(){
       return DaoFactory.getInstance().getCheckDao().getLatestCheckId();
    }


}
