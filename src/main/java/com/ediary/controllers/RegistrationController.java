package com.ediary.controllers;

import com.ediary.database.models.Group;
import com.ediary.database.models.School;
import com.ediary.modelFx.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.*;

import java.io.IOException;


public class RegistrationController {

    StackPaneController stackPaneController;
    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private SchoolModel schoolModel;
    private GroupModel groupModel;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField birthField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repPasswordField;

    @FXML
    private Label alertLabel;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> studentTeacherChoiceBox;

    @FXML
    private ChoiceBox<SchoolFx> schoolChoiceBox;

    @FXML
    private ChoiceBox<GroupFx> groupChoiceBox;

    @FXML
    private Label groupLabel;

    public void initialize(){
        this.studentModel = new StudentModel();
        this.teacherModel = new TeacherModel();
        //ładowanie choice boxa
        studentTeacherChoiceBox.getItems().addAll("Uczeń","Nauczyciel");
        studentTeacherChoiceBox.setValue("Uczeń");

        //ładowanie choice boxa ze szkołami
        studentModel.init();
        schoolChoiceBox.getItems().setAll(studentModel.getSchoolFxList());

        //ustawienie listenera ustawiającego grupy na podstawie szkoły
        schoolChoiceBox.getSelectionModel().selectedItemProperty().addListener((sv,soldValue,snewValue)-> {
            System.out.println(snewValue.getId());
            studentModel.schoolFxObjectPropertyProperty().bind(this.schoolChoiceBox.valueProperty());
            System.out.println(studentModel.getSchoolFxObjectProperty().getId());
            studentModel.initSchoolGroups();
            groupChoiceBox.getItems().setAll(studentModel.getFilteredGroupFxList());
        });

        //listener ustawiający widoczność choiceboxa z grupami
        studentTeacherChoiceBox.getSelectionModel().selectedItemProperty().addListener((gv,goldValue,gnewValue)->{
            if(gnewValue == "Nauczyciel"){
                groupChoiceBox.setVisible(false);
                groupLabel.setVisible(false);
            }
            else {
                groupChoiceBox.setVisible(true);
                groupLabel.setVisible(true);
            }
        });

        initBindings();
    }

    private void initBindings() {
        acceptButton.disableProperty().bind(nameField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(surnameField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(birthField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(mailField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(phoneField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(addressField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(passwordField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(repPasswordField.textProperty().isEmpty());
    }

    @FXML
    public void confirmNewUser() {

        //Student
        if (studentTeacherChoiceBox.getValue().equals("Uczeń")) {
            //sprawdzenie czy mail jest już w bazie
            if (studentModel.emailIsInDatabase(mailField.getText()) || teacherModel.emailIsInDatabase(mailField.getText())) {
                alertLabel.setText("Podany mail znajduje się już w bazie!");
            } else {

                //sprawdzenie czy hasło zgadza się z powtórzonym
                if (passwordField.getText().equals(repPasswordField.getText())) {
                    studentModel.saveStudentInDatabase(nameField.getText(), surnameField.getText(),
                            birthField.getText(), phoneField.getText(), addressField.getText(),
                            mailField.getText(), passwordField.getText(), schoolChoiceBox.getValue(),
                            groupChoiceBox.getValue());

                    nameField.clear();
                    surnameField.clear();
                    birthField.clear();
                    phoneField.clear();
                    addressField.clear();
                    mailField.clear();
                    passwordField.clear();
                    repPasswordField.clear();

                    alertLabel.setText("Zarejestrowano użytkownika");

                } else
                    alertLabel.setText("Hasła różnią się");
            }
        } else //Nauczyciel
        {
            if (studentModel.emailIsInDatabase(mailField.getText()) || teacherModel.emailIsInDatabase(mailField.getText())) {
                alertLabel.setText("Podany mail znajduje się już w bazie!");
            } else {
                if (passwordField.getText().equals(repPasswordField.getText())) {
                    teacherModel.saveTeacherInDatabase(nameField.getText(), surnameField.getText(),
                            birthField.getText(), phoneField.getText(), addressField.getText(),
                            mailField.getText(), passwordField.getText(), schoolChoiceBox.getValue());

                    nameField.clear();
                    surnameField.clear();
                    birthField.clear();
                    phoneField.clear();
                    addressField.clear();
                    mailField.clear();
                    passwordField.clear();
                    repPasswordField.clear();

                    alertLabel.setText("Zarejestrowano użytkownika");
                }else
                    alertLabel.setText("Hasła różnią się");
            }
        }
    }

    @FXML
    public void setPasswordToVisible(){
        
    }

    @FXML
    public void goBack() throws IOException {
        stackPaneController.openStartScreen();
    }

    //Getter Setters for tests





    void setStackPaneController(StackPaneController stackPaneController) {
        this.stackPaneController = stackPaneController;
    }

    StudentModel getStudentModel() {
        return studentModel;
    }

    TeacherModel getTeacherModel() {
        return teacherModel;
    }

    ChoiceBox<String> getStudentTeacherChoiceBox() {
        return this.studentTeacherChoiceBox;
    }

    void setStudentTeacherChoiceBox(ChoiceBox<String> studentTeacherChoiceBox) {
        this.studentTeacherChoiceBox = studentTeacherChoiceBox;
    }

     ChoiceBox<SchoolFx> getSchoolChoiceBox() {
        return schoolChoiceBox;
    }

     void setSchoolChoiceBox(ChoiceBox<SchoolFx> schoolChoiceBox) {
        this.schoolChoiceBox = schoolChoiceBox;
    }

     TextField getNameField() {
        return nameField;
    }

     void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

     TextField getSurnameField() {
        return surnameField;
    }

     void setSurnameField(TextField surnameField) {
        this.surnameField = surnameField;
    }

     TextField getBirthField() {
        return birthField;
    }

     void setBirthField(TextField birthField) {
        this.birthField = birthField;
    }

     TextField getMailField() {
        return mailField;
    }

     void setMailField(TextField mailField) {
        this.mailField = mailField;
    }

     TextField getPhoneField() {
        return phoneField;
    }

     void setPhoneField(TextField phoneField) {
        this.phoneField = phoneField;
    }

     TextField getAddressField() {
        return addressField;
    }

     void setAddressField(TextField addressField) {
        this.addressField = addressField;
    }

     PasswordField getPasswordField() {
        return passwordField;
    }

     void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

     PasswordField getRepPasswordField() {
        return repPasswordField;
    }

     void setRepPasswordField(PasswordField repPasswordField) {
        this.repPasswordField = repPasswordField;
    }

     Button getAcceptButton() {
        return acceptButton;
    }

     void setAcceptButton(Button acceptButton) {
        this.acceptButton = acceptButton;
    }

    void setGroupChoiceBox(ChoiceBox<GroupFx> groupChoiceBox) {
        this.groupChoiceBox = groupChoiceBox;
    }
}
