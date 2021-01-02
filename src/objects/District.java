package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class District {

    private SimpleIntegerProperty district_id;
    private SimpleStringProperty district_name;

    public District() {
        this.district_id = new SimpleIntegerProperty();
        this.district_name = new SimpleStringProperty("");
    }

    public District(int district_id, String district_name) {
        this.district_id = new SimpleIntegerProperty(district_id);
        this.district_name = new SimpleStringProperty(district_name);
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

    public String getDistrict_name() {
        return district_name.get();
    }

    public SimpleStringProperty district_nameProperty() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name.set(district_name);
    }
}

