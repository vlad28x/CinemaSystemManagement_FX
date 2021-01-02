package controllers;

import impls.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.*;
import utils.DialogManager;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class MainController extends Observable {

    private static final String FXML_EDIT_DISTRICT = "../fxml/editDistrict.fxml";
    private static final String FXML_EDIT_CINEMA = "../fxml/editCinema.fxml";
    private static final String FXML_EDIT_HALL = "../fxml/editHall.fxml";
    private static final String FXML_EDIT_GENRE = "../fxml/editGenre.fxml";
    private static final String FXML_EDIT_MOVIE = "../fxml/editMovie.fxml";
    private static final String FXML_EDIT_MOVIE_GENRE = "../fxml/editMovieGenre.fxml";
    private static final String FXML_EDIT_SESSION = "../fxml/editSession.fxml";
    private final DBDistrict districtImpl = new DBDistrict();
    private final DBCinema cinemaImpl = new DBCinema();
    private final DBHall hallImpl = new DBHall();
    private final DBGenre genreImpl = new DBGenre();
    private final DBMovie movieImpl = new DBMovie();
    private static final DBMovieGenre movieGenreImpl = new DBMovieGenre();
    private static final DBSession sessionImpl = new DBSession();
    private final DBQuery1 query1DB = new DBQuery1();
    private final DBQuery2 query2DB = new DBQuery2();
    private final DBQuery3 query3DB = new DBQuery3();
    private final DBQuery4 query4DB = new DBQuery4();
    private final DBQuery5 query5DB = new DBQuery5();

    public static DBMovieGenre getMovieGenreImpl() {
        return movieGenreImpl;
    }

    public static DBSession getSessionImpl() {
        return sessionImpl;
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<District> tableDistrict;

    @FXML
    private TableColumn<District, Integer> columnDistrictID;

    @FXML
    private TableColumn<District, String> columnDistrictName;

    @FXML
    private Button btnAddDistrict;

    @FXML
    private Button btnEditDistrict;

    @FXML
    private Button btnDeleteDistrict;

    @FXML
    private TextField txtSearchDistrict;

    @FXML
    private Button btnSearchDistrict;

    @FXML
    private TableView<Cinema> tableCinema;

    @FXML
    private TableColumn<Cinema, Integer> columnCinemaID;

    @FXML
    private TableColumn<Cinema, String> columnCinemaName;

    @FXML
    private TableColumn<Cinema, Integer> columnCinemaDistrict;

    @FXML
    private TableColumn<Cinema, String> columnCinemaAddress;

    @FXML
    private TableColumn<Cinema, String> columnCinemaPhone;

    @FXML
    private Button btnAddCinema;

    @FXML
    private Button btnEditCinema;

    @FXML
    private Button btnDeleteCinema;

    @FXML
    private TextField txtSearchCinema;

    @FXML
    private Button btnSearchCinema;

    @FXML
    private TableView<Hall> tableHall;

    @FXML
    private TableColumn<Hall, Integer> columnHallID;

    @FXML
    private TableColumn<Hall, Integer> columnHallCapacity;

    @FXML
    private TableColumn<Hall, Integer> columnHallCinema;

    @FXML
    private Button btnAddHall;

    @FXML
    private Button btnEditHall;

    @FXML
    private Button btnDeleteHall;

    @FXML
    private TextField txtSearchHall;

    @FXML
    private Button btnSearchHall;

    @FXML
    private TableView<Session> tableSession;

    @FXML
    private TableColumn<Session, Integer> columnSessionID;

    @FXML
    private TableColumn<Session, String> columnSessionDate;

    @FXML
    private TableColumn<Session, Integer> columnSessionPlaces;

    @FXML
    private TableColumn<Session, Integer> columnSessionHall;

    @FXML
    private TableColumn<Session, Integer> columnSessionMovie;

    @FXML
    private Button btnAddSession;

    @FXML
    private Button btnEditSession;

    @FXML
    private Button btnDeleteSession;

    @FXML
    private TextField txtSearchSession;

    @FXML
    private Button btnSearchSession;

    @FXML
    private TableView<Movie> tableMovie;

    @FXML
    private TableColumn<Movie, Integer> columnMovieID;

    @FXML
    private TableColumn<Movie, String> columnMovieName;

    @FXML
    private TableColumn<Movie, Integer> columnMovieRelease;

    @FXML
    private TableColumn<Movie, Integer> columnMovieDuration;

    @FXML
    private TableColumn<Movie, Float> columnMovieRating;

    @FXML
    private Button btnAddMovie;

    @FXML
    private Button btnEditMovie;

    @FXML
    private Button btnDeleteMovie;

    @FXML
    private TextField txtSearchMovie;

    @FXML
    private Button btnSearchMovie;

    @FXML
    private TableView<MovieGenre> tableMovieGenre;

    @FXML
    private TableColumn<MovieGenre, Integer> columnMovieGenreMovie;

    @FXML
    private TableColumn<MovieGenre, Integer> columnMovieGenreGenre;

    @FXML
    private Button btnAddMovieGenre;

    @FXML
    private Button btnEditMovieGenre;

    @FXML
    private Button btnDeleteMovieGenre;

    @FXML
    private TextField txtSearchMovieGenre;

    @FXML
    private Button btnSearchMovieGenre;

    @FXML
    private TableView<Genre> tableGenre;

    @FXML
    private TableColumn<Genre, Integer> columnGenreID;

    @FXML
    private TableColumn<Genre, String> columnGenreName;

    @FXML
    private Button btnAddGenre;

    @FXML
    private Button btnEditGenre;

    @FXML
    private Button btnDeleteGenre;

    @FXML
    private TextField txtSearchGenre;

    @FXML
    private Button btnSearchGenre;

    @FXML
    private TableView<Query1> tableQuery1;

    @FXML
    private TableColumn<Query1, String> columnQuery1;

    @FXML
    private ComboBox<Integer> comboBoxQuery1;

    @FXML
    private Tab tabQuery2;

    @FXML
    private TableView<Query2> tableQuery2;

    @FXML
    private TableColumn<Query2, String> columnQuery2Date;

    @FXML
    private TableColumn<Query2, Integer> columnQuery2Places;

    @FXML
    private ComboBox<Integer> comboBoxQuery2;


    @FXML
    private Tab tabQuery3;

    @FXML
    private TableView<Query3> tableQuery3;

    @FXML
    private TableColumn<Query3, String> columnQuery3;

    @FXML
    private ComboBox<Integer> comboBoxQuery3;

    @FXML
    private Tab tabQuery4;

    @FXML
    private TextField txtQuery4;

    @FXML
    private TableView<Query4> tableQuery4;

    @FXML
    private TableColumn<Query4, String> columnQuery4;

    @FXML
    private Tab tabQuery5;

    @FXML
    private TextField txtQuery5;

    @FXML
    private TableView<Query5> tableQuery5;

    @FXML
    private TableColumn<Query5, String> columnQuery5;


    private final FXMLLoader fxmlLoaderDistrict = new FXMLLoader();

    private Parent fxmlEditDistrict;

    private EditDistrictController editDistrictController;

    private Stage editDistrictStage;

    private final FXMLLoader fxmlLoaderCinema = new FXMLLoader();

    private Parent fxmlEditCinema;

    private EditCinemaController editCinemaController;

    private Stage editCinemaStage;

    private final FXMLLoader fxmlLoaderHall = new FXMLLoader();

    private Parent fxmlEditHall;

    private EditHallController editHallController;

    private Stage editHallStage;

    private final FXMLLoader fxmlLoaderSession = new FXMLLoader();

    private Parent fxmlEditSession;

    private EditSessionController editSessionController;

    private Stage editSessionStage;

    private final FXMLLoader fxmlLoaderMovie = new FXMLLoader();

    private Parent fxmlEditMovie;

    private EditMovieController editMovieController;

    private Stage editMovieStage;

    private final FXMLLoader fxmlLoaderMovieGenre = new FXMLLoader();

    private Parent fxmlEditMovieGenre;

    private EditMovieGenreController editMovieGenreController;

    private Stage editMovieGenreStage;

    private final FXMLLoader fxmlLoaderGenre = new FXMLLoader();

    private Parent fxmlEditGenre;

    private EditGenreController editGenreController;

    private Stage editGenreStage;

    @FXML
    private void initialize() {
        columnDistrictID.setCellValueFactory(new PropertyValueFactory<District, Integer>("district_id"));
        columnDistrictName.setCellValueFactory(new PropertyValueFactory<District, String>("district_name"));
        columnCinemaID.setCellValueFactory(new PropertyValueFactory<Cinema, Integer>("cinema_id"));
        columnCinemaName.setCellValueFactory(new PropertyValueFactory<Cinema, String>("cinema_name"));
        columnCinemaDistrict.setCellValueFactory(new PropertyValueFactory<Cinema, Integer>("district_id"));
        columnCinemaAddress.setCellValueFactory(new PropertyValueFactory<Cinema, String>("address"));
        columnCinemaPhone.setCellValueFactory(new PropertyValueFactory<Cinema, String>("phone_number"));
        columnHallID.setCellValueFactory(new PropertyValueFactory<Hall, Integer>("hall_id"));
        columnHallCapacity.setCellValueFactory(new PropertyValueFactory<Hall, Integer>("capacity"));
        columnHallCinema.setCellValueFactory(new PropertyValueFactory<Hall, Integer>("cinema_id"));
        columnGenreID.setCellValueFactory(new PropertyValueFactory<Genre, Integer>("genre_id"));
        columnGenreName.setCellValueFactory(new PropertyValueFactory<Genre, String>("genre_name"));
        columnMovieID.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movie_id"));
        columnMovieName.setCellValueFactory(new PropertyValueFactory<Movie, String>("movie_name"));
        columnMovieRelease.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("release"));
        columnMovieDuration.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("duration"));
        columnMovieRating.setCellValueFactory(new PropertyValueFactory<Movie, Float>("rating"));
        columnMovieGenreMovie.setCellValueFactory(new PropertyValueFactory<MovieGenre, Integer>("movie_id"));
        columnMovieGenreGenre.setCellValueFactory(new PropertyValueFactory<MovieGenre, Integer>("genre_id"));
        columnSessionID.setCellValueFactory(new PropertyValueFactory<Session, Integer>("session_id"));
        columnSessionDate.setCellValueFactory(new PropertyValueFactory<Session, String>("date_time"));
        columnSessionPlaces.setCellValueFactory(new PropertyValueFactory<Session, Integer>("number_free_places"));
        columnSessionHall.setCellValueFactory(new PropertyValueFactory<Session, Integer>("hall_id"));
        columnSessionMovie.setCellValueFactory(new PropertyValueFactory<Session, Integer>("movie_id"));
        columnQuery1.setCellValueFactory(new PropertyValueFactory<Query1, String>("name"));
        columnQuery2Date.setCellValueFactory(new PropertyValueFactory<Query2, String>("date_time"));
        columnQuery2Places.setCellValueFactory(new PropertyValueFactory<Query2, Integer>("places"));
        columnQuery3.setCellValueFactory(new PropertyValueFactory<Query3, String>("name"));
        columnQuery4.setCellValueFactory(new PropertyValueFactory<Query4, String>("name"));
        columnQuery5.setCellValueFactory(new PropertyValueFactory<Query5, String>("name"));
        //tabPane.setOnMouseClicked(ev -> handleHeader(tabPane));
        initListeners();
        fillTable();
        initQueryObject();
        initLoader();
    }

    private void initQueryObject() {
        setComboboxQuery1();
        setComboboxQuery2();
        setComboboxQuery3();
    }

    private void setComboboxQuery1() {
        comboBoxQuery1.getItems().clear();
        for (District district: districtImpl.getDistrictList()) {
            comboBoxQuery1.getItems().add(district.getDistrict_id());
        }
    }

    private void setComboboxQuery2() {
        comboBoxQuery2.getItems().clear();
        for (Cinema cinema: cinemaImpl.getCinemaList()) {
            comboBoxQuery2.getItems().add(cinema.getCinema_id());
        }
    }

    private void setComboboxQuery3() {
        comboBoxQuery3.getItems().clear();
        for (Genre genre: genreImpl.getGenreList()) {
            comboBoxQuery3.getItems().add(genre.getGenre_id());
        }
    }

    /*private void handleHeader(TabPane tabPane) {
        if(tabPane.getSelectionModel().getSelectedItem().getId() == null) return;

        switch(tabPane.getSelectionModel().getSelectedItem().getId()) {
            case "tabQuery1":
                setComboboxQuery1();
                break;
            case "tabQuery2":
                setComboboxQuery2();
                break;
            case "tabQuery3":
                setComboboxQuery1();
                break;
            case "tabQuery4":
                setComboboxQuery1();
                break;
            case "tabQuery5":
                setComboboxQuery1();
                break;
        }
        System.out.println("do stuff for tab: " + tabPane.getSelectionModel().getSelectedItem().getId());
    }*/

    private void fillTable() {
        ObservableList<District> districtList = districtImpl.findAll();
        tableDistrict.setItems(districtList);
        ObservableList<Cinema> cinemaList = cinemaImpl.findAll();
        tableCinema.setItems(cinemaList);
        ObservableList<Hall> hallList = hallImpl.findAll();
        tableHall.setItems(hallList);
        ObservableList<Genre> genreList = genreImpl.findAll();
        tableGenre.setItems(genreList);
        ObservableList<Movie> movieList = movieImpl.findAll();
        tableMovie.setItems(movieList);
        ObservableList<MovieGenre> movieGenreList = movieGenreImpl.findAll();
        tableMovieGenre.setItems(movieGenreList);
        ObservableList<Session> sessionList = sessionImpl.findAll();
        tableSession.setItems(sessionList);
    }

    private void initListeners() {
        // слушает двойное нажатие для редактирования записи
        tableDistrict.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditDistrict.fire();
                }
            }
        });

        tableCinema.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditCinema.fire();
                }
            }
        });

        tableHall.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditHall.fire();
                }
            }
        });

        tableGenre.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditGenre.fire();
                }
            }
        });

        tableMovie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditMovie.fire();
                }
            }
        });

        tableMovieGenre.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditMovieGenre.fire();
                }
            }
        });

        tableSession.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    btnEditSession.fire();
                }
            }
        });
    }

    private void initLoader() {
        try {
            fxmlLoaderDistrict.setLocation(getClass().getResource(FXML_EDIT_DISTRICT));
            fxmlEditDistrict = fxmlLoaderDistrict.load();
            editDistrictController = fxmlLoaderDistrict.getController();
            fxmlLoaderCinema.setLocation(getClass().getResource(FXML_EDIT_CINEMA));
            fxmlEditCinema = fxmlLoaderCinema.load();
            editCinemaController = fxmlLoaderCinema.getController();
            fxmlLoaderHall.setLocation(getClass().getResource(FXML_EDIT_HALL));
            fxmlEditHall = fxmlLoaderHall.load();
            editHallController = fxmlLoaderHall.getController();
            fxmlLoaderMovie.setLocation(getClass().getResource(FXML_EDIT_MOVIE));
            fxmlEditMovie = fxmlLoaderMovie.load();
            editMovieController = fxmlLoaderMovie.getController();
            fxmlLoaderGenre.setLocation(getClass().getResource(FXML_EDIT_GENRE));
            fxmlEditGenre = fxmlLoaderGenre.load();
            editGenreController = fxmlLoaderGenre.getController();
            fxmlLoaderMovieGenre.setLocation(getClass().getResource(FXML_EDIT_MOVIE_GENRE));
            fxmlEditMovieGenre = fxmlLoaderMovieGenre.load();
            editMovieGenreController = fxmlLoaderMovieGenre.getController();
            fxmlLoaderSession.setLocation(getClass().getResource(FXML_EDIT_SESSION));
            fxmlEditSession = fxmlLoaderSession.load();
            editSessionController = fxmlLoaderSession.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionButtonDistrict(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        District selectedDistrict = (District) tableDistrict.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;

        switch (clickedButton.getId()) {
            case "btnAddDistrict":
                editDistrictController.setDistrictAdd(new District());
                showDialogDistrict(actionEvent);

                if (editDistrictController.isSaveClicked()) {
                    districtImpl.add(editDistrictController.getDistrict());
                    research = true;
                }
                break;

            case "btnEditDistrict":
                if (!districtIsSelected(selectedDistrict)) {
                    return;
                }
                editDistrictController.setDistrictEdit(selectedDistrict);
                showDialogDistrict(actionEvent);

                if (editDistrictController.isSaveClicked()) {
                    districtImpl.update(selectedDistrict);
                    research = true;
                }

                break;

            case "btnDeleteDistrict":
                if (!districtIsSelected(selectedDistrict) || !(confirmDelete())) {
                    return;
                }

                research = true;
                districtImpl.delete(selectedDistrict);
                break;
        }

        if (research) {
            actionSearchCinema(actionEvent);
        }

    }

    private boolean confirmDelete() {
        if (DialogManager.showConfirmDialog("Удаление", "Вы хотите удалить запись?").get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }

    private boolean districtIsSelected(District selectedDistrict) {
        if (selectedDistrict == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogDistrict(ActionEvent actionEvent) {
        if (editDistrictStage == null) {
            editDistrictStage = new Stage();
            editDistrictStage.setTitle("Район");
            editDistrictStage.setMinHeight(100);
            editDistrictStage.setMinWidth(330);
            editDistrictStage.setResizable(false);
            editDistrictStage.setScene(new Scene(fxmlEditDistrict));
            editDistrictStage.initModality(Modality.WINDOW_MODAL);
            editDistrictStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editDistrictStage.showAndWait(); // для ожидания закрытия окна
    }

    public void actionSearchDistrict(ActionEvent actionEvent) {
        districtImpl.find(txtSearchDistrict.getText());
        setComboboxQuery1();
    }

    public void actionButtonCinema(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        Cinema selectedCinema = (Cinema) tableCinema.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        setComboboxDistrict();
        switch (clickedButton.getId()) {
            case "btnAddCinema":
                editCinemaController.setCinemaAdd(new Cinema());
                showDialogCinema(actionEvent);
                if (editCinemaController.isSaveClicked()) {
                    cinemaImpl.add(editCinemaController.getCinema());
                    research = true;
                }
                break;

            case "btnEditCinema":
                if (!cinemaIsSelected(selectedCinema)) {
                    return;
                }
                editCinemaController.setCinemaEdit(selectedCinema);
                showDialogCinema(actionEvent);

                if (editCinemaController.isSaveClicked()) {
                    cinemaImpl.update(selectedCinema);
                    research = true;
                }

                break;

            case "btnDeleteCinema":
                if (!cinemaIsSelected(selectedCinema) || !(confirmDelete())) {
                    return;
                }

                research = true;
                cinemaImpl.delete(selectedCinema);
                break;
        }

        if (research) {
            actionSearchCinema(actionEvent);
        }
    }

    public void actionSearchCinema(ActionEvent actionEvent) {
        cinemaImpl.find(txtSearchCinema.getText());
        setComboboxQuery2();
    }

    private boolean cinemaIsSelected(Cinema selectedCinema) {
        if (selectedCinema == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogCinema(ActionEvent actionEvent) {

        if (editCinemaStage == null) {
            editCinemaStage = new Stage();
            editCinemaStage.setTitle("Кинотеатр");
            editCinemaStage.setMinHeight(210);
            editCinemaStage.setMinWidth(330);
            editCinemaStage.setResizable(false);
            editCinemaStage.setScene(new Scene(fxmlEditCinema));
            editCinemaStage.initModality(Modality.WINDOW_MODAL);
            editCinemaStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editCinemaStage.showAndWait(); // для ожидания закрытия окна
    }

    private void setComboboxDistrict() {
        editCinemaController.getComboBoxDistrict().getItems().clear();
        for (District district: districtImpl.getDistrictList()) {
            editCinemaController.getComboBoxDistrict().getItems().add(district.getDistrict_id());
        }
    }

    public void actionButtonHall(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        Hall selectedHall = (Hall) tableHall.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        setComboboxCinema();
        switch (clickedButton.getId()) {
            case "btnAddHall":
                editHallController.setHallAdd(new Hall());
                showDialogHall(actionEvent);
                if (editHallController.isSaveClicked()) {
                    hallImpl.add(editHallController.getHall());
                    research = true;
                }
                break;

            case "btnEditHall":
                if (!hallIsSelected(selectedHall)) {
                    return;
                }
                editHallController.setHallEdit(selectedHall);
                showDialogHall(actionEvent);

                if (editHallController.isSaveClicked()) {
                    hallImpl.update(selectedHall);
                    research = true;
                }

                break;

            case "btnDeleteHall":
                if (!hallIsSelected(selectedHall) || !(confirmDelete())) {
                    return;
                }

                research = true;
                hallImpl.delete(selectedHall);
                break;
        }

        if (research) {
            actionSearchHall(actionEvent);
        }
    }

    public void actionSearchHall(ActionEvent actionEvent) {
        hallImpl.find(txtSearchHall.getText());
    }

    private boolean hallIsSelected(Hall selectedHall) {
        if (selectedHall == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogHall(ActionEvent actionEvent) {

        if (editHallStage == null) {
            editHallStage = new Stage();
            editHallStage.setTitle("Зал");
            editHallStage.setMinHeight(140);
            editHallStage.setMinWidth(330);
            editHallStage.setResizable(false);
            editHallStage.setScene(new Scene(fxmlEditHall));
            editHallStage.initModality(Modality.WINDOW_MODAL);
            editHallStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editHallStage.showAndWait(); // для ожидания закрытия окна
    }

    private void setComboboxCinema() {
        editHallController.getComboBoxCinema().getItems().clear();
        for (Cinema cinema: cinemaImpl.getCinemaList()) {
            editHallController.getComboBoxCinema().getItems().add(cinema.getCinema_id());
        }
    }

    public void actionButtonGenre(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        Genre selectedGenre = (Genre) tableGenre.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnAddGenre":
                editGenreController.setGenreAdd(new Genre());
                showDialogGenre(actionEvent);
                if (editGenreController.isSaveClicked()) {
                    genreImpl.add(editGenreController.getGenre());
                    research = true;
                }
                break;

            case "btnEditGenre":
                if (!genreIsSelected(selectedGenre)) {
                    return;
                }
                editGenreController.setGenreEdit(selectedGenre);
                showDialogGenre(actionEvent);

                if (editGenreController.isSaveClicked()) {
                    genreImpl.update(selectedGenre);
                    research = true;
                }

                break;

            case "btnDeleteGenre":
                if (!genreIsSelected(selectedGenre) || !(confirmDelete())) {
                    return;
                }

                research = true;
                genreImpl.delete(selectedGenre);
                break;
        }

        if (research) {
            actionSearchGenre(actionEvent);
        }
    }

    public void actionSearchGenre(ActionEvent actionEvent) {
        genreImpl.find(txtSearchGenre.getText());
        setComboboxQuery3();
    }

    private boolean genreIsSelected(Genre selectedGenre) {
        if (selectedGenre == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogGenre(ActionEvent actionEvent) {

        if (editGenreStage == null) {
            editGenreStage = new Stage();
            editGenreStage.setTitle("Жанр");
            editGenreStage.setMinHeight(100);
            editGenreStage.setMinWidth(330);
            editGenreStage.setResizable(false);
            editGenreStage.setScene(new Scene(fxmlEditGenre));
            editGenreStage.initModality(Modality.WINDOW_MODAL);
            editGenreStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editGenreStage.showAndWait(); // для ожидания закрытия окна
    }

    public void actionButtonMovie(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        Movie selectedMovie = (Movie) tableMovie.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        boolean research = false;
        switch (clickedButton.getId()) {
            case "btnAddMovie":
                editMovieController.setMovieAdd(new Movie());
                showDialogMovie(actionEvent);
                if (editMovieController.isSaveClicked()) {
                    movieImpl.add(editMovieController.getMovie());
                    research = true;
                }
                break;

            case "btnEditMovie":
                if (!movieIsSelected(selectedMovie)) {
                    return;
                }
                editMovieController.setMovieEdit(selectedMovie);
                showDialogMovie(actionEvent);
                if (editMovieController.isSaveClicked()) {
                    movieImpl.update(selectedMovie);
                    research = true;
                }
                break;

            case "btnDeleteMovie":
                if (!movieIsSelected(selectedMovie) || !(confirmDelete())) {
                    return;
                }
                research = true;
                movieImpl.delete(selectedMovie);
                break;
        }

        if (research) {
            actionSearchMovie(actionEvent);
        }
    }

    public void actionSearchMovie(ActionEvent actionEvent) {
        movieImpl.find(txtSearchMovie.getText());
    }

    private boolean movieIsSelected(Movie selectedMovie) {
        if (selectedMovie == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogMovie(ActionEvent actionEvent) {

        if (editMovieStage == null) {
            editMovieStage = new Stage();
            editMovieStage.setTitle("Фильм");
            editMovieStage.setMinHeight(210);
            editMovieStage.setMinWidth(370);
            editMovieStage.setResizable(false);
            editMovieStage.setScene(new Scene(fxmlEditMovie));
            editMovieStage.initModality(Modality.WINDOW_MODAL);
            editMovieStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editMovieStage.showAndWait(); // для ожидания закрытия окна
    }

    public void actionButtonMovieGenre(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        MovieGenre selectedMovieGenre = (MovieGenre) tableMovieGenre.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        setComboboxMovie();
        setComboboxGenre();
        switch (clickedButton.getId()) {
            case "btnAddMovieGenre":
                editMovieGenreController.setMovieGenreAdd(new MovieGenre());
                showDialogMovieGenre(actionEvent);
                if (editMovieGenreController.isSaveClicked()) {
                    movieGenreImpl.add(editMovieGenreController.getMovieGenre());
                    research = true;
                }
                break;

            case "btnEditMovieGenre":
                if (!movieGenreIsSelected(selectedMovieGenre)) {
                    return;
                }
                editMovieGenreController.setMovieGenreEdit(selectedMovieGenre);
                showDialogMovieGenre(actionEvent);

                if (editMovieGenreController.isSaveClicked()) {
                    movieGenreImpl.update(selectedMovieGenre, editMovieGenreController.getMovie_id(), editMovieGenreController.getGenre_id());
                    research = true;
                }

                break;

            case "btnDeleteMovieGenre":
                if (!movieGenreIsSelected(selectedMovieGenre) || !(confirmDelete())) {
                    return;
                }

                research = true;
                movieGenreImpl.delete(selectedMovieGenre);
                break;
        }

        if (research) {
            actionSearchMovieGenre(actionEvent);
        }
    }

    public void actionSearchMovieGenre(ActionEvent actionEvent) {
        movieGenreImpl.find(txtSearchMovieGenre.getText());
    }

    private boolean movieGenreIsSelected(MovieGenre selectedMovieGenre) {
        if (selectedMovieGenre == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogMovieGenre(ActionEvent actionEvent) {

        if (editMovieGenreStage == null) {
            editMovieGenreStage = new Stage();
            editMovieGenreStage.setTitle("Соответствие");
            editMovieGenreStage.setMinHeight(130);
            editMovieGenreStage.setMinWidth(260);
            editMovieGenreStage.setResizable(false);
            editMovieGenreStage.setScene(new Scene(fxmlEditMovieGenre));
            editMovieGenreStage.initModality(Modality.WINDOW_MODAL);
            editMovieGenreStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editMovieGenreStage.showAndWait(); // для ожидания закрытия окна
    }

    private void setComboboxMovie() {
        editMovieGenreController.getComboBoxMovie().getItems().clear();
        for (Movie movie: movieImpl.getMovieList()) {
            editMovieGenreController.getComboBoxMovie().getItems().add(movie.getMovie_id());
        }
    }

    private void setComboboxGenre() {
        editMovieGenreController.getComboBoxGenre().getItems().clear();
        for (Genre genre: genreImpl.getGenreList()) {
            editMovieGenreController.getComboBoxGenre().getItems().add(genre.getGenre_id());
        }
    }

    public void actionButtonSession(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим
        if(!(source instanceof Button)) {
            return;
        }

        Session selectedSession = (Session) tableSession.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        boolean research = false;
        setComboboxHall();
        setComboboxSessionMovie();
        switch (clickedButton.getId()) {
            case "btnAddSession":
                editSessionController.setSessionAdd(new Session());
                showDialogSession(actionEvent);
                if (editSessionController.isSaveClicked()) {
                    sessionImpl.add(editSessionController.getSession());
                    research = true;
                }
                break;

            case "btnEditSession":
                if (!sessionIsSelected(selectedSession)) {
                    return;
                }
                editSessionController.setSessionEdit(selectedSession);
                showDialogSession(actionEvent);

                if (editSessionController.isSaveClicked()) {
                    sessionImpl.update(selectedSession);
                    research = true;
                }

                break;

            case "btnDeleteSession":
                if (!sessionIsSelected(selectedSession) || !(confirmDelete())) {
                    return;
                }

                research = true;
                sessionImpl.delete(selectedSession);
                break;
        }

        if (research) {
            actionSearchSession(actionEvent);
        }
    }

    public void actionSearchSession(ActionEvent actionEvent) {
        sessionImpl.find(txtSearchSession.getText());
    }

    private boolean sessionIsSelected(Session selectedSession) {
        if (selectedSession == null) {
            DialogManager.showInfoDialog("Ошибка","Выберите запись!");
            return false;
        }
        return true;
    }

    private void showDialogSession(ActionEvent actionEvent) {

        if (editSessionStage == null) {
            editSessionStage = new Stage();
            editSessionStage.setTitle("Сеанс");
            editSessionStage.setMinHeight(210);
            editSessionStage.setMinWidth(300);
            editSessionStage.setResizable(false);
            editSessionStage.setScene(new Scene(fxmlEditSession));
            editSessionStage.initModality(Modality.WINDOW_MODAL);
            editSessionStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        }
        editSessionStage.showAndWait(); // для ожидания закрытия окна
    }

    private void setComboboxSessionMovie() {
        editSessionController.getComboBoxMovie().getItems().clear();
        for (Movie movie: movieImpl.getMovieList()) {
            editSessionController.getComboBoxMovie().getItems().add(movie.getMovie_id());
        }
    }

    private void setComboboxHall() {
        editSessionController.getComboBoxHall().getItems().clear();
        for (Hall hall: hallImpl.getHallList()) {
            editSessionController.getComboBoxHall().getItems().add(hall.getHall_id());
        }
    }

    public void actionQuery1(ActionEvent actionEvent) {
        if (comboBoxQuery1.getSelectionModel().isEmpty()) {
            DialogManager.showInfoDialog("Ошибка", "Выберите район!");
            return;
        }
        tableQuery1.setItems(query1DB.findAll(comboBoxQuery1.getValue()));
        if (tableQuery1.getItems().size() == 0) {
            DialogManager.showInfoDialog("Информация", "Ничего не найдено");
        }
    }

    public void actionQuery2(ActionEvent actionEvent) {
        if (comboBoxQuery2.getSelectionModel().isEmpty()) {
            DialogManager.showInfoDialog("Ошибка", "Выберите кинотеатр!");
            return;
        }
        tableQuery2.setItems(query2DB.findAll(comboBoxQuery2.getValue()));
        if (tableQuery2.getItems().size() == 0) {
            DialogManager.showInfoDialog("Информация", "Ничего не найдено");
        }
    }

    public void actionQuery3(ActionEvent actionEvent) {
        if (comboBoxQuery3.getSelectionModel().isEmpty()) {
            DialogManager.showInfoDialog("Ошибка", "Выберите жанр!");
            return;
        }
        tableQuery3.setItems(query3DB.findAll(comboBoxQuery3.getValue()));
        if (tableQuery3.getItems().size() == 0) {
            DialogManager.showInfoDialog("Информация", "Ничего не найдено");
        }
    }

    public void actionQuery4(ActionEvent actionEvent) {
        if(txtQuery4.getText().trim().length() == 0
                || !txtQuery4.getText().trim().matches("\\d+")) {
            DialogManager.showInfoDialog("Ошибка", "Введите корректные значения!");
            return;
        }
        tableQuery4.setItems(query4DB.findAll(Integer.parseInt(txtQuery4.getText().trim())));
        if (tableQuery4.getItems().size() == 0) {
            DialogManager.showInfoDialog("Информация", "Ничего не найдено");
        }
    }

    public void actionQuery5(ActionEvent actionEvent) {
        if (txtQuery5.getText().trim().length() == 0
                || !txtQuery5.getText().trim().matches("\\d+")) {
            DialogManager.showInfoDialog("Ошибка", "Введите корректные значения!");
            return;
        }
        tableQuery5.setItems(query5DB.findAll(Integer.parseInt(txtQuery5.getText().trim())));
        if (tableQuery5.getItems().size() == 0) {
            DialogManager.showInfoDialog("Информация", "Ничего не найдено");
        }
    }
}