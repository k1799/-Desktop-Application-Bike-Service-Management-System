/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SparePartsPoPUp;

import Model.SparePartsModel;
import Service.SparePartsService;
import SpareParts.SparePartsController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KANNAN G
 */
public class SparePartsPoPUpController implements Initializable {

    @FXML
    private StackPane popupRoot;
    @FXML
    private TextField pid;
    @FXML
    private TextField pnum;
    @FXML
    private TextField pname;
    @FXML
    private ComboBox<String> pcategory;
    @FXML
    private TextField pbrand;
    @FXML
    private TextField pcompatiblevechicle;
    @FXML
    private TextField pcostprice;
    @FXML
    private TextField pseelingprice;
    @FXML
    private TextField popenqty;
    @FXML
    private TextField pminstockalert;
    @FXML
    private TextField prackarea;
    @FXML
    private TextField pnotes;
    private SparePartsModel sparePart;
    private SparePartsController sparePartsController;
    @FXML
    private Button btnSave;
    @FXML
    private TextField pcurrentstock;
    
    

public void setSparePart(SparePartsModel sparePart) {

    this.sparePart = sparePart;

    pnum.setText(sparePart.getPartNumber());
    pname.setText(sparePart.getPartName());
    pcategory.setValue(sparePart.getCategory());
    pbrand.setText(sparePart.getBrand());
    pcompatiblevechicle.setText(sparePart.getCompatibleVehicle());

    pcostprice.setText(String.valueOf(sparePart.getCostPrice()));
    pseelingprice.setText(String.valueOf(sparePart.getSellingPrice()));

    popenqty.setText(String.valueOf(sparePart.getOpeningQuantity()));
    
    pminstockalert.setText(String.valueOf(sparePart.getMinStock()));
    pcurrentstock.setText(String.valueOf(sparePart.getCurrentStock()));
    prackarea.setText(sparePart.getRackLocation());
    pnotes.setText(sparePart.getNotes());

    // Opening Quantity edit panna koodadhu
    popenqty.setDisable(true);
}

    public void setSparePartsController(SparePartsController sparePartsController) {

        this.sparePartsController = sparePartsController;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      pcategory.getItems().addAll(
            "Engine",
            "Brake",
            "Electrical",
            "Tyre",
            "Oil",
            "Battery",
            "Chain Kit",
            "Body Parts",
            "Accessories",
            "Others"
    );
    }    

    @FXML
    private void cancel(ActionEvent event) {
         ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot); 
    }

    @FXML
    private void save(ActionEvent event) throws ClassNotFoundException, SQLException {
    if (sparePart == null) {
        sparePart = new SparePartsModel();
    }
    sparePart.setPartNumber(pnum.getText());
    sparePart.setPartName(pname.getText());
    sparePart.setCategory((String) pcategory.getValue());
    sparePart.setBrand(pbrand.getText());
    sparePart.setCompatibleVehicle(pcompatiblevechicle.getText());

    sparePart.setCostPrice(Double.parseDouble(pcostprice.getText()));
    sparePart.setSellingPrice(Double.parseDouble(pseelingprice.getText()));

    sparePart.setOpeningQuantity(Integer.parseInt(popenqty.getText()));
    sparePart.setCurrentStock(Integer.parseInt(pcurrentstock.getText()));
    sparePart.setMinStock(Integer.parseInt(pminstockalert.getText()));

    sparePart.setRackLocation(prackarea.getText());
    sparePart.setNotes(pnotes.getText());

    SparePartsService service = new SparePartsService();
    if (sparePart.getPartId() == 0) {

        service.saveSparePart(sparePart);

    } else {

        service.updateSparePart(sparePart);

    }

    sparePartsController.refreshTable();

    ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot);
        }
    public void setViewMode() {

    pnum.setDisable(true);
    pname.setDisable(true);
    pcategory.setDisable(true);
    pbrand.setDisable(true);
    pcompatiblevechicle.setDisable(true);
    pcostprice.setDisable(true);
    pseelingprice.setDisable(true);
    popenqty.setDisable(true);
    pminstockalert.setDisable(true);
    prackarea.setDisable(true);
    pnotes.setDisable(true);

    btnSave.setDisable(true);

}
    }
    

