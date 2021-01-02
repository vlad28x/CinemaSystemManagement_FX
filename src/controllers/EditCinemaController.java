package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Cinema;
import utils.DialogManager;

public class EditCinemaController {

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<Integer> comboBoxDistrict;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private Cinema cinema;

    private boolean saveClicked = false;

    public void setCinemaAdd(Cinema cinema) {
        saveClicked = false;
        this.cinema = cinema;
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
    }

    public void setCinemaEdit(Cinema cinema) {
        saveClicked = false;
        this.cinema = cinema;
        txtName.setText(cinema.getCinema_name());
        txtAddress.setText(cinema.getAddress());
        txtPhone.setText(cinema.getPhone_number());
        comboBoxDistrict.getSelectionModel().select(new Integer(cinema.getDistrict_id()));
    }

    public Cinema getCinema() {
        return cinema;
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
        cinema.setCinema_name(txtName.getText().trim());
        cinema.setAddress(txtAddress.getText().trim());
        cinema.setPhone_number(txtPhone.getText().trim());
        cinema.setDistrict_id(comboBoxDistrict.getValue());
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length() == 0
                || txtAddress.getText().trim().length() == 0
                || comboBoxDistrict.getSelectionModel().isEmpty()
                || (!(txtPhone.getText().trim().length() == 0)
                && !(txtPhone.getText().trim().matches("([+]?\\d[ ]?[(]?\\d{3}[)]?[ ]?\\d{2,3}[- ]?\\d{2}[- ]?\\d{2})")))){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите корректные данные!");
            return false;
        }

        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public ComboBox<Integer> getComboBoxDistrict() {
        return comboBoxDistrict;
    }
}
