package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Movie;
import utils.DialogManager;

public class EditMovieController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRelease;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtRating;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private Movie movie;

    private boolean saveClicked = false;

    public Movie getMovie() {
        return movie;
    }

    public void setMovieAdd(Movie movie) {
        saveClicked = false;
        this.movie = movie;
        txtName.setText("");
        txtRelease.setText("");
        txtDuration.setText("");
        txtRating.setText("");
    }

    public void setMovieEdit(Movie movie) {
        saveClicked = false;
        this.movie = movie;
        txtName.setText(movie.getMovie_name());
        txtRelease.setText(Integer.toString(movie.getRelease()));
        txtDuration.setText(Integer.toString(movie.getDuration()));
        txtRating.setText(Float.toString(movie.getRating()));
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
        movie.setMovie_name(txtName.getText());
        movie.setRelease(Integer.parseInt(txtRelease.getText()));
        movie.setDuration(Integer.parseInt(txtDuration.getText()));
        movie.setRating(Float.parseFloat(txtRating.getText()));
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length() == 0
                || !txtDuration.getText().matches("\\d+")
                || !txtRelease.getText().matches("\\d+")
                || !txtRating.getText().matches("\\d+(\\.\\d+)?")){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите корректные данные!");
            return false;
        }
        int release = Integer.parseInt(txtRelease.getText());
        float rating = Float.parseFloat(txtRating.getText());
        if (rating < 0 || rating > 10 || release < 2000) {
            DialogManager.showErrorDialog("Ошибка", "Нарушена целостность данных!");
            return false;
        }

        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

}
