/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Reports;

import Model.JobCardModel;
import Model.ReportsModel;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import Model.ReportsModel;
import Model.SparePartsModel;
import Service.ReportsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ReportsController implements Initializable {

    @FXML
    private Label totalrevenue;
    @FXML
    private Label activejobcards;
    @FXML
    private Label lowstockalert;
    @FXML
    private TableView<ReportsModel> reporttable;
    @FXML
    private TableColumn<ReportsModel,Integer> rjobcardno;
    @FXML
    private TableColumn<ReportsModel,LocalDate> rdate;
    @FXML
    private TableColumn<ReportsModel,String> rcusandbike;
    @FXML
    private TableColumn<ReportsModel,String> rmechanic;
    @FXML
    private TableColumn<ReportsModel,Double> rcost;
    @FXML
    private TableColumn<ReportsModel,String> rstatus;
    @FXML
    private TableView<SparePartsModel> lowstocktable;
    @FXML
    private TableColumn<SparePartsModel,String> itemname;
    @FXML
    private TableColumn<SparePartsModel,Integer> stock;
    @FXML
    private TextField txtSearch;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    rjobcardno.setCellValueFactory(new PropertyValueFactory<>("jobCardId"));
    rdate.setCellValueFactory(new PropertyValueFactory<>("date"));
    rcusandbike.setCellValueFactory(new PropertyValueFactory<>("customerAndBike"));
    rmechanic.setCellValueFactory(new PropertyValueFactory<>("mechanicName"));
    rcost.setCellValueFactory(new PropertyValueFactory<>("amount"));
    rstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    itemname.setCellValueFactory(new PropertyValueFactory<>("partName"));
    stock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
    loadSummary();
    loadLowStockTable();
    loadReportTable();
    txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    searchReport(newValue);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Billing/Billing.fxml"));
        Parent root = loader.load();
 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Invoice Screen");
        stage. show();
    }

    @FXML
    private void Reports(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports/Reports.fxml"));
//        Parent root = loader.load();
// 
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage. show();
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
    
    private void loadSummary() {

    try {

        ReportsService service = new ReportsService();
        
        
        totalrevenue.setText(
                "₹ " + service.getTotalRevenue());
        
        activejobcards.setText(
             "📋 " + String.valueOf(service.getTotalJobCards()));

        lowstockalert.setText(
             "⚠ " + String.valueOf(service.getLowStockCount()));

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    private void loadReportTable() {

    try {

        ReportsService service = new ReportsService();

        ObservableList<ReportsModel> list =
                FXCollections.observableArrayList(
                        service.getAllReports());

        reporttable.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    private void loadLowStockTable() {

    try {

        ReportsService service = new ReportsService();

        ObservableList<SparePartsModel> list =
                FXCollections.observableArrayList(
                        service.getLowStockItems());

        lowstocktable.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
private void searchReport(String keyword) {

    try {

        ReportsService service = new ReportsService();

        reporttable.setItems(service.searchReport(keyword));

    } catch (Exception e) {

        e.printStackTrace();

    }

}
}
