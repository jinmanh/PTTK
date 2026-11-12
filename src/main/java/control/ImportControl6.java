package control;

import dao.ImportInvoiceDAO6;
import dao.ItemDAO6;
import dao.SupplierDAO6;
import java.util.List;
import model.ImportInvoice6;
import model.Item6;
import model.Supplier6;

public class ImportControl6 {
    private SupplierDAO6 supplierDAO;
    private ItemDAO6 itemDAO;
    private ImportInvoiceDAO6 importInvoiceDAO;

    public ImportControl6() {
        this.supplierDAO = new SupplierDAO6();
        this.itemDAO = new ItemDAO6();
        this.importInvoiceDAO = new ImportInvoiceDAO6();
    }

    public List<Supplier6> searchSupplier(String keyword) {
        return supplierDAO.getSupplierByKeyword(keyword != null ? keyword : "");
    }

    public List<Item6> searchItem(String keyword) {
        return itemDAO.getItemByKeyword(keyword != null ? keyword : "");
    }

    public boolean addSupplier(Supplier6 supplier) {
        if (supplier == null || isBlank(supplier.getSupplierCode()) || isBlank(supplier.getSupplierName())) {
            return false;
        }
        return supplierDAO.addSupplier(supplier);
    }

    public boolean addItem(Item6 item) {
        if (item == null || isBlank(item.getItemCode()) || isBlank(item.getItemName())
                || item.getPrice() < 0 || item.getStockQuantity() < 0) {
            return false;
        }
        return itemDAO.addItem(item);
    }

    public boolean saveImportInvoice(ImportInvoice6 invoice) {
        if (invoice == null || invoice.getSupplier() == null || invoice.getDetails() == null || invoice.getDetails().isEmpty()) {
            return false;
        }
        return importInvoiceDAO.saveInvoice(invoice);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
