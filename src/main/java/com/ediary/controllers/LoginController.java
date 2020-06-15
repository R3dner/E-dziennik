package com.ediary.controllers;

import com.ediary.modelFx.StudentModel;
import com.ediary.modelFx.TeacherModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {

    private StackPaneController stackPaneController;
    private StudentModel studentModel;
    private TeacherModel teacherModel;

    @FXML
    private Button backButton;

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField passworldField;

    @FXML
    private TextField mailField;

    @FXML
    private Label alertLabel;

    public Label getAlertLabel() {
        return alertLabel;
    }

    @FXML
    public void initialize(){
        this.studentModel = new StudentModel();
        this.teacherModel = new TeacherModel();
    }

    @FXML
    public void goBack() throws IOException {
        stackPaneController.openStartScreen();
    }

    @FXML
    public void logIn() throws IOException {
        //uczeń
        if(this.studentModel.emailIsInDatabase(mailField.getText())) {
            if(this.studentModel.checkLogIn(mailField.getText(),passworldField.getText())){
                alertLabel.setText("Witaj "+ studentModel.getLoggedStudent().getName());

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/StudentScreen.fxml"));
                Parent pane = null;
                pane = loader.load();

                StudentScreenController studentScreenController = loader.getController();
                studentScreenController.setStackPaneController(stackPaneController);
                stackPaneController.setScreen(pane);


            }
            else {
                alertLabel.setText("Hasło jest niepoprawne");
            }
        }
        //nauczyciel
        if(this.teacherModel.emailIsInDatabase(mailField.getText())){
            if(this.teacherModel.checkLogIn(mailField.getText(),passworldField.getText())){
                alertLabel.setText("Witaj " + teacherModel.getLoggedTeacher());

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/TeacherScreen.fxml"));
                Parent pane = null;
                pane = loader.load();

                TeacherScreenController teacherScreenController = loader.getController();
                teacherScreenController.setStackPaneController(stackPaneController);
                stackPaneController.setScreen(pane);

            }
            else {
                alertLabel.setText("Hasło jest niepoprawne");
            }
        }
        else {
            alertLabel.setText("Konto z podanym adresem \n e-mail nie istnieje");

        }

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

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public StackPaneController getStackPaneController() {
        return stackPaneController;
    }
}
