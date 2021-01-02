package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.District;
import utils.DialogManager;

public class EditDistrictController {

    @FXML
    private TextField txtName;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private District district;

    private boolean saveClicked = false;

    public void setDistrictAdd(District district) {
        saveClicked = false;
        this.district = district;
        txtName.setText("");
    }

    public void setDistrictEdit(District district) {
        saveClicked = false;
        this.district = district;
        txtName.setText(district.getDistrict_name());
    }

    public District getDistrict() {
        return district;
    }

    @FXML
    void actionClose(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (!checkValues()){
            return;
        }
        district.setDistrict_name(txtName.getText().trim());
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length() == 0){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите корректные данные!");
            return false;
        }

        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

}
