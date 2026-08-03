/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CustomerPoPUp;

import Customer.CustomerController;
import Model.CustomerModel;
import Service.CustomerService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KANNAN G
 */
public class CustomerPoPUpController implements Initializable {

    @FXML
    private StackPane popupRoot;
    @FXML
    private TextField cname;
    @FXML
    private TextField cid;
    @FXML
    private TextField cphone;
    @FXML
    private TextField cbknum;
    @FXML
    private TextField cbrand;
    @FXML
    private TextField cmodel;
    @FXML
    private TextArea caddress;
    @FXML
    private TextField ckm;
    @FXML
    private TextArea cnotes;
    private CustomerController customerController;
    private CustomerModel customer;
    @FXML
    private Button btnSave;
    
    public void setCustomer(CustomerModel customer) {

    this.customer = customer;

    cname.setText(customer.getCustomerName());
    cphone.setText(customer.getPhone());
    cbknum.setText(customer.getBikeNumber());
    cbrand.setText(customer.getBrand());
    cmodel.setText(customer.getModel());
    ckm.setText(String.valueOf(customer.getCurrentKm()));
    caddress.setText(customer.getAddress());
    cnotes.setText(customer.getNotes());

}
    
    public void setCustomerController(CustomerController customerController) {
    this.customerController = customerController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) {
    ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);
    }

    @FXML
    private void save(ActionEvent event) throws SQLException, ClassNotFoundException {
        
    if (customer == null) {
        customer = new CustomerModel();
    }
       
       customer.setCustomerName(cname.getText());
       customer.setPhone(cphone.getText());
       customer.setBikeNumber(cbknum.getText());
       customer.setBrand(cbrand.getText());
       customer.setModel(cmodel.getText());
       customer.setCurrentKm(Integer.parseInt(ckm.getText()));
       customer.setAddress(caddress.getText());
       customer.setNotes(cnotes.getText());
       
    CustomerService service = new CustomerService();

    if (customer.getCustomerId() == 0) {

    // Add Customer
    service.saveCustomer(customer);

    } else {

    // Edit Customer
    service.updateCustomer(customer);

    }
     customerController.refreshTable();
     ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);
    }
    public void setViewMode() {

    cname.setDisable(true);
    cphone.setDisable(true);
    cbknum.setDisable(true);
    cbrand.setDisable(true);
    cmodel.setDisable(true);
    ckm.setDisable(true);
    caddress.setDisable(true);
    cnotes.setDisable(true);

    btnSave.setDisable(true);

}
 }
    
   

