/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SpareParts;

import Model.SparePartsModel;
import Service.SparePartsService;
import SparePartsPoPUp.SparePartsPoPUpController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SparePartsController implements Initializable {

    @FXML
    private StackPane rootstackpane;
    @FXML
    private TableView<SparePartsModel> SparePartsTable;
    @FXML
    private TableColumn<SparePartsModel,Integer> spid;
    @FXML
    private TableColumn<SparePartsModel,String> spnum;
    @FXML
    private TableColumn<SparePartsModel,String> spname;
    @FXML
    private TableColumn<SparePartsModel,String> spcategory;
    @FXML
    private TableColumn<SparePartsModel,String> spbrand;
    @FXML
    private TableColumn<SparePartsModel,Integer> spcurrentstock;
    @FXML
    private TableColumn<SparePartsModel,Integer> spminstock;
    @FXML
    private TableColumn<SparePartsModel,String> spselflocation;
    @FXML
    private TableColumn<SparePartsModel,Void> spaction;
    @FXML
    private TableColumn<SparePartsModel,Double> spsellingprice;
    @FXML
    private TextField txtSearch;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    spid.setCellValueFactory(new PropertyValueFactory<>("partId"));
    spnum.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
    spname.setCellValueFactory(new PropertyValueFactory<>("partName"));
    spcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    spbrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
    spcurrentstock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
    spminstock.setCellValueFactory(new PropertyValueFactory<>("minStock"));
    spsellingprice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
    spselflocation.setCellValueFactory(new PropertyValueFactory<>("rackLocation"));

    loadSparpart();

    addButtonToTable();
    txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

    searchSpareParts(newValue);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mechanic/Mechanic.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Mechanic Screen");
        stage. show();
    }

    @FXML
    private void SpareParts(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpareParts/SpareParts.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
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
    private void addSpareParts(ActionEvent event) throws IOException {
    try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SparePartsPoPUp/SparePartsPoPUp.fxml"));

        Parent popup = loader.load();

        SparePartsPoPUpController controller = loader.getController();

        controller.setSparePartsController(this);

        rootstackpane.getChildren().add(popup);

    } catch (IOException e) {

        e.printStackTrace();

    }

    }
    
    public void loadSparpart() {

    try {

        SparePartsService service = new SparePartsService();

        ObservableList<SparePartsModel> list = service.getAllSpareParts();

        SparePartsTable.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    private void addButtonToTable() {

    spaction.setCellFactory(param -> new TableCell<SparePartsModel, Void>() {
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

    SparePartsModel part =
            getTableView().getItems().get(getIndex());

    try {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/SparePartsPoPUp/SparePartsPoPUp.fxml"));

        Parent popup = loader.load();

        SparePartsPoPUpController controller =
                loader.getController();

        controller.setSparePart(part);

        controller.setViewMode();

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {

        e.printStackTrace();

    }

});
                // ================= EDIT =================

                editBtn.setOnAction(event -> {

                    SparePartsModel sparePart = getTableView().getItems().get(getIndex());

                    System.out.println("Edit Clicked : " + sparePart.getPartName());

                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SparePartsPoPUp/SparePartsPoPUp.fxml"));

                        Parent popup = loader.load();

                        SparePartsPoPUpController popupController = loader.getController();

                        popupController.setSparePartsController(SparePartsController.this);

                        popupController.setSparePart(sparePart);

                        rootstackpane.getChildren().add(popup);

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                });

                // ================= DELETE =================

                deleteBtn.setOnAction(event -> {

                    SparePartsModel sparePart = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Delete Spare Part");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this spare part?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {

                        try {

                            SparePartsService service = new SparePartsService();

                            service.deleteSparePart(sparePart.getPartId());

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
     
        loadSparpart();
    }
private void searchSpareParts(String keyword) {

    try {

        SparePartsService service = new SparePartsService();

        SparePartsTable.setItems(
                service.searchSpareParts(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

}
}
