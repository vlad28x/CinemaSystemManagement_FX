package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Session;
import utils.DialogManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditSessionController {

    @FXML
    private TextField txtDateTime;

    @FXML
    private TextField txtPlaces;

    @FXML
    private ComboBox<Integer> comboBoxHall;

    @FXML
    private ComboBox<Integer> comboBoxMovie;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private Session session;

    private boolean saveClicked = false;

    public void setSessionAdd(Session session) {
        saveClicked = false;
        this.session = session;
        txtDateTime.setText("");
        txtPlaces.setText("");
    }

    public void setSessionEdit(Session session) {
        saveClicked = false;
        this.session = session;
        txtDateTime.setText(session.getDate_time());
        txtPlaces.setText(Integer.toString(session.getNumber_free_places()));
        comboBoxHall.getSelectionModel().select(new Integer(session.getHall_id()));
        comboBoxMovie.getSelectionModel().select(new Integer(session.getMovie_id()));
    }

    public Session getSession() {
        return session;
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
        session.setDate_time(txtDateTime.getText().trim());
        session.setNumber_free_places(Integer.parseInt(txtPlaces.getText().trim()));
        session.setHall_id(comboBoxHall.getValue());
        session.setMovie_id(comboBoxMovie.getValue());
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (!dateValidate(txtDateTime.getText())
                || !txtPlaces.getText().trim().matches("\\d+")
                || comboBoxHall.getSelectionModel().isEmpty()
                || comboBoxMovie.getSelectionModel().isEmpty()){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите корректные данные!");
            return false;
        }
        if(MainController.getSessionImpl().check(comboBoxHall.getValue(), Integer.parseInt(txtPlaces.getText()))) {
            DialogManager.showInfoDialog("Ошибка ввода", "Свободных месте не может быть больше вместимости зала!");
            return false;
        }
        return true;
    }

    private static boolean dateValidate(String inputDate) {
        try {
            String datePattern = "yyyy-MM-dd HH:mm";
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            Date date = sdf.parse(inputDate);
            String formattedDate = sdf.format(date);
            if (inputDate.equals(formattedDate)) {
                return true;
            }
        } catch (ParseException ex) {
            return false;
        }
        return false;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public ComboBox<Integer> getComboBoxHall() {
        return comboBoxHall;
    }

    public ComboBox<Integer> getComboBoxMovie() {
        return comboBoxMovie;
    }

}
