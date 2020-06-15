package com.ediary.modelFx;

import com.ediary.database.models.School;
import javafx.beans.property.*;

public class GroupFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<SchoolFx> schoolFx = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SchoolFx getSchoolFx() {
        return schoolFx.get();
    }

    public ObjectProperty<SchoolFx> schoolFxProperty() {
        return schoolFx;
    }

    public void setSchoolFx(SchoolFx schoolFx) {
        this.schoolFx.set(schoolFx);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
