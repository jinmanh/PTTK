package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Supplier6;

public class SupplierDAO6 extends DAO {

    public SupplierDAO6() {
        super();
    }

    public List<Supplier6> getSupplierByKeyword(String keyword) {
        List<Supplier6> list = new ArrayList<>();
        String sql = "SELECT * FROM tblSupplier6 WHERE supplierCode LIKE ? OR supplierName LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            String kwParam = "%" + keyword + "%";
            ps.setString(1, kwParam);
            ps.setString(2, kwParam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supplier6 sup = new Supplier6();
                sup.setId(rs.getInt("id"));
                sup.setSupplierCode(rs.getString("supplierCode"));
                sup.setSupplierName(rs.getString("supplierName"));
                sup.setAddress(rs.getString("address"));
                sup.setPhone(rs.getString("phone"));
                sup.setEmail(rs.getString("email"));
                sup.setDescription(rs.getString("description"));
                list.add(sup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addSupplier(Supplier6 supplier) {
        if (supplier == null) {
            return false;
        }

        String sql = "INSERT INTO tblSupplier6(supplierCode, supplierName, address, phone, email, description) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, supplier.getSupplierCode());
            ps.setString(2, supplier.getSupplierName());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getPhone());
            ps.setString(5, supplier.getEmail());
            ps.setString(6, supplier.getDescription());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
