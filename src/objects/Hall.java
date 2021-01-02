package objects;

import javafx.beans.property.SimpleIntegerProperty;

public class Hall {

    private SimpleIntegerProperty hall_id;
    private SimpleIntegerProperty capacity;
    private SimpleIntegerProperty cinema_id;

    public Hall() {
        this.hall_id = new SimpleIntegerProperty();
        this.capacity = new SimpleIntegerProperty();
        this.cinema_id = new SimpleIntegerProperty();
    }

    public Hall(int hall_id, int capacity, int cinema_id) {
        this.hall_id = new SimpleIntegerProperty(hall_id);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.cinema_id = new SimpleIntegerProperty(cinema_id);
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

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public int getCinema_id() {
        return cinema_id.get();
    }

    public SimpleIntegerProperty cinema_idProperty() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id.set(cinema_id);
    }
}
