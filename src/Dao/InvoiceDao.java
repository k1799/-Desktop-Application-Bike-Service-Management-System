
package Dao;
import Database.Database;
import Model.InvoiceModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceDao {

    public void saveInvoice(InvoiceModel invoice) throws Exception {

        Database db = new Database();
        Connection con = db.connect();

        String sql = "INSERT INTO invoice "
                + "(invoice_no, job_card_id, customer_id, bill_date,"
                + " spare_total, labour_charge,"
                + " grand_total, payment_mode, payment_status)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, invoice.getInvoiceNo());

        ps.setInt(2, invoice.getJobCardId());

        ps.setInt(3, invoice.getCustomerId());

        ps.setDate(4, Date.valueOf(invoice.getBillDate()));

        ps.setDouble(5, invoice.getSpareTotal());

        ps.setDouble(6, invoice.getLabourCharge());

        ps.setDouble(7, invoice.getGrandTotal());

        ps.setString(8, invoice.getPaymentMode());

        ps.setString(9, invoice.getPaymentStatus());

        ps.executeUpdate();

        ps.close();
        con.close();
    }  
    
    public ObservableList<InvoiceModel> getAllInvoices()
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    ObservableList<InvoiceModel> list = FXCollections.observableArrayList();

    String sql =
        "SELECT i.invoice_id, i.invoice_no, i.job_card_id, " +
        "i.customer_id, i.bill_date, i.spare_total, " +
        "i.labour_charge, i.grand_total, " +
        "i.payment_mode, i.payment_status, " +
        "c.customer_name, c.bike_number " +
        "FROM invoice i " +
        "INNER JOIN customer_bike c " +
        "ON i.customer_id = c.customer_id " +
        "ORDER BY i.invoice_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        InvoiceModel invoice = new InvoiceModel();

        invoice.setInvoiceId(rs.getInt("invoice_id"));

        invoice.setInvoiceNo(rs.getString("invoice_no"));

        invoice.setJobCardId(rs.getInt("job_card_id"));

        invoice.setCustomerId(rs.getInt("customer_id"));

        invoice.setBillDate(rs.getDate("bill_date").toLocalDate());

        invoice.setSpareTotal(rs.getDouble("spare_total"));

        invoice.setLabourCharge(rs.getDouble("labour_charge"));

        invoice.setGrandTotal(rs.getDouble("grand_total"));

        invoice.setPaymentMode(rs.getString("payment_mode"));

        invoice.setPaymentStatus(rs.getString("payment_status"));

        invoice.setCustomerName(rs.getString("customer_name"));

        invoice.setBikeNumber(rs.getString("bike_number"));

        list.add(invoice);

    }

    rs.close();

    ps.close();

    con.close();

    return list;
}
    public void deleteInvoice(int invoiceId)
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    String sql = "DELETE FROM invoice WHERE invoice_id=?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, invoiceId);

    ps.executeUpdate();

    ps.close();
    con.close();

}
    public int getNextInvoiceId()
        throws ClassNotFoundException, SQLException {

    Database db = new Database();

    Connection con = db.connect();

    String sql = "SELECT COALESCE(MAX(invoice_id),0)+1 AS next_id FROM invoice";

    PreparedStatement ps = con.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();

    int id = 1;

    if (rs.next()) {

        id = rs.getInt("next_id");

    }

    rs.close();
    ps.close();
    con.close();

    return id;

}
    public boolean isInvoiceExist(int jobCardId) throws SQLException, ClassNotFoundException {


   Database db = new Database();

    Connection con = db.connect();
 String sql =
    "SELECT COUNT(*) FROM invoice WHERE job_card_id=?";

    PreparedStatement ps =
    con.prepareStatement(sql);


    ps.setInt(1, jobCardId);


    ResultSet rs = ps.executeQuery();


    if(rs.next()){

        return rs.getInt(1) > 0;

    }


    return false;

}
    public boolean checkInvoiceExists(int jobCardId) throws SQLException, ClassNotFoundException {

         Database db = new Database();

         Connection con = db.connect();
        
    String sql =
    "SELECT COUNT(*) FROM invoice WHERE job_card_id=?";

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setInt(1, jobCardId);


    ResultSet rs = ps.executeQuery();


    if(rs.next()){

        return rs.getInt(1) > 0;

    }


    return false;
}
    public ObservableList<InvoiceModel> searchInvoice(String keyword)
        throws Exception {

    ObservableList<InvoiceModel> list =
            FXCollections.observableArrayList();
    Database db = new Database();
    Connection con = db.connect();

    String sql =
        "SELECT i.*, c.customer_name, c.bike_number " +
        "FROM invoice i " +
        "JOIN customer_bike c ON i.customer_id = c.customer_id " +
        "WHERE i.invoice_no ILIKE ? " +
        "OR CAST(i.job_card_id AS TEXT) ILIKE ? " +
        "OR c.customer_name ILIKE ? " +
        "ORDER BY i.invoice_id DESC";

    PreparedStatement ps = con.prepareStatement(sql);

    String search = "%" + keyword + "%";

    ps.setString(1, search);
    ps.setString(2, search);
    ps.setString(3, search);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        InvoiceModel invoice = new InvoiceModel();

        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setInvoiceNo(rs.getString("invoice_no"));
        invoice.setJobCardId(rs.getInt("job_card_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setBillDate(rs.getDate("bill_date").toLocalDate());
        invoice.setSpareTotal(rs.getDouble("spare_total"));
        invoice.setLabourCharge(rs.getDouble("labour_charge"));
        invoice.setGrandTotal(rs.getDouble("grand_total"));
        invoice.setPaymentMode(rs.getString("payment_mode"));
        invoice.setPaymentStatus(rs.getString("payment_status"));
        
        // TableView display fields
        invoice.setCustomerName(rs.getString("customer_name"));
        invoice.setBikeNumber(rs.getString("bike_number"));

        list.add(invoice);
    }

    rs.close();
    ps.close();
    con.close();

    return list;
}
   
}
