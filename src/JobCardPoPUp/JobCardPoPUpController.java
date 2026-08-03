/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package JobCardPoPUp;

import java.sql.Connection;
import Dao.SpartPartsDao;
import Database.Database;
import JobCard.JobCardController;
import Model.CustomerModel;
import Model.JobCardModel;
import Model.SparePartsModel;
import Service.CustomerService;
import Service.JobCardService;
import Service.MechanicService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Model.UsedSparePartModel;
import Service.SparePartsService;
import Service.UsedSparePartService;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class JobCardPoPUpController implements Initializable {

    @FXML
    private StackPane popupRoot;
    @FXML
    private TextField jid;
    @FXML
    private TextField jdatetime;
    @FXML
    private TextField jcustomer;
    @FXML
    private ComboBox<String> jbknum;
    @FXML
    private TextField jbrand;
    @FXML
    private TextField jmodel;
    @FXML
    private ComboBox<String> jservicetype;
    @FXML
    private ComboBox<String> jfuelindi;
    @FXML
    private ComboBox<String> jassianmechanic;
    @FXML
    private TextField jappcost;
    @FXML
    private TextArea jcomplaints;
    @FXML
    private DatePicker jdelivarydate;
    @FXML
    private TextField jcurrentkm;
    private JobCardController jobCardController;
    private JobCardModel jobCard;
    @FXML
    private ComboBox<String> cmbSparePart;
    @FXML
    private TextField txtAvailableStock;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Button btnAdd;
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
    private TableColumn<UsedSparePartModel,Void> caction;
    @FXML
    private TextField sparetotal;
    private int selectedPartId;
    private UsedSparePartModel editingPart = null;
    private ObservableList<UsedSparePartModel> usedParts =
        FXCollections.observableArrayList();
   
    
    
//   public void setJobCard(JobCardModel jobCard) {
//
//    this.jobCard = jobCard;
//
//    jid.setText(String.valueOf(jobCard.getJobCardId()));
//
//    jcustomer.setText(jobCard.getCustomerName());
//
//    jbknum.setValue(jobCard.getBikeNumber());
//
//    jbrand.setText(jobCard.getBrand());
//
//    jmodel.setText(jobCard.getModel());
//
//    jservicetype.setValue(jobCard.getServiceType());
//
//    jfuelindi.setValue(jobCard.getFuelLevel());
//
//    jassianmechanic.setValue(jobCard.getMechanicName());
//
//    jcurrentkm.setText(String.valueOf(jobCard.getCurrentKm()));
//
//    jappcost.setText(String.valueOf(jobCard.getApproximateCost()));
//
//    jcomplaints.setText(jobCard.getComplaint());
//
//    jdelivarydate.setValue(jobCard.getDeliveryDate());
//
//}
    
    public void setJobCard(JobCardModel jobCard) {

    this.jobCard = jobCard;

    jid.setText(String.valueOf(jobCard.getJobCardId()));
    jcustomer.setText(jobCard.getCustomerName());
    jbknum.setValue(jobCard.getBikeNumber());
    jbrand.setText(jobCard.getBrand());
    jmodel.setText(jobCard.getModel());
    jservicetype.setValue(jobCard.getServiceType());
    jfuelindi.setValue(jobCard.getFuelLevel());
    jassianmechanic.setValue(jobCard.getMechanicName());
    jcurrentkm.setText(String.valueOf(jobCard.getCurrentKm()));
    jappcost.setText(String.valueOf(jobCard.getApproximateCost()));
    jcomplaints.setText(jobCard.getComplaint());
    jdelivarydate.setValue(jobCard.getDeliveryDate());

    try {

    UsedSparePartService service = new UsedSparePartService();

    ObservableList<UsedSparePartModel> list =
            service.getUsedPartsByJobCard(jobCard.getJobCardId());

    usedParts.clear();

    usedParts.addAll(list);

    tblSpareParts.setItems(usedParts);
    calculateGrandTotal();
} catch (Exception e) {

    e.printStackTrace();

}

}
   
    public void setJobCardController(JobCardController jobCardController) {

    this.jobCardController = jobCardController;

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    loadBikeNumbers();

    loadMechanics();

    loadServiceType();

    loadFuelLevel();
      
    colPartName.setCellValueFactory(new PropertyValueFactory<>("partName"));

colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

tblSpareParts.setItems(usedParts);
loadSpareParts();
addButtonToTable();
    }    

    @FXML
    private void cancel(ActionEvent event) {
    ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);
    }

    @FXML
    private void save(ActionEvent event) {
   System.out.println("Save Button Clicked");
    try {

        JobCardService service = new JobCardService();

        if (jobCard == null) {

            jobCard = new JobCardModel();

        }
        System.out.println("1");
        CustomerService customerService = new CustomerService();
        System.out.println("2");
        CustomerModel customer =customerService.getCustomerByBikeNumber(jbknum.getValue());
        System.out.println("3");
        MechanicService mechanicService = new MechanicService();
        System.out.println("4");
        System.out.println("Selected Mechanic = " + jassianmechanic.getValue());
        int mechanicId = mechanicService.getMechanicIdByName(jassianmechanic.getValue());

        jobCard.setCustomerId(customer.getCustomerId());

        jobCard.setMechanicId(mechanicId);

        jobCard.setServiceType(jservicetype.getValue());

        jobCard.setFuelLevel(jfuelindi.getValue());

        jobCard.setCurrentKm(Integer.parseInt(jcurrentkm.getText()));

        jobCard.setComplaint(jcomplaints.getText());

        if (jappcost.getText().trim().isEmpty()) {

    System.out.println("Approximate Cost is Empty");

    return;
}
jobCard.setApproximateCost(
        Double.parseDouble(jappcost.getText()));

        jobCard.setDeliveryDate(jdelivarydate.getValue());
        jobCard.setStatus("Pending");
        
        if (jobCard.getJobCardId() == 0) {
            System.out.println("5");
            service.saveJobCard(jobCard);
            System.out.println("6");
           int jobCardId = service.getLastJobCardId();
         System.out.println("Job Card Id = " + jobCardId);
         System.out.println("Used Parts Size = " + usedParts.size());
         UsedSparePartService spareService =new UsedSparePartService();

    SparePartsService stockService =new SparePartsService();
System.out.println("Used Parts Size = " + usedParts.size());
    for (UsedSparePartModel part : usedParts) {
        System.out.println(part.getPartName());
        part.setJobCardId(jobCardId);

        spareService.saveUsedSparePart(part);

        stockService.updateStock(part.getPartId(), part.getQuantity());

    }

            
        } else {

    service.updateJobCard(jobCard);

    UsedSparePartService spareService = new UsedSparePartService();

    SparePartsService stockService = new SparePartsService();

    
    ObservableList<UsedSparePartModel> oldParts =
            spareService.getUsedPartsByJobCard(jobCard.getJobCardId());

   
    for (UsedSparePartModel part : oldParts) {

        stockService.increaseStock(part.getPartId(),
                                   part.getQuantity());

    }

    
    spareService.deleteByJobCardId(jobCard.getJobCardId());

    
    for (UsedSparePartModel part : usedParts) {

        part.setJobCardId(jobCard.getJobCardId());

        spareService.saveUsedSparePart(part);

        stockService.updateStock(part.getPartId(),
                                 part.getQuantity());

    }

}

        jobCardController.refreshTable();

        ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);

    } catch (Exception e) {

        e.printStackTrace();

    }

    }    
       
    private void loadServiceType() {

    jservicetype.getItems().addAll(

            "General Service",
            "Oil Change",
            "Engine Repair",
            "Brake Service",
            "Chain Service",
            "Water Wash",
            "Electrical Work",
            "Tyre Replacement"

    );

}
    private void loadFuelLevel() {

    jfuelindi.getItems().addAll(

            "Empty",
            "1/4",
            "Half",
            "3/4",
            "Full"

    );

}
    private void loadBikeNumbers() {

    try {

        CustomerService service = new CustomerService();

        jbknum.setItems(service.getBikeNumbers());

    } catch (Exception e) {

        e.printStackTrace();

    }

}
    private void loadMechanics() {

    try {

        MechanicService service = new MechanicService();

        jassianmechanic.setItems(service.getMechanicNames());

    } catch (Exception e) {

        e.printStackTrace();

    }

}
@FXML
private void bikeNumberSelected(ActionEvent event) {

    
    try {

        CustomerService service = new CustomerService();

        CustomerModel customer = service.getCustomerByBikeNumber(jbknum.getValue());

        if (customer != null) {

            jcustomer.setText(customer.getCustomerName());

            jbrand.setText(customer.getBrand());

            jmodel.setText(customer.getModel());

            jcurrentkm.setText(String.valueOf(customer.getCurrentKm()));

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}

    @FXML
    private void handleAddPart(ActionEvent event) {
        if (editingPart != null) {

    editingPart.setPartId(selectedPartId);

    editingPart.setPartName(cmbSparePart.getValue());

    editingPart.setQuantity(Integer.parseInt(txtQuantity.getText()));

    editingPart.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));

    editingPart.setTotalPrice(
            editingPart.getQuantity() * editingPart.getUnitPrice());

    tblSpareParts.refresh();

    calculateGrandTotal();

    editingPart = null;

    btnAdd.setText("Add");

    cmbSparePart.setValue(null);

    txtAvailableStock.clear();

    txtUnitPrice.clear();

    txtQuantity.clear();

    return;
}
        for (UsedSparePartModel p : usedParts) {

   
    if (editingPart != null && p == editingPart) {
        continue;
    }

    if (p.getPartId() == selectedPartId) {

        System.out.println("Already Added");

        return;

    }

}
         try {
             int qty = Integer.parseInt(txtQuantity.getText());

int stock = Integer.parseInt(txtAvailableStock.getText());

if (qty > stock) {

    System.out.println("Stock not available");

    return;

}

        UsedSparePartModel part = new UsedSparePartModel();

       
        part.setPartId(selectedPartId);

        part.setPartName(cmbSparePart.getValue());

        part.setQuantity(Integer.parseInt(txtQuantity.getText()));

        part.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));

        double total = part.getQuantity() * part.getUnitPrice();

        part.setTotalPrice(total);

        usedParts.add(part);
     calculateGrandTotal();
      cmbSparePart.setValue(null);

        txtAvailableStock.clear();

        txtUnitPrice.clear();

        txtQuantity.clear();
        
    } catch (Exception e) {

        e.printStackTrace();

    }
    }
    private void calculateGrandTotal() {

    double total = 0;

    for (UsedSparePartModel part : usedParts) {
        System.out.println(part.getTotalPrice());
        total += part.getTotalPrice();

    }
    System.out.println("Grand Total = " + total);
    sparetotal.setText(String.valueOf(total));

}
    private void loadSpareParts() {

    try {

        SparePartsService service = new SparePartsService();

        cmbSparePart.setItems(service.getPartNames());

    } catch (Exception e) {

        e.printStackTrace();

    }

}
 
    
    @FXML
    private void sparePartSelected(ActionEvent event) {
System.out.println("Spare Part Selected");
    try {

        SparePartsService service = new SparePartsService();

        SparePartsModel part =
                service.getPartByName(cmbSparePart.getValue());
        
       
        
        if (part != null) {

            selectedPartId = part.getPartId();

            txtAvailableStock.setText(
                    String.valueOf(part.getCurrentStock()));

            txtUnitPrice.setText(
                    String.valueOf(part.getSellingPrice()));

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}   
private void addButtonToTable() {

    caction.setCellFactory(param -> new TableCell<UsedSparePartModel, Void>() {
        
        private final Button editBtn = new Button("Edit");
        private final Button deleteBtn = new Button("Delete");

        {
            
           editBtn.setOnAction(event -> {

    UsedSparePartModel part =
            getTableView().getItems().get(getIndex());

    editingPart = part;

    cmbSparePart.setValue(part.getPartName());
  try {

        SparePartsService service = new SparePartsService();

        SparePartsModel sparePart =
                service.getPartByName(part.getPartName());

        if (sparePart != null) {

            selectedPartId = sparePart.getPartId();

            txtAvailableStock.setText(
                    String.valueOf(sparePart.getCurrentStock()));
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    txtUnitPrice.setText(
            String.valueOf(part.getUnitPrice()));

    txtQuantity.setText(
            String.valueOf(part.getQuantity()));

    btnAdd.setText("Update");

});
    
            deleteBtn.setOnAction(event -> {

                UsedSparePartModel part =
                        getTableView().getItems().get(getIndex());

                usedParts.remove(part);

                calculateGrandTotal();

            });

        }

        @Override
        protected void updateItem(Void item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {

                setGraphic(null);

            } else {

                HBox box = new HBox(5);

                box.getChildren().addAll(editBtn, deleteBtn);

                setGraphic(box);

            }

        }

    });

}
public void disableEditing() {

    jbknum.setDisable(true);
    jservicetype.setDisable(true);
    jfuelindi.setDisable(true);
    jassianmechanic.setDisable(true);
    jcurrentkm.setDisable(true);
    jappcost.setDisable(true);
    jcomplaints.setDisable(true);
    jdelivarydate.setDisable(true);

    cmbSparePart.setDisable(true);
    txtAvailableStock.setDisable(true);
    txtUnitPrice.setDisable(true);
    txtQuantity.setDisable(true);

    btnAdd.setDisable(true);
}
       
    }