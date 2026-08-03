
package Service;
import Dao.UsedSparePartDao;
import Model.UsedSparePartModel;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class UsedSparePartService {
  UsedSparePartDao dao = new UsedSparePartDao();

    public void saveUsedSparePart(UsedSparePartModel part)throws ClassNotFoundException, SQLException {

        dao.saveUsedSparePart(part);

    }

   public ObservableList<UsedSparePartModel>
getUsedPartsByJobCard(int jobCardId)
        throws ClassNotFoundException, SQLException {

    return dao.getUsedPartsByJobCard(jobCardId);

} 
    public void deleteByJobCardId(int jobCardId)
        throws ClassNotFoundException, SQLException {

    dao.deleteByJobCardId(jobCardId);

}
}
