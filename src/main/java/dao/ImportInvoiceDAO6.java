package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.ImportDetail6;
import model.ImportInvoice6;

public class ImportInvoiceDAO6 extends DAO {

    public ImportInvoiceDAO6() {
        super();
    }

    public boolean saveInvoice(ImportInvoice6 invoice) {
        boolean success = false;
        String sqlInv = "INSERT INTO tblImportInvoice6(importCode, importDate, totalValue, supplier_id, warehouse_staff_id) VALUES(?,?,?,?,?)";
        String sqlDetail = "INSERT INTO tblImportDetail6(import_invoice_id, item_id, quantity, importPrice) VALUES(?,?,?,?)";
        String sqlStock = "UPDATE tblItem6 SET stockQuantity = stockQuantity + ? WHERE id = ?";

        try {
            con.setAutoCommit(false);

            PreparedStatement psInv = con.prepareStatement(sqlInv, Statement.RETURN_GENERATED_KEYS);
            psInv.setString(1, invoice.getImportCode());
            psInv.setTimestamp(2, new java.sql.Timestamp(invoice.getImportDate().getTime()));
            psInv.setDouble(3, invoice.getTotalValue());
            psInv.setInt(4, invoice.getSupplier().getId());
            psInv.setInt(5, invoice.getStaff() != null ? invoice.getStaff().getId() : 1); // Default staff ID 1

            int affectedRows = psInv.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = psInv.getGeneratedKeys();
                if (rs.next()) {
                    int invoiceId = rs.getInt(1);
                    invoice.setId(invoiceId);

                    for (ImportDetail6 detail : invoice.getDetails()) {
                        PreparedStatement psDetail = con.prepareStatement(sqlDetail);
                        psDetail.setInt(1, invoiceId);
                        psDetail.setInt(2, detail.getItem().getId());
                        psDetail.setInt(3, detail.getQuantity());
                        psDetail.setDouble(4, detail.getImportPrice());
                        psDetail.executeUpdate();

                        // Update stock quantity directly
                        PreparedStatement psStock = con.prepareStatement(sqlStock);
                        psStock.setInt(1, detail.getQuantity());
                        psStock.setInt(2, detail.getItem().getId());
                        psStock.executeUpdate();
                    }

                    con.commit();
                    success = true;
                }
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }
}
