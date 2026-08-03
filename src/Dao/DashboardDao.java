
package Dao;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.JobCardModel;
import java.util.LinkedHashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DashboardDao {
 
    
    PreparedStatement pst;
    ResultSet rs;

    // Total Customers
    public int getTotalCustomers() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COUNT(*) FROM customer_bike";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    // Total Bikes
    public int getTotalBikes() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COUNT(*) FROM customer_bike";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    // Total Revenue
    public double getTotalRevenue() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COALESCE(SUM(grand_total),0) FROM invoice";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getDouble(1);
        }

        return 0;
    }

    // Total Spare Parts Stock
    public int getTotalSpareStock() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COALESCE(SUM(current_stock),0) FROM spare_parts";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    // Low Stock Items
    public int getLowStockItems() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COUNT(*) FROM spare_parts WHERE current_stock <= min_stock";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    // Pending Job Cards
    public int getPendingJobCards() throws ClassNotFoundException, SQLException {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "SELECT COUNT(*) FROM job_card WHERE status='Pending'";

        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }
 public ObservableList<JobCardModel> getRecentServices() throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    ObservableList<JobCardModel> list = FXCollections.observableArrayList();

    String sql = "SELECT j.job_card_id, c.customer_name, c.bike_number, "
            + "m.mechanic_name, j.approximate_cost, j.status "
            + "FROM job_card j "
            + "JOIN customer_bike c ON j.customer_id = c.customer_id "
            + "LEFT JOIN mechanic m ON j.mechanic_id = m.mechanic_id "
            + "ORDER BY j.job_card_id DESC LIMIT 5";

    pst = con.prepareStatement(sql);

    rs = pst.executeQuery();

    while (rs.next()) {

        JobCardModel job = new JobCardModel();

        job.setJobCardId(rs.getInt("job_card_id"));
        job.setCustomerName(rs.getString("customer_name"));
        job.setBikeNumber(rs.getString("bike_number"));
        job.setMechanicName(rs.getString("mechanic_name"));
        job.setApproximateCost(rs.getDouble("approximate_cost"));
        job.setStatus(rs.getString("status"));

        list.add(job);
    }

    return list;
}
 public LinkedHashMap<String, Integer> getMonthlyServices()
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = """
        SELECT TO_CHAR(created_date, 'Mon') AS month,
               COUNT(*) AS total
        FROM job_card
        GROUP BY TO_CHAR(created_date, 'Mon'),
                 EXTRACT(MONTH FROM created_date)
        ORDER BY EXTRACT(MONTH FROM created_date);
    """;

    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    while (rs.next()) {
        map.put(rs.getString("month"), rs.getInt("total"));
    }

    con.close();

    return map;
}
 public int getTodayCustomers() throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = """
        SELECT COUNT(*)
        FROM customer_bike
        WHERE DATE(created_date) = CURRENT_DATE
    """;

    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    int total = 0;

    if (rs.next()) {
        total = rs.getInt(1);
    }

    rs.close();
    ps.close();
    con.close();

    return total;
}
 public double getTodayRevenue()
        throws ClassNotFoundException, SQLException {

    Database db = new Database();
    Connection con = db.connect();

    String sql = """
        SELECT COALESCE(SUM(grand_total),0)
        FROM invoice
        WHERE bill_date = CURRENT_DATE
    """;

    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    double total = 0;

    if (rs.next()) {
        total = rs.getDouble(1);
    }

    rs.close();
    ps.close();
    con.close();

    return total;
}
}
