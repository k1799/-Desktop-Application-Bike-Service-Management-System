
package Mechanic;

import MechanicPoPUp.MechanicPoPUpController;
import Model.MechanicModel;
import Service.MechanicService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Service.MechanicService;
import Model.MechanicModel;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class MechanicController implements Initializable {

    @FXML
    private StackPane rootstackpane;
    @FXML
    private TableView<MechanicModel> mechanictable;
    @FXML
    private TableColumn<MechanicModel,Integer> mid;
    @FXML
    private TableColumn<MechanicModel,String> mname;
    @FXML
    private TableColumn<MechanicModel,String> mphone;
    @FXML
    private TableColumn<MechanicModel,String> memail;
    @FXML
    private TableColumn<MechanicModel,String> midproof;
    @FXML
    private TableColumn<MechanicModel,String> mspcification;
    @FXML
    private TableColumn<MechanicModel,Integer> mexp;
    @FXML
    private TableColumn<MechanicModel,String> maddress;
    @FXML
    private TableColumn<MechanicModel,String> mstatus;
    @FXML
    private TableColumn<MechanicModel,Void> maction;
    @FXML
    private TextField txtSearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    mid.setCellValueFactory(new PropertyValueFactory<>("mechanicId"));
    mname.setCellValueFactory(new PropertyValueFactory<>("mechanicName"));
    mphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    memail.setCellValueFactory(new PropertyValueFactory<>("email"));
    midproof.setCellValueFactory(new PropertyValueFactory<>("idProof"));
    mspcification.setCellValueFactory(new PropertyValueFactory<>("specialization"));
    mexp.setCellValueFactory(new PropertyValueFactory<>("experience"));
    maddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    mstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
     
       loadMechanics();
       addButtonToTable();
       txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

    searchMechanic(newValue);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobCard/JobCard.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("JobCard Screen");
        stage. show();
    }

    @FXML
    private void Mechanics(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mechanic/Mechanic.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
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
    private void addMechanic(ActionEvent event) throws IOException {
     try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MechanicPoPUp/MechanicPoPUp.fxml"));

        Parent popup = loader.load();

        MechanicPoPUpController popupController = loader.getController();

        popupController.setMechanicController(this);

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
    
    public void loadMechanics() {

    try {

        MechanicService service = new MechanicService();

        ObservableList<MechanicModel> list =FXCollections.observableArrayList(service.getAllMechanics());

        mechanictable.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
     private void addButtonToTable() {

    maction.setCellFactory(param -> new TableCell<MechanicModel, Void>() {
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

                box.getChildren().addAll(viewBtn,editBtn, deleteBtn);
                
                viewBtn.setOnAction(event -> {

    MechanicModel mechanic =
            getTableView().getItems().get(getIndex());

    try {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/MechanicPoPUp/MechanicPoPUp.fxml"));

        Parent popup = loader.load();

        MechanicPoPUpController controller =
                loader.getController();

        controller.setMechanic(mechanic);

        controller.setViewMode();

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {

        e.printStackTrace();

    }

});

                // EDIT BUTTON
                editBtn.setOnAction(event -> {

                    MechanicModel mechanic = getTableView().getItems().get(getIndex());

                    System.out.println("Edit Clicked : " + mechanic.getMechanicName());

                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MechanicPoPUp/MechanicPoPUp.fxml"));

                        Parent popup = loader.load();

                        MechanicPoPUpController popupController = loader.getController();

                        popupController.setMechanicController(MechanicController.this);

                        popupController.setMechanic(mechanic);

                        rootstackpane.getChildren().add(popup);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                // DELETE BUTTON
                deleteBtn.setOnAction(event -> {

                    MechanicModel mechanic = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Mechanic");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this mechanic?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {

                        try {

                            MechanicService service = new MechanicService();

                            service.deleteMechanic(mechanic.getMechanicId());

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

    loadMechanics();

}

   private void searchMechanic(String keyword) {

    try {

        MechanicService service = new MechanicService();

        mechanictable.setItems(
                service.searchMechanic(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

} 
    
}
