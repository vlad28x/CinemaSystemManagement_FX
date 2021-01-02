package objects;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {

    private SimpleIntegerProperty movie_id;
    private SimpleStringProperty movie_name;
    private SimpleIntegerProperty release;
    private SimpleIntegerProperty duration;
    private SimpleFloatProperty rating;

    public Movie() {
        this.movie_id = new SimpleIntegerProperty();
        this.movie_name = new SimpleStringProperty("");
        this.release = new SimpleIntegerProperty();
        this.duration = new SimpleIntegerProperty();
        this.rating = new SimpleFloatProperty();
    }
    
    public Movie(int movie_id, String movie_name, int release, int duration, float rating) {
        this.movie_id = new SimpleIntegerProperty(movie_id);
        this.movie_name = new SimpleStringProperty(movie_name);
        this.release = new SimpleIntegerProperty(release);
        this.duration = new SimpleIntegerProperty(duration);
        this.rating = new SimpleFloatProperty(rating);
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

    public String getMovie_name() {
        return movie_name.get();
    }

    public SimpleStringProperty movie_nameProperty() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name.set(movie_name);
    }

    public int getRelease() {
        return release.get();
    }

    public SimpleIntegerProperty releaseProperty() {
        return release;
    }

    public void setRelease(int release) {
        this.release.set(release);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duraction) {
        this.duration.set(duraction);
    }

    public float getRating() {
        return rating.get();
    }

    public SimpleFloatProperty ratingProperty() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating.set(rating);
    }
}
