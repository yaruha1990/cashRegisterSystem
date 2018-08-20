package ua.training.model.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Check {
    private int id;
    private int userId;
    private Map<Product, Integer> products;
    private double checkSum;
    private LocalDate date;
    private String dateTime;

    public Check() {
        products = new HashMap<>();
    }

    public Check(int id, int userId, Map<Product,Integer> products, double checkSum, LocalDate date, String dateTime) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.checkSum = checkSum;
        this.date = date;
        this.dateTime = dateTime;
    }

    public Check(Map<Product,Integer> products,int userId, double checkSum, LocalDate date, String dateTime) {
        this.products = products;
        this.userId = userId;
        this.checkSum = checkSum;
        this.date = date;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        return id == check.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(double checkSum) {
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
                ", products=" + products +
                ", checkSum=" + checkSum +
                ", date=" + date +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
