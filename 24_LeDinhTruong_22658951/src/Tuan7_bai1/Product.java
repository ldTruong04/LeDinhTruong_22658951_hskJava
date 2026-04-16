package Tuan7_bai1;

public class Product {
    private String productID;
    private String name;
    private String manufacture;
    private String description;
    private Supplier supplier;
    private double price;

    public Product(String productID, String name, String manufacture,
                   String description, Supplier supplier, double price) {
        this.productID = productID;
        this.name = name;
        this.manufacture = manufacture;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
    }

    public Product(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getDescription() {
        return description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
