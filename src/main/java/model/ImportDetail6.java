package model;

import java.io.Serializable;

public class ImportDetail6 implements Serializable {
    private int id;
    private int quantity;
    private double importPrice;
    private Item6 item;

    public ImportDetail6() {}

    public ImportDetail6(int id, int quantity, double importPrice, Item6 item) {
        this.id = id;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.item = item;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getImportPrice() { return importPrice; }
    public void setImportPrice(double importPrice) { this.importPrice = importPrice; }
    public Item6 getItem() { return item; }
    public void setItem(Item6 item) { this.item = item; }
}
