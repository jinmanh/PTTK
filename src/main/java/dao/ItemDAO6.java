package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Item6;

public class ItemDAO6 extends DAO {

    public ItemDAO6() {
        super();
    }

    public List<Item6> getItemByKeyword(String keyword) {
        List<Item6> list = new ArrayList<>();
        String sql = "SELECT * FROM tblItem6 WHERE itemCode LIKE ? OR itemName LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            String kwParam = "%" + keyword + "%";
            ps.setString(1, kwParam);
            ps.setString(2, kwParam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item6 item = new Item6();
                item.setId(rs.getInt("id"));
                item.setItemCode(rs.getString("itemCode"));
                item.setItemName(rs.getString("itemName"));
                item.setCategory(rs.getString("category"));
                item.setSpecifications(rs.getString("specifications"));
                item.setPrice(rs.getDouble("price"));
                item.setStockQuantity(rs.getInt("stockQuantity"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStock(int itemId, int quantity) {
        boolean success = false;
        String sql = "UPDATE tblItem6 SET stockQuantity = stockQuantity + ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, itemId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean addItem(Item6 item) {
        if (item == null) {
            return false;
        }

        String sql = "INSERT INTO tblItem6(itemCode, itemName, category, specifications, price, stockQuantity) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, item.getItemCode());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getSpecifications());
            ps.setDouble(5, item.getPrice());
            ps.setInt(6, item.getStockQuantity());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
