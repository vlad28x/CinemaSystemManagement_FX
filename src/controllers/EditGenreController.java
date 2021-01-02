package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.District;
import objects.Genre;
import utils.DialogManager;

public class EditGenreController {

    @FXML
    private TextField txtName;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private Genre genre;

    private boolean saveClicked = false;

    public void setGenreAdd(Genre genre) {
        saveClicked = false;
        this.genre = genre;
        txtName.setText("");
    }

    public void setGenreEdit(Genre genre) {
        saveClicked = false;
        this.genre = genre;
        txtName.setText(genre.getGenre_name());
    }

    public Genre getGenre() {
        return genre;
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
        genre.setGenre_name(txtName.getText().trim());
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
