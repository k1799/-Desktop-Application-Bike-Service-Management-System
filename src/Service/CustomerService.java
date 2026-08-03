
package Service;

import Model.CustomerModel;
import Dao.CustomerDao;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class CustomerService {

    public void saveCustomer(CustomerModel customer)throws SQLException, ClassNotFoundException {

        CustomerDao da = new CustomerDao();

        da.saveCustomer(customer);
    }  
    
    public void updateCustomer(CustomerModel customer) throws SQLException, ClassNotFoundException {

    CustomerDao dao = new CustomerDao();

    dao.updateCustomer(customer);

}
   public void deleteCustomer(int customerId) throws SQLException, ClassNotFoundException {

    CustomerDao dao = new CustomerDao();

    dao.deleteCustomer(customerId);

}
    public ObservableList<CustomerModel> getAllCustomers()throws SQLException, ClassNotFoundException {

    CustomerDao dao = new CustomerDao();
    return dao.getAllCustomers();
    }
    
    public ObservableList<String> getBikeNumbers()
        throws ClassNotFoundException, SQLException {

    CustomerDao dao = new CustomerDao();

    return dao.getBikeNumbers();

}
    public CustomerModel getCustomerByBikeNumber(String bikeNumber)
        throws ClassNotFoundException, SQLException {

    CustomerDao dao = new CustomerDao();

    return dao.getCustomerByBikeNumber(bikeNumber);

}
    public ObservableList<CustomerModel> searchCustomer(String keyword)
        throws Exception {
 CustomerDao dao = new CustomerDao();
    return dao.searchCustomer(keyword);

}
}






    

