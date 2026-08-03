package Dashboard;

import Service.DashboardService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import Model.JobCardModel;
import Service.DashboardService;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import java.util.LinkedHashMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController implements Initializable {

    @FXML
    private Label lbltotalcustomers;
    @FXML
    private Label lbltotalbikes;
    @FXML
    private Label lbltodayrevenue;
    @FXML
    private Label lbltotalsparespartstock;
    @FXML
    private Label lbllowstock;
    @FXML
    private Label lblpendinjobcard;
    @FXML
    private TableView<JobCardModel> dashtableview;
    @FXML
    private TableColumn<JobCardModel,Integer> dashjobcard;
    @FXML
    private TableColumn<JobCardModel,String> dashcus;
    @FXML
    private TableColumn<JobCardModel,String> dashbknum;
    @FXML
    private TableColumn<JobCardModel,String> dashmechanic;
    @FXML
    private TableColumn<JobCardModel,Double> dashamount;
    @FXML
    private TableColumn<JobCardModel,String> dashstatus;
    @FXML
    private AnchorPane chartPane;
    @FXML
    private Label lblDateTime;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadDashboardCards();
      dashjobcard.setCellValueFactory(new PropertyValueFactory<>("jobCardId"));
    dashcus.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    dashbknum.setCellValueFactory(new PropertyValueFactory<>("bikeNumber"));
    dashmechanic.setCellValueFactory(new PropertyValueFactory<>("mechanicName"));
    dashamount.setCellValueFactory(new PropertyValueFactory<>("approximateCost"));
    dashstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    loadRecentServices();
    loadChart();
    showDateTime();
    }    

    @FXML
    private void customer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/Customer.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Customer Screen");
        stage.show();
    }

    @FXML
    private void Dashboard(ActionEvent event) {
    }

    

    @FXML
    private void JobCard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobCard/JobCard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("JobCard Screen");
        stage.show();
    }

    @FXML
    private void Mechanics(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mechanic/Mechanic.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Mechanic Screen");
        stage.show();
    }

    @FXML
    private void SpareParts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpareParts/SpareParts.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Inventory & SpareParts Screen");
        stage.show();
    }

    @FXML
    private void Reports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports/Reports.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Reports Screen");
        stage.show();
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
    private void BillingAndInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Billing/Billing.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Invoice Screen");
        stage.show();
    }

    private void loadDashboardCards() {

    try {

        DashboardService service = new DashboardService();

        lbltotalcustomers.setText(
                String.valueOf(service.getTotalCustomers()));

        lbltotalbikes.setText(
                String.valueOf(service.getTotalBikes()));

        lbltodayrevenue.setText(
                String.valueOf(service.getTodayRevenue()));

        lbltotalsparespartstock.setText(
                String.valueOf(service.getTotalSpareStock()));

        lbllowstock.setText(
                String.valueOf(service.getLowStockItems()));

        lblpendinjobcard.setText(
                String.valueOf(service.getPendingJobCards()));

    } catch (Exception e) {

        e.printStackTrace();

    }

} 
    private void loadRecentServices() {

    try {

        DashboardService service = new DashboardService();

        ObservableList<JobCardModel> list = service.getRecentServices();

        dashtableview.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    
private void loadChart() {

 try {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Services");

        LineChart<String, Number> chart =
                new LineChart<>(xAxis, yAxis);

        chart.setTitle("Service Overview");

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.setName("Services");

        DashboardService service = new DashboardService();

        LinkedHashMap<String, Integer> map =
                service.getMonthlyServices();

        for (String month : map.keySet()) {

            series.getData().add(
                    new XYChart.Data<>(month, map.get(month)));

        }

        chart.getData().add(series);

        chart.setAnimated(false);

        chart.setPrefWidth(850);
        chart.setPrefHeight(250);

        chartPane.getChildren().clear();
        chartPane.getChildren().add(chart);

    } catch (Exception e) {

        e.printStackTrace();

    }
}
private void showDateTime() {

    Timeline timeline = new Timeline(

        new KeyFrame(Duration.seconds(1), e -> {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy | hh:mm:ss a");

            lblDateTime.setText(now.format(formatter));

        })

    );

    timeline.setCycleCount(Timeline.INDEFINITE);

    timeline.play();

}
}