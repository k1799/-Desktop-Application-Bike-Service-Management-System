
package Service;

import Dao.MechanicDao;
import Model.MechanicModel;
import java.sql.SQLException;
import javafx.collections.ObservableList;


public class MechanicService {
    

    public void saveMechanic(MechanicModel mechanic)throws ClassNotFoundException, SQLException {
       MechanicDao dao = new MechanicDao();
        dao.saveMechanic(mechanic);
    }
    
     public void updateMechanics(MechanicModel mechanic) throws SQLException, ClassNotFoundException {

    MechanicDao dao = new MechanicDao();

    dao.updateMechanic(mechanic);

}
     public void deleteMechanic(int mechanicId) throws SQLException, ClassNotFoundException {

    MechanicDao dao = new MechanicDao();

    dao.deleteMechanic(mechanicId);

}
    
     public ObservableList<MechanicModel> getAllMechanics()throws SQLException, ClassNotFoundException {

       MechanicDao dao = new MechanicDao();
       return dao.getAllMechanics();
    }
public ObservableList<String> getMechanicNames()
        throws ClassNotFoundException, SQLException {

    MechanicDao dao = new MechanicDao();

    return dao.getMechanicNames();

}
public int getMechanicIdByName(String mechanicName)
        throws ClassNotFoundException, SQLException {

    MechanicDao dao = new MechanicDao();

    return dao.getMechanicIdByName(mechanicName);

}
public ObservableList<MechanicModel> searchMechanic(String keyword)
        throws Exception {
    MechanicDao dao = new MechanicDao();
    return dao.searchMechanic(keyword);

}
}
