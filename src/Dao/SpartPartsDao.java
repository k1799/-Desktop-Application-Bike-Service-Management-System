
package Dao;

import Database.Database;
import Model.SparePartsModel;
import Model.SparePartsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
public class SpartPartsDao {

    public void saveSparePart(SparePartsModel sparePart) throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "INSERT INTO spare_parts(part_number, part_name, category, brand, compatible_vehicle, cost_price, selling_price, opening_quantity, current_stock, min_stock, rack_location, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sparePart.getPartNumber());
        ps.setString(2, sparePart.getPartName());
        ps.setString(3, sparePart.getCategory());
        ps.setString(4, sparePart.getBrand());
        ps.setString(5, sparePart.getCompatibleVehicle());
        ps.setDouble(6, sparePart.getCostPrice());
        ps.setDouble(7, sparePart.getSellingPrice());
        ps.setInt(8, sparePart.getOpeningQuantity());

        // First time save pannumbodhu Current Stock = Opening Quantity
        ps.setInt(9, sparePart.getOpeningQuantity());

        ps.setInt(10, sparePart.getMinStock());
        ps.setString(11, sparePart.getRackLocation());
        ps.setString(12, sparePart.getNotes());

        ps.executeUpdate();

        ps.close();
        con.close();
    }
    
    public ObservableList<SparePartsModel> getAllSpareParts()throws SQLException, ClassNotFoundException {

    ObservableList<SparePartsModel> list = FXCollections.observableArrayList();

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT * FROM spare_parts";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        SparePartsModel sparePart = new SparePartsModel();

        sparePart.setPartId(rs.getInt("part_id"));
        sparePart.setPartNumber(rs.getString("part_number"));
        sparePart.setPartName(rs.getString("part_name"));
        sparePart.setCategory(rs.getString("category"));
        sparePart.setBrand(rs.getString("brand"));
        sparePart.setCompatibleVehicle(rs.getString("compatible_vehicle"));
        sparePart.setCostPrice(rs.getDouble("cost_price"));
        sparePart.setSellingPrice(rs.getDouble("selling_price"));
        sparePart.setOpeningQuantity(rs.getInt("opening_quantity"));
        sparePart.setCurrentStock(rs.getInt("current_stock"));
        sparePart.setMinStock(rs.getInt("min_stock"));
        sparePart.setRackLocation(rs.getString("rack_location"));
        sparePart.setNotes(rs.getString("notes"));

        list.add(sparePart);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
    
    public void updateSparePart(SparePartsModel sparePart) throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "UPDATE spare_parts SET part_number=?, part_name=?, category=?, brand=?, compatible_vehicle=?, cost_price=?, selling_price=?, current_stock=?, min_stock=?, rack_location=?, notes=? WHERE part_id=?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, sparePart.getPartNumber());
    ps.setString(2, sparePart.getPartName());
    ps.setString(3, sparePart.getCategory());
    ps.setString(4, sparePart.getBrand());
    ps.setString(5, sparePart.getCompatibleVehicle());
    ps.setDouble(6, sparePart.getCostPrice());
    ps.setDouble(7, sparePart.getSellingPrice());
    ps.setInt(8, sparePart.getCurrentStock());
    ps.setInt(9, sparePart.getMinStock());
    ps.setString(10, sparePart.getRackLocation());
    ps.setString(11, sparePart.getNotes());
    ps.setInt(12, sparePart.getPartId());

    ps.executeUpdate();

    ps.close();
    con.close();
}
    public void deleteSparePart(int partId) throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "DELETE FROM spare_parts WHERE part_id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, partId);

    ps.executeUpdate();

    ps.close();
    con.close();
}
    public ObservableList<String> getPartNames()throws ClassNotFoundException, SQLException {

    ObservableList<String> list = FXCollections.observableArrayList();

    Database db = new Database();

    Connection con = db.connect();

    String sql = "SELECT part_name FROM spare_parts ORDER BY part_name";
    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        list.add(rs.getString("part_name"));
    }



    rs.close();
    ps.close();
    con.close();

    return list;

}
    public void updateStock(int partId, int quantity)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "UPDATE spare_parts " +
                 "SET current_stock = current_stock - ? " +
                 "WHERE part_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, quantity);
    ps.setInt(2, partId);

    ps.executeUpdate();

    ps.close();
    con.close();

}
public SparePartsModel getPartByName(String partName)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT * FROM spare_parts WHERE part_name = ?";

    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, partName);

    ResultSet rs = ps.executeQuery();

    SparePartsModel part = null;

    if (rs.next()) {

        part = new SparePartsModel();

        part.setPartId(rs.getInt("part_id"));
        part.setPartName(rs.getString("part_name"));
        part.setSellingPrice(rs.getDouble("selling_price"));
        part.setCurrentStock(rs.getInt("current_stock"));
//       part.setCompatibleVehicle(rs.getString("compatible_vehicle")); 
    }

    rs.close();
    ps.close();
    con.close();

    return part;
}
   public void increaseStock(int partId, int quantity)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    String sql = "UPDATE spare_parts SET current_stock = current_stock + ? WHERE part_id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, quantity);
    ps.setInt(2, partId);

    ps.executeUpdate();

    ps.close();
    con.close();
} 
   public ObservableList<SparePartsModel> searchSpareParts(String keyword)
        throws Exception {

    ObservableList<SparePartsModel> list =
            FXCollections.observableArrayList();
    Database db = new Database();
    Connection con = db.connect();

    String sql =
            "SELECT * FROM spare_parts " +
            "WHERE part_name ILIKE ? " +
            "OR part_number ILIKE ? " +
            "ORDER BY part_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        SparePartsModel part = new SparePartsModel();

        part.setPartId(rs.getInt("part_id"));
        part.setPartNumber(rs.getString("part_number"));
        part.setPartName(rs.getString("part_name"));
        part.setCategory(rs.getString("category"));
        part.setBrand(rs.getString("brand"));
        part.setCompatibleVehicle(rs.getString("compatible_vehicle"));
        part.setCostPrice(rs.getDouble("cost_price"));
        part.setSellingPrice(rs.getDouble("selling_price"));
        part.setOpeningQuantity(rs.getInt("opening_quantity"));
        part.setCurrentStock(rs.getInt("current_stock"));
        part.setMinStock(rs.getInt("min_stock"));
        part.setRackLocation(rs.getString("rack_location"));
        part.setNotes(rs.getString("notes"));

        list.add(part);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
}
