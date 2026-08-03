
package Dao;

import Database.Database;
import Model.CustomerModel;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class CustomerDao {
  
    public void saveCustomer(CustomerModel customer) throws SQLException, ClassNotFoundException {
      
      Database db = new Database();
      Connection con = db.connect();
      
      String sql = "INSERT INTO customer_bike(customer_name, phone, bike_number, brand, model, current_km, address, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      
      PreparedStatement ps = con.prepareStatement(sql);
      
      ps.setString(1,customer.getCustomerName());
      ps.setString(2,customer.getPhone());
      ps.setString(3,customer.getBikeNumber());
      ps.setString(4,customer.getBrand());
      ps.setString(5,customer.getModel());
      ps.setInt(6,customer.getCurrentKm());
      ps.setString(7,customer.getAddress());
      ps.setString(8,customer.getNotes());
      
      ps.executeUpdate();
      
      ps.close();
      con.close();
    }
    
    public void updateCustomer(CustomerModel customer) throws SQLException, ClassNotFoundException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "UPDATE customer_bike SET customer_name=?, phone=?, bike_number=?, brand=?, model=?, current_km=?, address=?, notes=? WHERE customer_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, customer.getCustomerName());
    ps.setString(2, customer.getPhone());
    ps.setString(3, customer.getBikeNumber());
    ps.setString(4, customer.getBrand());
    ps.setString(5, customer.getModel());
    ps.setInt(6, customer.getCurrentKm());
    ps.setString(7, customer.getAddress());
    ps.setString(8, customer.getNotes());

    // WHERE customer_id = ?
    ps.setInt(9, customer.getCustomerId());

    ps.executeUpdate();

    ps.close();
    con.close();
}
    public void deleteCustomer(int customerId) throws SQLException, ClassNotFoundException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "DELETE FROM customer_bike WHERE customer_id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, customerId);

    ps.executeUpdate();

    ps.close();
    con.close();
}
    
    public ObservableList<CustomerModel> getAllCustomers()throws SQLException, ClassNotFoundException {
      
      Database db = new Database();
      Connection con = db.connect();
        
    ObservableList<CustomerModel> list = FXCollections.observableArrayList();
    
    String sql = "SELECT * FROM customer_bike";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {

    CustomerModel customer = new CustomerModel();
     
    customer.setCustomerId(rs.getInt("customer_id"));
    customer.setCustomerName(rs.getString("customer_name"));
    customer.setPhone(rs.getString("phone"));
    customer.setBikeNumber(rs.getString("bike_number"));
    customer.setBrand(rs.getString("brand"));
    customer.setModel(rs.getString("model"));
    customer.setCurrentKm(rs.getInt("current_km"));
    customer.setAddress(rs.getString("address"));
    customer.setNotes(rs.getString("notes"));
    
    list.add(customer);
  }
     rs.close();
     ps.close();
     con.close();
    
    return list;
}
    public ObservableList<String> getBikeNumbers() throws ClassNotFoundException, SQLException {

    ObservableList<String> bikeNumbers = FXCollections.observableArrayList();

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT bike_number FROM customer_bike ORDER BY bike_number";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        bikeNumbers.add(rs.getString("bike_number"));

    }

    rs.close();
    ps.close();
    con.close();

    return bikeNumbers;

}
    public CustomerModel getCustomerByBikeNumber(String bikeNumber) throws ClassNotFoundException, SQLException {

    CustomerModel customer = null;

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT * FROM customer_bike WHERE bike_number = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, bikeNumber);

    ResultSet rs = ps.executeQuery();

    if (rs.next()) {

        customer = new CustomerModel();

        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerName(rs.getString("customer_name"));
        customer.setBikeNumber(rs.getString("bike_number"));
        customer.setBrand(rs.getString("brand"));
        customer.setModel(rs.getString("model"));
        customer.setCurrentKm(rs.getInt("current_km"));

    }

    rs.close();
    ps.close();
    con.close();

    return customer;

}
    public ObservableList<CustomerModel> searchCustomer(String keyword)
        throws Exception {

    ObservableList<CustomerModel> list =
            FXCollections.observableArrayList();
     Database db = new Database();
    Connection con = db.connect();

    String sql =
            "SELECT * FROM customer_bike " +
            "WHERE customer_name ILIKE ? " +
            "OR phone ILIKE ? " +
            "OR bike_number ILIKE ? " +
            "ORDER BY customer_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);
    ps.setString(3, search);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        CustomerModel model = new CustomerModel();

        model.setCustomerId(rs.getInt("customer_id"));
        model.setCustomerName(rs.getString("customer_name"));
        model.setPhone(rs.getString("phone"));
        model.setBikeNumber(rs.getString("bike_number"));
        model.setBrand(rs.getString("brand"));
        model.setModel(rs.getString("model"));
        model.setCurrentKm(rs.getInt("current_km"));
        model.setAddress(rs.getString("address"));
        model.setNotes(rs.getString("notes"));

        list.add(model);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
}
