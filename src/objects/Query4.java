package objects;

import javafx.beans.property.SimpleStringProperty;

public class Query4 {

    SimpleStringProperty name;

    public Query4() {
        this.name = new SimpleStringProperty("");
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
