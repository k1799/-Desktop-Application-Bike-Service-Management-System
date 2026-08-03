
package Service;
import Dao.DashboardDao;
import Model.JobCardModel;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javafx.collections.ObservableList;

public class DashboardService {
   DashboardDao dao = new DashboardDao();

    public int getTotalCustomers() throws ClassNotFoundException, SQLException {
        return dao.getTotalCustomers();
    }

    public int getTotalBikes() throws ClassNotFoundException, SQLException {
        return dao.getTotalBikes();
    }

    public double getTotalRevenue() throws ClassNotFoundException, SQLException {
        return dao.getTotalRevenue();
    }

    public int getTotalSpareStock() throws ClassNotFoundException, SQLException {
        return dao.getTotalSpareStock();
    }

    public int getLowStockItems() throws ClassNotFoundException, SQLException {
        return dao.getLowStockItems();
    }

    public int getPendingJobCards() throws ClassNotFoundException, SQLException {
        return dao.getPendingJobCards();
    }  
    public ObservableList<JobCardModel> getRecentServices() throws ClassNotFoundException, SQLException {

    return dao.getRecentServices();

}
    public LinkedHashMap<String, Integer> getMonthlyServices()
        throws ClassNotFoundException, SQLException {

    DashboardDao dao = new DashboardDao();

    return dao.getMonthlyServices();
}
    public int getTodayCustomers()
        throws ClassNotFoundException, SQLException {

    DashboardDao dao = new DashboardDao();

    return dao.getTodayCustomers();
}
    public double getTodayRevenue()
        throws ClassNotFoundException, SQLException {

    DashboardDao dao = new DashboardDao();

    return dao.getTodayRevenue();
}
}
