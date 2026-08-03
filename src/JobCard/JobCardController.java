
package JobCard;

import BillingPoPUp.BillingPoPUpController;
import JobCardPoPUp.JobCardPoPUpController;
import Model.JobCardModel;
import Service.JobCardService;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class JobCardController implements Initializable {

    @FXML
    private StackPane rootstackpane;
    @FXML
    private TableView<JobCardModel> jobcardtable;
    @FXML
    private TableColumn<JobCardModel,Integer> jno;
    @FXML
    private TableColumn<JobCardModel,Timestamp> jdatetime;
    @FXML
    private TableColumn<JobCardModel,String> jcustomer;
    @FXML
    private TableColumn<JobCardModel,String> kbknum;
    @FXML
    private TableColumn<JobCardModel,String> jservicetype;
    @FXML
    private TableColumn<JobCardModel,String> jassignmechanic;
    @FXML
    private TableColumn<JobCardModel,Date> jdeliverydate;
    @FXML
    private TableColumn<JobCardModel,String> jstatus;
    @FXML
    private TableColumn<JobCardModel,Void> jaction;
    @FXML
    private TextField txtSearch;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    jno.setCellValueFactory(new PropertyValueFactory<>("jobCardId"));
    jcustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    kbknum.setCellValueFactory(new PropertyValueFactory<>("bikeNumber"));
    jservicetype.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
    jassignmechanic.setCellValueFactory(new PropertyValueFactory<>("mechanicName"));
    jstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    jdeliverydate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    jdatetime.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
    refreshTable();
    addButtonToTable();
    txtSearch.textProperty().addListener((obs, oldValue, newValue) -> {

    searchJobCard(newValue);

});
   
    }    

    @FXML
    private void Dashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Dashboard.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard Screen");
        stage. show();
    }

    @FXML
    private void customer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/Customer.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Customer Screen");
        stage. show();
    }

    @FXML
    private void JobCard(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobCard/JobCard.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
    }

    @FXML
    private void Mechanics(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mechanic/Mechanic.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
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
        stage.setTitle("Reports Screen");
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
    private void createJobCard(ActionEvent event) throws IOException {
    try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobCardPoPUp/JobCardPoPUp.fxml"));

        Parent popup = loader.load();
         
        JobCardPoPUpController popupController = loader.getController();

       
        popupController.setJobCardController(this);

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    
    public void refreshTable() {

    try {

        JobCardService service = new JobCardService();

        ObservableList<JobCardModel> list =
                FXCollections.observableArrayList(service.getAllJobCards());
        System.out.println("Total Records = " + list.size());
        
        jobcardtable.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    
 private void addButtonToTable() {

    jaction.setCellFactory(param -> new TableCell<JobCardModel, Void>() {

        private final Button viewBtn = new Button("View");
        private final Button editBtn = new Button("Edit");
        private final Button completeBtn = new Button("Complete");
        private final Button billBtn = new Button("Bill");

        @Override
        protected void updateItem(Void item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {

                setGraphic(null);

            } else {

                JobCardModel jobCard = getTableView().getItems().get(getIndex());

                HBox box = new HBox(8);

                box.getChildren().addAll(viewBtn, editBtn, completeBtn, billBtn);

                // ================= VIEW =================
                viewBtn.setOnAction(event -> {

                    try {

                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/JobCardPoPUp/JobCardPoPUp.fxml"));

                        Parent popup = loader.load();

                        JobCardPoPUpController controller = loader.getController();

                        controller.setJobCardController(JobCardController.this);

                        controller.setJobCard(jobCard);

                        // View Mode
                        controller.disableEditing();

                        rootstackpane.getChildren().add(popup);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                });

                // ================= EDIT =================
                editBtn.setOnAction(event -> {

                    try {

                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/JobCardPoPUp/JobCardPoPUp.fxml"));

                        Parent popup = loader.load();

                        JobCardPoPUpController controller = loader.getController();

                        controller.setJobCardController(JobCardController.this);

                        controller.setJobCard(jobCard);

                        rootstackpane.getChildren().add(popup);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                });

                // ================= COMPLETE =================
                completeBtn.setOnAction(event -> {

                    try {

                        JobCardService service = new JobCardService();

                        jobCard.setStatus("Completed");

                        service.updateJobCard(jobCard);

                        refreshTable();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                });

                // ================= BILL =================
                billBtn.setOnAction(event -> {
                
                    try {

                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/BillingPoPUp/BillingPoPUp.fxml"));

                        Parent popup = loader.load();

                        BillingPoPUpController controller = loader.getController();

                        controller.setJobCard(jobCard);

                        rootstackpane.getChildren().add(popup);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                });

                // Pending என்றால் மட்டும் Edit & Complete
                if (jobCard.getStatus().equalsIgnoreCase("Pending")) {

                    editBtn.setDisable(false);

                    completeBtn.setDisable(false);

                    billBtn.setDisable(true);

                } else {

                    editBtn.setDisable(true);

                    completeBtn.setDisable(true);

                    billBtn.setDisable(false);

                }

                setGraphic(box);

            }

        }

    });

}
 
private void searchJobCard(String keyword) {

    try {

        JobCardService service = new JobCardService();

        jobcardtable.setItems(
                service.searchJobCard(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

} 
 
    
}