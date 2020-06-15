package com.ediary.controllers;

import com.ediary.modelFx.StudentModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StudentScreenController {

    private StackPaneController stackPaneController;
    private StudentModel studentModel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label loginLabel;

    @FXML
    public void initialize(){
        studentModel = new StudentModel();
        loginLabel.setText("Uczeń: " + studentModel.getLoggedStudent());
    }

    @FXML
    public void logOut() throws IOException {
        studentModel.setLoggedStudent(null);
        stackPaneController.openStartScreen();
    }


    public void setCenter(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;

        parent = loader.load();
        borderPane.setCenter(parent);
    }

    @FXML
    public void openOptions() throws IOException {
        this.setCenter("/fxml/StudentOptionsScreen.fxml");
    }

    @FXML
    public void openGrades() throws IOException {
        this.setCenter("/fxml/StudentGradeScreen.fxml");
    }

    public StackPaneController getStackPaneController() {
        return stackPaneController;
    }

    public void setStackPaneController(StackPaneController stackPaneController) {
        this.stackPaneController = stackPaneController;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
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
}
