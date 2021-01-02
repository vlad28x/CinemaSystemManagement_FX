package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DialogManager;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        if (!(new File("db" + File.separator + "CINEMA.db").exists())) {
            DialogManager.showErrorDialog("Ошибка", "Отсутствует база данных!");
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
            primaryStage.setTitle("Кинотеатр");
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(810);
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }
    }


    public static void main(String[] args) {
            launch(args);
    }
}
