
package Dao;
import Database.Database;
import Model.JobCardModel;
import Model.ReportsModel;
import Model.SparePartsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportsDao {
    Database db = new Database();
    public ArrayList<ReportsModel> getAllReports() {

    ArrayList<ReportsModel> list = new ArrayList<>();

    try {

        Connection con = db.connect();

        String sql =
                "SELECT j.job_card_id, " +
                "DATE(j.created_date) AS report_date, " +
                "c.customer_name, " +
                "c.bike_number,"+
                "m.mechanic_name, " +
                "j.approximate_cost, " +
                "j.status " +
                "FROM job_card j " +
                "JOIN customer_bike c ON j.customer_id = c.customer_id " +
                "LEFT JOIN mechanic m ON j.mechanic_id = m.mechanic_id " +
                "ORDER BY j.job_card_id DESC";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            ReportsModel model = new ReportsModel();

            model.setJobCardId(rs.getInt("job_card_id"));

            model.setDate(
                    rs.getDate("report_date").toLocalDate());

            model.setCustomerAndBike(
        rs.getString("customer_name") + " - " +
        rs.getString("bike_number"));

            model.setMechanicName(
                    rs.getString("mechanic_name"));

            model.setAmount(
                    rs.getDouble("approximate_cost"));

            model.setStatus(
                    rs.getString("status"));

            list.add(model);

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;

}
  public double getTotalRevenue() {

    double total = 0;

    try {

        Connection con = db.connect();

        String sql =
                "SELECT COALESCE(SUM(grand_total),0) AS total FROM invoice";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            total = rs.getDouble("total");

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return total;

}
public int getTotalJobCards() {

    int total = 0;

    try {

        Connection con = db.connect();

        String sql =
                "SELECT COUNT(*) AS total FROM job_card";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return total;

} 
public int getLowStockCount() {

    int total = 0;

    try {

        Connection con = db.connect();

        String sql =
                "SELECT COUNT(*) AS total " +
                "FROM spare_parts " +
                "WHERE current_stock <= min_stock";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return total;

}
public int getPendingJobCards() {

    int total = 0;

    try {

        Connection con = db.connect();

        String sql =
                "SELECT COUNT(*) AS total " +
                "FROM job_card " +
                "WHERE status='Pending'";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return total;

}
public ArrayList<SparePartsModel> getLowStockItems() {

    ArrayList<SparePartsModel> list = new ArrayList<>();

    try {

        Connection con = db.connect();

        String sql =
                "SELECT part_name, current_stock " +
                "FROM spare_parts " +
                "WHERE current_stock <= min_stock " +
                "ORDER BY current_stock ASC";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            SparePartsModel part = new SparePartsModel();

            part.setPartName(rs.getString("part_name"));

            part.setCurrentStock(rs.getInt("current_stock"));

            list.add(part);

        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;

}
public ObservableList<ReportsModel> searchReport(String keyword)
        throws  ClassNotFoundException, SQLException {

    ObservableList<ReportsModel> list = FXCollections.observableArrayList();

    Database db = new Database();
    Connection con = db.connect();

    String sql =
            "SELECT j.job_card_id, " +
            "j.created_date, " +
            "c.customer_name, " +
            "c.bike_number, " +
            "m.mechanic_name, " +
            "j.approximate_cost, " +
            "j.status " +
            "FROM job_card j " +
            "JOIN customer_bike c ON j.customer_id = c.customer_id " +
            "LEFT JOIN mechanic m ON j.mechanic_id = m.mechanic_id " +
            "WHERE CAST(j.job_card_id AS TEXT) ILIKE ? " +
            "OR c.customer_name ILIKE ? " +
            "OR m.mechanic_name ILIKE ? " +
            "OR j.status ILIKE ? " +
            "ORDER BY j.job_card_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);
    ps.setString(3, search);
    ps.setString(4, search);
    

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        ReportsModel report = new ReportsModel();

        report.setJobCardId(rs.getInt("job_card_id"));
        report.setDate(rs.getDate("created_date").toLocalDate());
        report.setCustomerAndBike(
        rs.getString("customer_name") + " - " +
        rs.getString("bike_number"));
        report.setMechanicName(rs.getString("mechanic_name"));
        report.setAmount(rs.getDouble("approximate_cost"));
        report.setStatus(rs.getString("status"));

        list.add(report);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}

}