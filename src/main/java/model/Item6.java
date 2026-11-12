package model;

import java.io.Serializable;

public class Item6 implements Serializable {
    private int id;
    private String itemCode;
    private String itemName;
    private String category;
    private String specifications;
    private double price;
    private int stockQuantity;

    public Item6() {}

    public Item6(int id, String itemCode, String itemName, String category, String specifications, double price, int stockQuantity) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.specifications = specifications;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
