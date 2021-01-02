package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Genre {

    private SimpleIntegerProperty genre_id;
    private SimpleStringProperty genre_name;

    public Genre() {
        this.genre_id = new SimpleIntegerProperty();
        this.genre_name = new SimpleStringProperty("");
    }

    public Genre(int genre_id, String genre_name) {
        this.genre_id = new SimpleIntegerProperty(genre_id);
        this.genre_name = new SimpleStringProperty(genre_name);
    }

    public int getGenre_id() {
        return genre_id.get();
    }

    public SimpleIntegerProperty genre_idProperty() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id.set(genre_id);
    }

    public String getGenre_name() {
        return genre_name.get();
    }

    public SimpleStringProperty genre_nameProperty() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name.set(genre_name);
    }
}
