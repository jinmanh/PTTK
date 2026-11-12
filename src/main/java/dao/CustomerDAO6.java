package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Customer6;

public class CustomerDAO6 extends DAO {

    public CustomerDAO6() {
        super();
    }

    public boolean checkDuplicate(String username, String email) {
        if (username == null || email == null) {
            return true;
        }

        boolean isDuplicate = false;
        String sql = "SELECT COUNT(*) AS total FROM tblAccount6 WHERE username = ? OR email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username.trim());
            ps.setString(2, email.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isDuplicate = rs.getInt("total") > 0;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    public boolean saveCustomer(Customer6 cust) {
        if (cust == null) {
            return false;
        }

        boolean success = false;
        String sqlAcc = "INSERT INTO tblAccount6(username, password, fullName, dob, address, email, phone, role) VALUES(?,?,?,?,?,?,?,'CUSTOMER')";
        String sqlCust = "INSERT INTO tblCustomer6(account_id, customerCode, customerType) VALUES(?,?,?)";

        try {
            con.setAutoCommit(false);

            PreparedStatement psAcc = con.prepareStatement(sqlAcc, Statement.RETURN_GENERATED_KEYS);
            psAcc.setString(1, cust.getUsername());
            psAcc.setString(2, cust.getPassword());
            psAcc.setString(3, cust.getFullName());
            psAcc.setDate(4, cust.getDob() != null ? new java.sql.Date(cust.getDob().getTime()) : null);
            psAcc.setString(5, cust.getAddress());
            psAcc.setString(6, cust.getEmail());
            psAcc.setString(7, cust.getPhone());

            int affectedRows = psAcc.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = psAcc.getGeneratedKeys();
                if (rs.next()) {
                    int accountId = rs.getInt(1);
                    cust.setId(accountId);

                    PreparedStatement psCust = con.prepareStatement(sqlCust);
                    psCust.setInt(1, accountId);
                    psCust.setString(2, cust.getCustomerCode() != null ? cust.getCustomerCode() : "KH" + accountId);
                    psCust.setString(3, cust.getCustomerType() != null ? cust.getCustomerType() : "THUONG");

                    psCust.executeUpdate();
                    psCust.close();
                    con.commit();
                    success = true;
                }
            }
            psAcc.close();
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
