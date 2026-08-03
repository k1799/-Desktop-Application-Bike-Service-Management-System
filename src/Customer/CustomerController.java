
package Customer;

import CustomerPoPUp.CustomerPoPUpController;
import Dao.CustomerDao;
import Model.CustomerModel;
import Service.CustomerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import Service.InvoiceService;


public class CustomerController implements Initializable {

    @FXML
    private StackPane rootstackpane;
    @FXML
    private TableView<CustomerModel> customertable;
    @FXML
    private TableColumn<CustomerModel,Integer> cid;
    @FXML
    private TableColumn<CustomerModel,String> cname;
    @FXML
    private TableColumn<CustomerModel,String> cphone;
    @FXML
    private TableColumn<CustomerModel,String> cbknum;
    @FXML
    private TableColumn<CustomerModel,String> cbrand;
    @FXML
    private TableColumn<CustomerModel,String> cmodel;
    @FXML
    private TableColumn<CustomerModel,Integer> ckm;
    @FXML
    private TableColumn<CustomerModel,String> caddress;
    @FXML
    private TableColumn<CustomerModel,String> cnotes;
    @FXML
    private TableColumn<CustomerModel,Void> caction;
    @FXML
    private TextField txtSearch;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cname.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cbknum.setCellValueFactory(new PropertyValueFactory<>("bikeNumber"));
        cbrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        cmodel.setCellValueFactory(new PropertyValueFactory<>("model"));
        ckm.setCellValueFactory(new PropertyValueFactory<>("currentKm"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cnotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        loadCustomers();
        
        addButtonToTable();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

    searchCustomer(newValue);

});
    }    

    @FXML
    private void Dashboard(ActionEvent event) throws IOException {FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Dashboard.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage. show();
    }

    @FXML
    private void customer(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/Customer.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
    }

    @FXML
    private void JobCard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobCard/JobCard.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("JobCard Screen");
        stage. show();
    }

    @FXML
    private void Mechanics(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mechanic/Mechanic.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Mechanic Screen");
        stage. show();
    }

    @FXML
    private void SpareParts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpareParts/SpareParts.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Inventory & SpareParts Screen");
        stage. show();
    }

    @FXML
    private void BillingAndInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Billing/Billing.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Invoice Screen");
        stage. show();
    }

    @FXML
    private void Reports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports/Reports.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Repords Screen");
        stage. show();
    }


    @FXML
    private void Logout(ActionEvent event) throws IOException {
//        Platform.exit();
//        System.exit(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/Login.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void addcustomer(ActionEvent event) throws IOException {
    try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerPoPUp/CustomerPoPUp.fxml"));

        Parent popup = loader.load();
         
        CustomerPoPUpController popupController = loader.getController();

       
        popupController.setCustomerController(this);

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    
    private void loadCustomers() {

    try {

        CustomerService service = new CustomerService();

        ObservableList<CustomerModel> list = service.getAllCustomers();

        customertable.setItems(list);

    } catch (Exception e) {
        e.printStackTrace();
    }

}
    private void addButtonToTable() {

    caction.setCellFactory(param -> new TableCell<CustomerModel, Void>() {
        private final Button viewBtn = new Button("View");
        private final Button editBtn = new Button("Edit");
        private final Button deleteBtn = new Button("Delete");

        @Override
        protected void updateItem(Void item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {

                setGraphic(null);

            } else {

                HBox box = new HBox(10);
                box.getChildren().addAll(viewBtn, editBtn, deleteBtn);
                viewBtn.setOnAction(event -> {

    CustomerModel customer = getTableView().getItems().get(getIndex());

    try {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/CustomerPoPUp/CustomerPoPUp.fxml"));

        Parent popup = loader.load();

        CustomerPoPUpController controller =
                loader.getController();

        controller.setCustomer(customer);

        controller.setViewMode();

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {

        e.printStackTrace();

    }

});
                editBtn.setOnAction(event -> {
                
                CustomerModel customer = getTableView().getItems().get(getIndex());

                 System.out.println("Edit Clicked : " + customer.getCustomerName());
                 
                try {
                    System.out.println("Inside Edit Button");

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerPoPUp/CustomerPoPUp.fxml"));

                    Parent popup = loader.load();
                    System.out.println("Popup Loaded");

                    CustomerPoPUpController popupController = loader.getController();

                    popupController.setCustomerController(CustomerController.this);


                    popupController.setCustomer(customer);
                    System.out.println("Customer Set");

                    rootstackpane.getChildren().add(popup);
                    System.out.println("Popup Added");
                    System.out.println(rootstackpane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                   
                });
                
                deleteBtn.setOnAction(event -> {

    CustomerModel customer = getTableView().getItems().get(getIndex());

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Customer");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this customer?");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {

        try {

            CustomerService service = new CustomerService();

            service.deleteCustomer(customer.getCustomerId());

            refreshTable();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

});
                setGraphic(box);

            }

        }

    });

}
    public void refreshTable() {
    loadCustomers();
}
      private void searchCustomer(String keyword) {

    try {

        CustomerService service = new CustomerService();

        customertable.setItems(
                service.searchCustomer(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

}
}
