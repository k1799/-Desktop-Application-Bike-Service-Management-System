
package Service;

import Dao.ReportsDao;
import Model.JobCardModel;
import Model.ReportsModel;
import Model.SparePartsModel;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;


public class ReportsService {
    ReportsDao dao = new ReportsDao();

    public ArrayList<ReportsModel> getAllReports() {

        return dao.getAllReports();

    }

    public double getTotalRevenue() {

        return dao.getTotalRevenue();

    }

    public int getTotalJobCards() {

        return dao.getTotalJobCards();

    }

    public int getLowStockCount() {

        return dao.getLowStockCount();

    }

    public int getPendingJobCards() {

        return dao.getPendingJobCards();

    }
 public ArrayList<SparePartsModel> getLowStockItems() {

    return dao.getLowStockItems();

}
 
 public ObservableList<ReportsModel> searchReport(String keyword)
        throws Exception {

    return dao.searchReport(keyword);

}
}
