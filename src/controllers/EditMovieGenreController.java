package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import objects.MovieGenre;
import utils.DialogManager;

public class EditMovieGenreController {

    @FXML
    private ComboBox<Integer> comboBoxMovie;

    @FXML
    private ComboBox<Integer> comboBoxGenre;

    @FXML
    private Button btnOK;

    @FXML
    private Button btbCancel;

    private MovieGenre movieGenre;

    private boolean saveClicked = false;

    private int movie_id;

    private int genre_id;

    public int getMovie_id() {
        return movie_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenreAdd(MovieGenre movieGenre) {
        saveClicked = false;
        this.movieGenre = movieGenre;
    }

    public void setMovieGenreEdit(MovieGenre movieGenre) {
        saveClicked = false;
        this.movieGenre = movieGenre;
        this.movie_id = movieGenre.getMovie_id();
        this.genre_id = movieGenre.getGenre_id();
        comboBoxMovie.getSelectionModel().select(new Integer(movieGenre.getMovie_id()));
        comboBoxGenre.getSelectionModel().select(new Integer(movieGenre.getGenre_id()));
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
        movieGenre.setMovie_id(comboBoxMovie.getValue());
        movieGenre.setGenre_id(comboBoxGenre.getValue());
        saveClicked = true;
        actionClose(event);
    }

    private boolean checkValues() {
        if (comboBoxMovie.getSelectionModel().isEmpty()
                || comboBoxGenre.getSelectionModel().isEmpty()){
            DialogManager.showInfoDialog("Ошибка ввода", "Введите выбирете значения из списка!");
            return false;
        }
        if(comboBoxMovie.getValue() == movie_id && comboBoxGenre.getValue() == genre_id) return true;
        if (MainController.getMovieGenreImpl().check(comboBoxMovie.getValue(), comboBoxGenre.getValue())) {
            DialogManager.showErrorDialog("Ошибка", "Нарушено ограничение уникальности!");
            return false;
        }
        return true;
    }

    public ComboBox<Integer> getComboBoxMovie() {
        return comboBoxMovie;
    }

    public ComboBox<Integer> getComboBoxGenre() {
        return comboBoxGenre;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

}
