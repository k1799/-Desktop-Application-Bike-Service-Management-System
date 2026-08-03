
package Service;
import Dao.InvoiceDao;
import Model.InvoiceModel;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class InvoiceService {
     public void saveInvoice(InvoiceModel invoice) throws Exception {

        InvoiceDao dao = new InvoiceDao();

        dao.saveInvoice(invoice);

    }
     
      public ObservableList<InvoiceModel> getAllInvoices() throws ClassNotFoundException, SQLException {

        InvoiceDao dao = new InvoiceDao();

        return dao.getAllInvoices();

    }

    public void deleteInvoice(int invoiceId) throws ClassNotFoundException, SQLException {

        InvoiceDao dao = new InvoiceDao();

        dao.deleteInvoice(invoiceId);

    }
    public int getNextInvoiceId()
        throws ClassNotFoundException, SQLException {

    InvoiceDao dao = new InvoiceDao();

    return dao.getNextInvoiceId();

}

    public boolean isInvoiceExist(int jobCardId) throws SQLException, ClassNotFoundException {


    InvoiceDao dao = new InvoiceDao();


    return dao.isInvoiceExist(jobCardId);

}
    
    public boolean checkInvoiceExists(int jobCardId) throws SQLException, ClassNotFoundException {

    InvoiceDao dao = new InvoiceDao();

    return dao.checkInvoiceExists(jobCardId);

}
   public ObservableList<InvoiceModel> searchInvoice(String keyword)
        throws Exception {
    InvoiceDao dao = new InvoiceDao();
    return dao.searchInvoice(keyword);

}
  
}
