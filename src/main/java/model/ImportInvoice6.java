package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportInvoice6 implements Serializable {
    private int id;
    private String importCode;
    private Date importDate;
    private double totalValue;
    private Supplier6 supplier;
    private WarehouseStaff6 staff;
    private List<ImportDetail6> details;

    public ImportInvoice6() {
        this.details = new ArrayList<>();
        this.importDate = new Date();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getImportCode() { return importCode; }
    public void setImportCode(String importCode) { this.importCode = importCode; }
    public Date getImportDate() { return importDate; }
    public void setImportDate(Date importDate) { this.importDate = importDate; }
    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }
    public Supplier6 getSupplier() { return supplier; }
    public void setSupplier(Supplier6 supplier) { this.supplier = supplier; }
    public WarehouseStaff6 getStaff() { return staff; }
    public void setStaff(WarehouseStaff6 staff) { this.staff = staff; }
    public List<ImportDetail6> getDetails() { return details; }
    public void setDetails(List<ImportDetail6> details) { this.details = details; }
}
