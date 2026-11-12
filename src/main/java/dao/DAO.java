package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DAO {
    private static final String DB_NAME = "db_qlst6";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static final String[] DB_USERS = {"root"};
    private static final String[] DB_PASSWORDS = {"Manh20112005", "root1234", "", "123456", "admin", "password"};
    public static Connection con;

    public DAO() {
        initConnection();
    }

    private static void initConnection() {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    return;
                }
            } catch (Exception ignored) {
                // continue to reconnect
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for (String user : DB_USERS) {
            for (String password : DB_PASSWORDS) {
                try {
                    con = DriverManager.getConnection(DB_URL, user, password);
                    initializeSchema();
                    return;
                } catch (Exception ex) {
                    // try next credential combination
                }
            }
        }

        try {
            String baseUrl = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
            for (String user : DB_USERS) {
                for (String password : DB_PASSWORDS) {
                    try {
                        Connection baseCon = DriverManager.getConnection(baseUrl, user, password);
                        Statement st = baseCon.createStatement();
                        st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                        st.close();
                        baseCon.close();

                        con = DriverManager.getConnection(DB_URL, user, password);
                        initializeSchema();
                        return;
                    } catch (Exception ignored) {
                        // continue trying other credentials
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeSchema() {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblAccount6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "username VARCHAR(50) NOT NULL UNIQUE, "
                    + "password VARCHAR(255) NOT NULL, "
                    + "fullName VARCHAR(100), "
                    + "dob DATE, "
                    + "address VARCHAR(255), "
                    + "email VARCHAR(100) NOT NULL UNIQUE, "
                    + "phone VARCHAR(20), "
                    + "role VARCHAR(20) NOT NULL, "
                    + "PRIMARY KEY (id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblCustomer6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "account_id INT NOT NULL UNIQUE, "
                    + "customerCode VARCHAR(50) NOT NULL UNIQUE, "
                    + "customerType VARCHAR(20), "
                    + "PRIMARY KEY (id), "
                    + "CONSTRAINT fk_customer_account FOREIGN KEY (account_id) REFERENCES tblAccount6(id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblSupplier6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "supplierCode VARCHAR(50) NOT NULL UNIQUE, "
                    + "supplierName VARCHAR(100) NOT NULL, "
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(20), "
                    + "email VARCHAR(100), "
                    + "description TEXT, "
                    + "PRIMARY KEY (id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblItem6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "itemCode VARCHAR(50) NOT NULL UNIQUE, "
                    + "itemName VARCHAR(150) NOT NULL, "
                    + "category VARCHAR(100), "
                    + "specifications TEXT, "
                    + "price DECIMAL(15,2) NOT NULL DEFAULT 0, "
                    + "stockQuantity INT NOT NULL DEFAULT 0, "
                    + "PRIMARY KEY (id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblImportInvoice6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "importCode VARCHAR(50) NOT NULL UNIQUE, "
                    + "importDate DATETIME NOT NULL, "
                    + "totalValue DECIMAL(15,2) NOT NULL, "
                    + "supplier_id INT NOT NULL, "
                    + "warehouse_staff_id INT NOT NULL DEFAULT 1, "
                    + "PRIMARY KEY (id), "
                    + "CONSTRAINT fk_import_supplier6 FOREIGN KEY (supplier_id) REFERENCES tblSupplier6(id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tblImportDetail6 ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "import_invoice_id INT NOT NULL, "
                    + "item_id INT NOT NULL, "
                    + "quantity INT NOT NULL, "
                    + "importPrice DECIMAL(15,2) NOT NULL, "
                    + "PRIMARY KEY (id), "
                    + "CONSTRAINT fk_import_detail_invoice6 FOREIGN KEY (import_invoice_id) REFERENCES tblImportInvoice6(id), "
                    + "CONSTRAINT fk_import_detail_item6 FOREIGN KEY (item_id) REFERENCES tblItem6(id))");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
