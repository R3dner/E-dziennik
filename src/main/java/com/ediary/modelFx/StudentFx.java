package com.ediary.modelFx;

import com.ediary.database.models.Group;
import com.ediary.database.models.School;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import javafx.beans.property.*;

import java.util.Date;

public class StudentFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty dateOfBirth = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private ObjectProperty<SchoolFx> schoolFx = new SimpleObjectProperty<>();
    private ObjectProperty<GroupFx> groupFx = new SimpleObjectProperty<>();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public StudentFx(){}

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
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

    public GroupFx getGroupFx() {
        return groupFx.get();
    }

    public ObjectProperty<GroupFx> groupFxProperty() {
        return groupFx;
    }

    public void setGroupFx(GroupFx groupFx) {
        this.groupFx.set(groupFx);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return name.getValue() + " " + surname.getValue();
    }
}
