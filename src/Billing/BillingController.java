/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Billing;

import BillingPoPUp.BillingPoPUpController;
import Model.InvoiceModel;
import Model.JobCardModel;
import Service.InvoiceService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KANNAN G
 */
public class BillingController implements Initializable {

    @FXML
    private StackPane rootstackpane;
    @FXML
    private TableView<InvoiceModel> billingtable;
    @FXML
    private TableColumn<InvoiceModel,String> invoiceno;
    @FXML
    private TableColumn<InvoiceModel,LocalDate> billdate;
    @FXML
    private TableColumn<InvoiceModel,String> jobcardno;
    @FXML
    private TableColumn<InvoiceModel,String> cusname;
    @FXML
    private TableColumn<InvoiceModel,String> bknum;
    @FXML
    private TableColumn<InvoiceModel,Double> totalcost;
    @FXML
    private TableColumn<InvoiceModel,String> paymentstatus;
    @FXML
    private TableColumn<InvoiceModel,Void> iaction;
    
    private JobCardModel jobCard;
    @FXML
    private TextField txtSearch;
    
    public void setJobCard(JobCardModel jobCard) {

    this.jobCard = jobCard;

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoiceno.setCellValueFactory(
            new PropertyValueFactory<>("invoiceNo"));

    billdate.setCellValueFactory(
            new PropertyValueFactory<>("billDate"));

    jobcardno.setCellValueFactory(
            new PropertyValueFactory<>("jobCardId"));

    cusname.setCellValueFactory(
            new PropertyValueFactory<>("customerName"));

    bknum.setCellValueFactory(
            new PropertyValueFactory<>("bikeNumber"));

    totalcost.setCellValueFactory(
            new PropertyValueFactory<>("grandTotal"));

    paymentstatus.setCellValueFactory(
            new PropertyValueFactory<>("paymentStatus"));

    loadInvoices();

    addButtonToTable();
    
    txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

    searchInvoice(newValue);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpareParts/SpareParts.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Inventory & SpareParts Screen");
        stage. show();
    }

    @FXML
    private void BillingAndInvoice(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Billing/Billing.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
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
        stage. show();
    }

    private void newInvoice(ActionEvent event) throws IOException {
      try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BillingPoPUp/BillingPoPUp.fxml"));

        Parent popup = loader.load();

        rootstackpane.getChildren().add(popup);

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    private void loadInvoices() {

    try {

        InvoiceService service = new InvoiceService();

        billingtable.setItems(service.getAllInvoices());

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    public void refreshTable() {

    loadInvoices();

}
    private void addButtonToTable() {

    iaction.setCellFactory(param -> new TableCell<InvoiceModel, Void>() {

        private final Button viewBtn = new Button("View");

        @Override
        protected void updateItem(Void item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {

                setGraphic(null);

            } else {

                InvoiceModel invoice = getTableView().getItems().get(getIndex());

                viewBtn.setOnAction(event -> {

                    try {

                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource("/BillingPoPUp/BillingPoPUp.fxml"));

                        Parent popup = loader.load();

                        BillingPoPUpController controller =
                                loader.getController();

                        controller.setInvoice(invoice);

                        rootstackpane.getChildren().add(popup);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                });

                setGraphic(viewBtn);

            }

        }

    });

}
    
   private void searchInvoice(String keyword) {

    try {

        InvoiceService service = new InvoiceService();

        billingtable.setItems(
                service.searchInvoice(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

} 
}
