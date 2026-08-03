
package Dao;

import Database.Database;
import Model.JobCardModel;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class JobCardDao {
   public void saveJobCard(JobCardModel jobCard) throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "INSERT INTO job_card(customer_id, mechanic_id, service_type, fuel_level, current_km, complaint, approximate_cost, delivery_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, jobCard.getCustomerId());
    ps.setInt(2, jobCard.getMechanicId());
    ps.setString(3, jobCard.getServiceType());
    ps.setString(4, jobCard.getFuelLevel());
    ps.setInt(5, jobCard.getCurrentKm());
    ps.setString(6, jobCard.getComplaint());
    ps.setDouble(7, jobCard.getApproximateCost());
    ps.setDate(8, Date.valueOf(jobCard.getDeliveryDate()));
    ps.setString(9, jobCard.getStatus());

    ps.executeUpdate();

    ps.close();
    con.close();
} 
   public void updateJobCard(JobCardModel jobCard) throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "UPDATE job_card SET customer_id=?, mechanic_id=?, service_type=?, fuel_level=?, current_km=?, complaint=?, approximate_cost=?, delivery_date=?, status=? WHERE job_card_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, jobCard.getCustomerId());
    ps.setInt(2, jobCard.getMechanicId());
    ps.setString(3, jobCard.getServiceType());
    ps.setString(4, jobCard.getFuelLevel());
    ps.setInt(5, jobCard.getCurrentKm());
    ps.setString(6, jobCard.getComplaint());
    ps.setDouble(7, jobCard.getApproximateCost());
    ps.setDate(8, Date.valueOf(jobCard.getDeliveryDate()));
    ps.setString(9, jobCard.getStatus());
    ps.setInt(10, jobCard.getJobCardId());

    ps.executeUpdate();

    ps.close();
    con.close();
}
   public void deleteJobCard(int jobCardId) throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "DELETE FROM job_card WHERE job_card_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, jobCardId);

    ps.executeUpdate();

    ps.close();
    con.close();
}
   
   public ArrayList<JobCardModel> getAllJobCards() throws ClassNotFoundException, SQLException {

    ArrayList<JobCardModel> list = new ArrayList<>();

    Database db = new Database();
    Connection con = db.connect();

    String sql =
"SELECT jc.*, " +
"cb.customer_name, " +
"cb.bike_number, " +"cb.brand," +
"cb.model,"+
"m.mechanic_name " +
"FROM job_card jc " +
"LEFT JOIN customer_bike cb ON jc.customer_id = cb.customer_id " +
"LEFT JOIN mechanic m ON jc.mechanic_id = m.mechanic_id " +
"ORDER BY jc.job_card_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        JobCardModel jobCard = new JobCardModel();

        jobCard.setJobCardId(rs.getInt("job_card_id"));
        jobCard.setCustomerId(rs.getInt("customer_id"));
        jobCard.setMechanicId(rs.getInt("mechanic_id"));
        jobCard.setCustomerName(rs.getString("customer_name"));
        jobCard.setBrand(rs.getString("brand"));
        jobCard.setModel(rs.getString("model"));
        jobCard.setBikeNumber(rs.getString("bike_number"));
        jobCard.setMechanicName(rs.getString("mechanic_name"));
        jobCard.setServiceType(rs.getString("service_type"));
        jobCard.setFuelLevel(rs.getString("fuel_level"));
        jobCard.setCurrentKm(rs.getInt("current_km"));
        jobCard.setComplaint(rs.getString("complaint"));
        jobCard.setApproximateCost(rs.getDouble("approximate_cost"));
        jobCard.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
        jobCard.setStatus(rs.getString("status"));
        jobCard.setCreatedDate(rs.getTimestamp("created_date"));

        list.add(jobCard);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
  public int getLastJobCardId()
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = "SELECT MAX(job_card_id) FROM job_card";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    int id = 0;

    if (rs.next()) {

        id = rs.getInt(1);

    }

    rs.close();
    ps.close();
    con.close();

    return id;

}
  public ObservableList<JobCardModel> searchJobCard(String keyword)
        throws Exception {

    ObservableList<JobCardModel> list =
            FXCollections.observableArrayList();
     Database db = new Database();
    Connection con = db.connect();

    String sql =
        "SELECT j.*, c.customer_name, c.bike_number, m.mechanic_name " +
        "FROM job_card j " +
        "JOIN customer_bike c ON j.customer_id = c.customer_id " +
        "LEFT JOIN mechanic m ON j.mechanic_id = m.mechanic_id " +
        "WHERE CAST(j.job_card_id AS TEXT) ILIKE ? " +
        "OR c.customer_name ILIKE ? " +
        "OR c.bike_number ILIKE ? " +
        "ORDER BY j.job_card_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);
    ps.setString(3, search);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        JobCardModel job = new JobCardModel();

        job.setJobCardId(rs.getInt("job_card_id"));
        job.setCustomerId(rs.getInt("customer_id"));
        job.setMechanicId(rs.getInt("mechanic_id"));
        job.setServiceType(rs.getString("service_type"));
        job.setFuelLevel(rs.getString("fuel_level"));
        job.setCurrentKm(rs.getInt("current_km"));
        job.setComplaint(rs.getString("complaint"));
        job.setApproximateCost(rs.getDouble("approximate_cost"));
        job.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
        job.setStatus(rs.getString("status"));

        job.setCustomerName(rs.getString("customer_name"));
        job.setBikeNumber(rs.getString("bike_number"));
        job.setMechanicName(rs.getString("mechanic_name"));

        list.add(job);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
}
