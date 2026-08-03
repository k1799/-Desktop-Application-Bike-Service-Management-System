/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package MechanicPoPUp;

import Mechanic.MechanicController;
import Model.MechanicModel;
import Service.MechanicService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KANNAN G
 */
public class MechanicPoPUpController implements Initializable {

    @FXML
    private StackPane popupRoot;
    @FXML
    private TextField mid;
    @FXML
    private TextField mname;
    @FXML
    private TextField mphone;
    @FXML
    private TextField memail;
    @FXML
    private TextField midproof;
    @FXML
    private ComboBox<String> mspecification;
    @FXML
    private ComboBox<String> mstatus;
    @FXML
    private TextField mexp;
    @FXML
    private TextArea maddress;
    private MechanicController mechanicController;
    private MechanicModel mechanic;
    @FXML
    private Button btnSave;
    
    public void setMechanic(MechanicModel mechanic) {

    this.mechanic = mechanic;

    mid.setText(String.valueOf(mechanic.getMechanicId()));
    mname.setText(mechanic.getMechanicName());
    mphone.setText(mechanic.getPhone());
    memail.setText(mechanic.getEmail());
    midproof.setText(mechanic.getIdProof());
    mspecification.setValue(mechanic.getSpecialization());
    mexp.setText(String.valueOf(mechanic.getExperience()));
    maddress.setText(mechanic.getAddress());
    mstatus.setValue(mechanic.getStatus());

}
    public void setMechanicController(MechanicController mechanicController) {
    this.mechanicController = mechanicController;
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mspecification.getItems().addAll(
            "Engine",
            "Electrical",
            "General Service",
            "Brake",
            "Suspension",
            "Tyre",
            "Body Work"
    );

    mstatus.getItems().addAll(
            "Available",
            "UnAvailable"        
    );
    }    

    @FXML
    private void cansel(ActionEvent event) {
        ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot); 
    }

    @FXML
    private void save(ActionEvent event) throws ClassNotFoundException, SQLException {
 
     if (mechanic == null) {
      mechanic = new MechanicModel();
    }

    mechanic.setMechanicName(mname.getText());
    mechanic.setPhone(mphone.getText());
    mechanic.setEmail(memail.getText());
    mechanic.setIdProof(midproof.getText());
    mechanic.setSpecialization(mspecification.getValue());
    mechanic.setExperience(Integer.parseInt(mexp.getText()));
    mechanic.setAddress(maddress.getText());
    mechanic.setStatus(mstatus.getValue());

    MechanicService service = new MechanicService();

    if (mechanic.getMechanicId() == 0) {

        service.saveMechanic(mechanic);

    } else {

        service.updateMechanics(mechanic);

    }

    mechanicController.refreshTable();
        
    ((StackPane) popupRoot.getParent()).getChildren().remove(popupRoot); 
    }
    public void setViewMode() {

    mname.setDisable(true);
    mphone.setDisable(true);
    memail.setDisable(true);
    midproof.setDisable(true);
    mspecification.setDisable(true);
    mexp.setDisable(true);
    maddress.setDisable(true);
    mstatus.setDisable(true);

    btnSave.setDisable(true);

}
    
}
    

