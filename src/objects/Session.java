package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Session {

    private SimpleIntegerProperty session_id;
    private SimpleStringProperty date_time;
    private SimpleIntegerProperty number_free_places;
    private SimpleIntegerProperty hall_id;
    private SimpleIntegerProperty movie_id;

    public Session() {
        this.session_id = new SimpleIntegerProperty();
        this.date_time = new SimpleStringProperty("");
        this.number_free_places = new SimpleIntegerProperty();
        this.hall_id = new SimpleIntegerProperty();
        this.movie_id = new SimpleIntegerProperty();
    }

    public Session(int session_id, String date_time, int number_free_places, int hall_id, int movie_id) {
        this.session_id = new SimpleIntegerProperty(session_id);
        this.date_time = new SimpleStringProperty(date_time);
        this.number_free_places = new SimpleIntegerProperty(number_free_places);
        this.hall_id = new SimpleIntegerProperty(hall_id);
        this.movie_id = new SimpleIntegerProperty(movie_id);
    }

    public int getSession_id() {
        return session_id.get();
    }

    public SimpleIntegerProperty session_idProperty() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id.set(session_id);
    }

    public String getDate_time() {
        return date_time.get();
    }

    public SimpleStringProperty date_timeProperty() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time.set(date_time);
    }

    public int getNumber_free_places() {
        return number_free_places.get();
    }

    public SimpleIntegerProperty number_free_placesProperty() {
        return number_free_places;
    }

    public void setNumber_free_places(int number_free_places) {
        this.number_free_places.set(number_free_places);
    }

    public int getHall_id() {
        return hall_id.get();
    }

    public SimpleIntegerProperty hall_idProperty() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id.set(hall_id);
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
}
