package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Hall;
import utils.DialogManager;

public class EditHallController {

    @FXML
    private TextField txtCapacity;

    @FXML
    private ComboBox<Integer> comboBoxCinema;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private Hall hall;

    private boolean saveClicked = false;

    public Hall getHall() {
        return hall;
    }

    public void setHallAdd(Hall hall) {
        saveClicked = false;
        this.hall = hall;
        txtCapacity.setText("");
    }

    public void setHallEdit(Hall hall) {
        saveClicked = false;
        this.hall = hall;
        txtCapacity.setText((Integer.toString(hall.getCapacity())));
        comboBoxCinema.getSelectionModel().select(new Integer(hall.getCinema_id()));
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
        hall.setCapacity(Integer.parseInt(txtCapacity.getText()));
        hall.setCinema_id(comboBoxCinema.getValue());
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (txtCapacity.getText().trim().length() == 0
                || !txtCapacity.getText().matches("\\d+")
                || comboBoxCinema.getSelectionModel().isEmpty()){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите корректные данные!");
            return false;
        }

        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public ComboBox<Integer> getComboBoxCinema() {
        return comboBoxCinema;
    }

}
