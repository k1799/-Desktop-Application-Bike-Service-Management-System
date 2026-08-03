/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BillingPoPUp;

import Model.InvoiceModel;
import Model.JobCardModel;
import Model.UsedSparePartModel;
import Service.InvoiceService;
import Service.UsedSparePartService;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BillingPoPUpController implements Initializable {

    @FXML
    private StackPane popupRoot;
    @FXML
    private TextField billno;
    @FXML
    private TextField billdate;
    @FXML
    private TextField jobcardref;
    @FXML
    private TextField cusname;
    @FXML
    private TextField bknum;
    @FXML
    private TableView<UsedSparePartModel> tblSpareParts;
    @FXML
    private TableColumn<UsedSparePartModel,String> colPartName;
    @FXML
    private TableColumn<UsedSparePartModel,Double> colUnitPrice;
    @FXML
    private TableColumn<UsedSparePartModel,Integer> colQuantity;
    @FXML
    private TableColumn<UsedSparePartModel,Double> colTotal;
    @FXML
    private TableColumn<UsedSparePartModel,Void> colAction;
    @FXML
    private ComboBox<String> cmbPaymentMode;
    @FXML
    private TextField txtTotalSpareParts;
    @FXML
    private TextField txtLabourCharge;
    @FXML
    private TextField grandtotal;
    private JobCardModel jobCard;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView imgQR;
    
    public void setJobCard(JobCardModel jobCard) throws ClassNotFoundException, SQLException {

    this.jobCard = jobCard;

    billdate.setText(LocalDate.now().toString());

    jobcardref.setText(
            String.valueOf(jobCard.getJobCardId()));

    cusname.setText(jobCard.getCustomerName());

    bknum.setText(jobCard.getBikeNumber());

    txtLabourCharge.setText(
            String.valueOf(jobCard.getApproximateCost()));
UsedSparePartService service = new UsedSparePartService();

ObservableList<UsedSparePartModel> list =
        service.getUsedPartsByJobCard(jobCard.getJobCardId());

tblSpareParts.setItems(list);
calculateSpareTotal();
calculateGrandTotal();
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         generateBillNumber();

    billdate.setText(LocalDate.now().toString());
       colPartName.setCellValueFactory(new PropertyValueFactory<>("partName"));

    colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    cmbPaymentMode.getItems().addAll(
            "Cash",
            "UPI",
            "Card");


//    cmbPaymentStatus.getItems().addAll(
//            "Paid",
//            "Pending");

       imgQR.setVisible(false);
       
       cmbPaymentMode.setOnAction(e -> {

    if (cmbPaymentMode.getValue().equals("UPI")) {

        Image image = new Image(
                getClass().getResourceAsStream("/BillingPoPUp/upi_qr.jpg"));

        imgQR.setImage(image);

        imgQR.setVisible(true);

    } else {

        imgQR.setVisible(false);

    }

});
    }    

    @FXML
    private void close(ActionEvent event) {
       ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);
    }

    @FXML
    private void saveandprint(ActionEvent event) {
      try {


        if(jobCard == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Job Card Not Loaded");
            alert.showAndWait();

            return;
        }



        if(cmbPaymentMode.getValue()==null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select Payment Mode");
            alert.showAndWait();

            return;
        }



//        if(cmbPaymentStatus.getValue()==null){
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Select Payment Status");
//            alert.showAndWait();
//
//            return;
//        }



        InvoiceModel invoice = new InvoiceModel();


        invoice.setInvoiceNo(
                billno.getText()
        );


        invoice.setJobCardId(
                jobCard.getJobCardId()
        );


        invoice.setCustomerId(
                jobCard.getCustomerId()
        );


        invoice.setBillDate(
                LocalDate.parse(billdate.getText())
        );


        invoice.setSpareTotal(
                Double.parseDouble(txtTotalSpareParts.getText())
        );


        invoice.setLabourCharge(
                Double.parseDouble(txtLabourCharge.getText())
        );


        invoice.setGrandTotal(
                Double.parseDouble(grandtotal.getText())
        );


        invoice.setPaymentMode(
                cmbPaymentMode.getValue()
        );

          invoice.setPaymentStatus("Paid");
          
//        invoice.setPaymentStatus(
//                cmbPaymentStatus.getValue()
//        );



        InvoiceService service = new InvoiceService();
        
        boolean exists = 
        service.checkInvoiceExists(jobCard.getJobCardId());


if(exists){

    Alert alert = new Alert(Alert.AlertType.WARNING);

    alert.setHeaderText(null);

    alert.setContentText(
        "Invoice already created for this Job Card"
    );

    alert.showAndWait();

    return;

}

        service.saveInvoice(invoice);



        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(
                "Invoice Saved Successfully"
        );

        alert.showAndWait();



    }catch(Exception e){

        e.printStackTrace();

    }
    }
    private void generateBillNumber() {

    try {

        InvoiceService service = new InvoiceService();

        int nextId = service.getNextInvoiceId();

        billno.setText("INV-" + String.format("%04d", nextId));

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    private void loadUsedSpareParts() {

    try {

        UsedSparePartService service = new UsedSparePartService();

        ObservableList<UsedSparePartModel> list =
                service.getUsedPartsByJobCard(jobCard.getJobCardId());

        tblSpareParts.setItems(list);

        calculateSpareTotal();

    } catch (Exception e) {

        e.printStackTrace();

    }

}
   private void calculateSpareTotal(){


    double total = 0;


    for(UsedSparePartModel part : tblSpareParts.getItems()){


        total += part.getTotalPrice();


    }


    txtTotalSpareParts.setText(
            String.valueOf(total)
    );


}
   private void calculateGrandTotal(){

    double spare = 0;

    double labour = 0;



    if(!txtTotalSpareParts.getText().isEmpty()){

        spare =
        Double.parseDouble(
                txtTotalSpareParts.getText()
        );

    }



    if(!txtLabourCharge.getText().isEmpty()){

        labour =
        Double.parseDouble(
                txtLabourCharge.getText()
        );

    }



    double total = spare + labour;


    grandtotal.setText(
            String.valueOf(total)
    );

}

    public void setInvoice(InvoiceModel invoice) throws ClassNotFoundException, SQLException {

    billno.setText(invoice.getInvoiceNo());

    billdate.setText(invoice.getBillDate().toString());

    jobcardref.setText(String.valueOf(invoice.getJobCardId()));

    cusname.setText(invoice.getCustomerName());

    bknum.setText(invoice.getBikeNumber());

    txtTotalSpareParts.setText(
            String.valueOf(invoice.getSpareTotal()));

    txtLabourCharge.setText(
            String.valueOf(invoice.getLabourCharge()));

    grandtotal.setText(
            String.valueOf(invoice.getGrandTotal()));

    cmbPaymentMode.setValue(invoice.getPaymentMode());

     UsedSparePartService service = new UsedSparePartService();

ObservableList<UsedSparePartModel> list =
        service.getUsedPartsByJobCard(invoice.getJobCardId());

tblSpareParts.setItems(list);
calculateSpareTotal();

calculateGrandTotal();
btnSave.setDisable(true);
} 
 
    
}
    

