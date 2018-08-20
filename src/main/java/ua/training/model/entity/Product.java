package ua.training.model.entity;

public class Product {

    private int id;
    private String vendorCode;
    private String productName;
    private double price;
    private int quantityInStock;

    public Product() {
    }

    public Product(String vendorCode, String productName, double price, int quantityInStock) {
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product(int id, String vendorCode, String productName, double price, int quantityInStock) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return vendorCode != null ? vendorCode.equals(product.vendorCode) : product.vendorCode == null;
    }

    @Override
    public int hashCode() {
        return vendorCode != null ? vendorCode.hashCode() : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", vendorCode='" + vendorCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}
