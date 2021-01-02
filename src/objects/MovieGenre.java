package objects;

import javafx.beans.property.SimpleIntegerProperty;

public class MovieGenre {

    private SimpleIntegerProperty movie_id;
    private SimpleIntegerProperty genre_id;

    public MovieGenre() {
        this.movie_id = new SimpleIntegerProperty();
        this.genre_id = new SimpleIntegerProperty();
    }

    public MovieGenre(int movie_id, int genre_id) {
        this.movie_id = new SimpleIntegerProperty(movie_id);
        this.genre_id = new SimpleIntegerProperty(genre_id);
    }

    public int getMovie_id() {
        return movie_id.get();
    }

    public SimpleIntegerProperty movie_idProperty() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id.set(movie_id);
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
}
