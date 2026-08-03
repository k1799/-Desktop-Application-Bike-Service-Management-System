
package Service;

import Dao.JobCardDao;
import Model.JobCardModel;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JobCardService {
    
    public void saveJobCard(JobCardModel jobCard)throws ClassNotFoundException, SQLException {

        JobCardDao dao = new JobCardDao();
        dao.saveJobCard(jobCard);
    }

    public void updateJobCard(JobCardModel jobCard)throws ClassNotFoundException, SQLException {

        JobCardDao dao = new JobCardDao();
        dao.updateJobCard(jobCard);
    }

    public void deleteJobCard(int jobCardId)throws ClassNotFoundException, SQLException {

        JobCardDao dao = new JobCardDao();
        dao.deleteJobCard(jobCardId);
    }

    public ObservableList<JobCardModel> getAllJobCards()throws ClassNotFoundException, SQLException {

        JobCardDao dao = new JobCardDao();

        ArrayList<JobCardModel> list = dao.getAllJobCards();

        return FXCollections.observableArrayList(list);
    } 
    public int getLastJobCardId()
        throws ClassNotFoundException, SQLException {
    JobCardDao dao = new JobCardDao();
    return dao.getLastJobCardId();

}
    public ObservableList<JobCardModel> searchJobCard(String keyword)
        throws Exception {
    JobCardDao dao = new JobCardDao();
    return dao.searchJobCard(keyword);

}
}
