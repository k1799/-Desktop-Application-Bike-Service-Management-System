
package Dao;

 import Database.Database;
import Model.MechanicModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class MechanicDao {




    public void saveMechanic(MechanicModel mechanic) throws ClassNotFoundException, SQLException {
      Database db = new Database();
    Connection con = db.connect();

    String sql = "INSERT INTO mechanic(mechanic_name, phone, email, id_proof, specialization, experience, address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, mechanic.getMechanicName());
    ps.setString(2, mechanic.getPhone());
    ps.setString(3, mechanic.getEmail());
    ps.setString(4, mechanic.getIdProof());
    ps.setString(5, mechanic.getSpecialization());
    ps.setInt(6, mechanic.getExperience());
    ps.setString(7, mechanic.getAddress());
    ps.setString(8, mechanic.getStatus());

    ps.executeUpdate();

    ps.close();
    con.close();
    }

    public ObservableList<MechanicModel> getAllMechanics() throws ClassNotFoundException, SQLException {
       Database db = new Database();
       Connection con = db.connect();

    ObservableList<MechanicModel> list = FXCollections.observableArrayList();


    String sql = "SELECT * FROM mechanic";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        MechanicModel mechanic = new MechanicModel();

        mechanic.setMechanicId(rs.getInt("mechanic_id"));
        mechanic.setMechanicName(rs.getString("mechanic_name"));
        mechanic.setPhone(rs.getString("phone"));
        mechanic.setEmail(rs.getString("email"));
        mechanic.setIdProof(rs.getString("id_proof"));
        mechanic.setSpecialization(rs.getString("specialization"));
        mechanic.setExperience(rs.getInt("experience"));
        mechanic.setAddress(rs.getString("address"));
        mechanic.setStatus(rs.getString("status"));

        list.add(mechanic);

    }

    rs.close();
    ps.close();
    con.close();
     
    return list;
    }

    public void updateMechanic(MechanicModel mechanic) throws ClassNotFoundException, SQLException {
       Database db = new Database();
    Connection con = db.connect();

    String sql = "UPDATE mechanic SET mechanic_name=?, phone=?, email=?, id_proof=?, specialization=?, experience=?, address=?, status=? WHERE mechanic_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, mechanic.getMechanicName());
    ps.setString(2, mechanic.getPhone());
    ps.setString(3, mechanic.getEmail());
    ps.setString(4, mechanic.getIdProof());
    ps.setString(5, mechanic.getSpecialization());
    ps.setInt(6, mechanic.getExperience());
    ps.setString(7, mechanic.getAddress());
    ps.setString(8, mechanic.getStatus());
    ps.setInt(9, mechanic.getMechanicId());

    ps.executeUpdate();

    ps.close();
    con.close();
    }

    public void deleteMechanic(int mechanicId) throws ClassNotFoundException, SQLException {

         Database db = new Database();
         Connection con = db.connect();

    String sql = "DELETE FROM mechanic WHERE customer_id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, mechanicId);

    ps.executeUpdate();

    ps.close();
    con.close();
    }

  public ObservableList<String> getMechanicNames() throws ClassNotFoundException, SQLException {

    ObservableList<String> mechanics = FXCollections.observableArrayList();

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT mechanic_name FROM mechanic WHERE status='Available' ORDER BY mechanic_name";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        mechanics.add(rs.getString("mechanic_name"));

    }

    rs.close();
    ps.close();
    con.close();

    return mechanics;

}
  public int getMechanicIdByName(String mechanicName)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT mechanic_id FROM mechanic WHERE mechanic_name = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, mechanicName);

    ResultSet rs = ps.executeQuery();

    int mechanicId = 0;

    if (rs.next()) {

        mechanicId = rs.getInt("mechanic_id");

    }

    rs.close();
    ps.close();
    con.close();

    return mechanicId;

}
  public ObservableList<MechanicModel> searchMechanic(String keyword)
        throws Exception {

    ObservableList<MechanicModel> list =
            FXCollections.observableArrayList();
    Database db = new Database();
    Connection con = db.connect();

    String sql =
            "SELECT * FROM mechanic " +
            "WHERE mechanic_name ILIKE ? " +
            "OR phone ILIKE ? " +
            "ORDER BY mechanic_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        MechanicModel model = new MechanicModel();

        model.setMechanicId(rs.getInt("mechanic_id"));
        model.setMechanicName(rs.getString("mechanic_name"));
        model.setPhone(rs.getString("phone"));
        model.setEmail(rs.getString("email"));
        model.setIdProof(rs.getString("id_proof"));
        model.setSpecialization(rs.getString("specialization"));
        model.setExperience(rs.getInt("experience"));
        model.setAddress(rs.getString("address"));
        model.setStatus(rs.getString("status"));

        list.add(model);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
}
