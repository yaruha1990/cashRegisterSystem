package ua.training.model.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Check {
    private int id;
    private int userId;
    private String userLogin;
    private Map<Product, Integer> products;
    private int checkSum;
    private LocalDate date;
    private String dateTime;

    public Check() {
        products = new HashMap<>();
    }

    public Check(int id, int userId, Map<Product,Integer> products, int checkSum, LocalDate date, String dateTime) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.checkSum = checkSum;
        this.date = date;
        this.dateTime = dateTime;
    }

    public Check(Map<Product,Integer> products,int userId, int checkSum, LocalDate date, String dateTime) {
        this.products = products;
        this.userId = userId;
        this.checkSum = checkSum;
        this.date = date;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product,Integer> products) {
        this.products = products;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", products=" + products +
                ", checkSum=" + checkSum +
                ", date=" + date +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
