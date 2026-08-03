
package Dao;

import Database.Database;
import Model.UsedSparePartModel;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsedSparePartDao {
    public void saveUsedSparePart(UsedSparePartModel part)
            throws ClassNotFoundException, SQLException {

        Database db = new Database();

        Connection con = db.connect();

        String sql = "INSERT INTO job_card_spare_parts(job_card_id, part_id, quantity, unit_price, total_price) VALUES(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, part.getJobCardId());

        ps.setInt(2, part.getPartId());

        ps.setInt(3, part.getQuantity());

        ps.setDouble(4, part.getUnitPrice());

        ps.setDouble(5, part.getTotalPrice());

        ps.executeUpdate();

        ps.close();

        con.close();

    }
    
    public ObservableList<UsedSparePartModel>getUsedPartsByJobCard(int jobCardId)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    ObservableList<UsedSparePartModel> list =
            FXCollections.observableArrayList();

String sql =
"SELECT jsp.part_id, " +
"sp.part_name, " +
"jsp.quantity, " +
"jsp.unit_price, " +
"jsp.total_price " +
"FROM job_card_spare_parts jsp " +
"JOIN spare_parts sp " +
"ON jsp.part_id = sp.part_id " +
"WHERE jsp.job_card_id = ?";
    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setInt(1, jobCardId);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        UsedSparePartModel part = new UsedSparePartModel();
        part.setPartId(rs.getInt("part_id"));
        part.setPartName(rs.getString("part_name"));
        part.setQuantity(rs.getInt("quantity"));
        part.setUnitPrice(rs.getDouble("unit_price"));
        part.setTotalPrice(rs.getDouble("total_price"));

        list.add(part);

    }

    rs.close();
    ps.close();
    con.close();

    return list;

}
    public void deleteByJobCardId(int jobCardId)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    String sql = "DELETE FROM job_card_spare_parts WHERE job_card_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, jobCardId);

    ps.executeUpdate();

    ps.close();
    con.close();
}
}
