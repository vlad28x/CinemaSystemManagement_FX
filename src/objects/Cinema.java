package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cinema {

    private SimpleIntegerProperty cinema_id;
    private SimpleStringProperty cinema_name;
    private SimpleStringProperty address;
    private SimpleStringProperty phone_number;
    private SimpleIntegerProperty district_id;

    public Cinema() {
        this.cinema_id = new SimpleIntegerProperty();
        this.cinema_name = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.phone_number = new SimpleStringProperty("");
        this.district_id = new SimpleIntegerProperty();
    }

    public Cinema(int cinema_id, String cinema_name, String address, String phone_number, int district_id) {
        this.cinema_id = new SimpleIntegerProperty(cinema_id);
        this.cinema_name = new SimpleStringProperty(cinema_name);
        this.address = new SimpleStringProperty(address);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.district_id = new SimpleIntegerProperty(district_id);
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

    public String getCinema_name() {
        return cinema_name.get();
    }

    public SimpleStringProperty cinema_nameProperty() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name.set(cinema_name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public SimpleStringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public int getDistrict_id() {
        return district_id.get();
    }

    public SimpleIntegerProperty district_idProperty() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id.set(district_id);
    }
}
