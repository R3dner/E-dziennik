package com.ediary.controllers;

import com.ediary.database.models.Student;
import com.ediary.modelFx.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TeacherScreenController {

    private StackPaneController stackPaneController;
    private TeacherModel teacherModel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label loginLabel;

    @FXML
    private Button logOutButton;



    @FXML
    void initialize(){
        teacherModel = new TeacherModel();
        loginLabel.setText("Nauczyciel: " + teacherModel.getLoggedTeacher());
    }

    @FXML
    void openOptions() {

    }

    @FXML
    public void openGrades() throws IOException {
        this.setCenter("/fxml/TeacherGradesScreen.fxml");
    }

    @FXML
    void logOut() throws IOException {
        teacherModel.setLoggedTeacher(null);
        stackPaneController.openStartScreen();
    }

    @FXML
    void openDiary() throws IOException {

        this.setCenter("/fxml/TeacherGroupsScreen.fxml");
        //this.setCenter("/fxml/test.fxml");
        //this.setCenter("/fxml/StudentOptionsScreen.fxml");

    }

    public void setCenter(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;

        parent = loader.load();
        borderPane.setCenter(parent);
    }

    public StackPaneController getStackPaneController() {
        return stackPaneController;
    }

    public void setStackPaneController(StackPaneController stackPaneController) {
        this.stackPaneController = stackPaneController;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Label getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(Label loginLabel) {
        this.loginLabel = loginLabel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public Button getLogOutButton() {
        return logOutButton;
    }

    public void setLogOutButton(Button logOutButton) {
        this.logOutButton = logOutButton;
    }

}
