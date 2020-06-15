package com.ediary.database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "STUDENT")
public class Student implements BaseModel {

    public Student(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "DATE_OF_BIRTH", canBeNull = false)
    private String dateOfBirth;

    @DatabaseField(columnName = "PHONE_NUMBER")
    private String phoneNumber;

    @DatabaseField(columnName = "ADDRESS", canBeNull = false)
    private String address;

    @DatabaseField(columnName = "SCHOOL_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private School school;

    @DatabaseField(columnName = "GROUP_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Group group;

    @DatabaseField(columnName = "EMAIL", unique = true, canBeNull = false)
    private String email;

    @DatabaseField(columnName = "PASSWORD", canBeNull = false)
    private String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Group getGroup() {
        return group;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
