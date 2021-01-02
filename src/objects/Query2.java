package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Query2 {

    SimpleStringProperty date_time;
    SimpleIntegerProperty places;

    public Query2() {
        this.date_time = new SimpleStringProperty("");
        this.places = new SimpleIntegerProperty();
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

    public int getPlaces() {
        return places.get();
    }

    public SimpleIntegerProperty placesProperty() {
        return places;
    }

    public void setPlaces(int places) {
        this.places.set(places);
    }
}
