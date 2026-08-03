
package Service;

import Dao.SpartPartsDao;
import Model.SparePartsModel;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

public class SparePartsService {
    
     public void saveSparePart(SparePartsModel sparePart) throws ClassNotFoundException, SQLException {

         SpartPartsDao da = new SpartPartsDao();

        da.saveSparePart(sparePart);
    }  
    
    public void updateSparePart(SparePartsModel sparePart) throws ClassNotFoundException, SQLException  {

    SpartPartsDao dao = new SpartPartsDao();

    dao.updateSparePart(sparePart);

}
   public void deleteSparePart(int partId) throws ClassNotFoundException, SQLException  {

    SpartPartsDao dao = new SpartPartsDao();

    dao.deleteSparePart(partId);

}
    public ObservableList<SparePartsModel> getAllSpareParts()throws SQLException, ClassNotFoundException {

    SpartPartsDao dao = new SpartPartsDao();

    return dao.getAllSpareParts();
}
    public ObservableList<String> getPartNames()
        throws ClassNotFoundException, SQLException {
      SpartPartsDao dao = new SpartPartsDao();
        
    return dao.getPartNames();

}
   public SparePartsModel getPartByName(String partName)
        throws ClassNotFoundException, SQLException {
SpartPartsDao dao = new SpartPartsDao();
    return dao.getPartByName(partName);

}
    public void updateStock(int partId, int quantity)
        throws ClassNotFoundException, SQLException {
SpartPartsDao dao = new SpartPartsDao();
    dao.updateStock(partId, quantity);

}
    public void increaseStock(int partId, int quantity)
        throws ClassNotFoundException, SQLException {

    SpartPartsDao dao = new SpartPartsDao();

    dao.increaseStock(partId, quantity);

}
    public ObservableList<SparePartsModel> searchSpareParts(String keyword)
        throws Exception {
    SpartPartsDao dao = new SpartPartsDao();
    return dao.searchSpareParts(keyword);

}
}
 