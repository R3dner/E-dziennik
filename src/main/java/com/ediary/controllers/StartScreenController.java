package com.ediary.controllers;

import com.ediary.modelFx.StudentModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController {

    private StackPaneController stackPaneController;
    private StudentModel studentModel;

    @FXML
    public void initialize(){
        this.studentModel = new StudentModel();
        studentModel.init();
    }

    @FXML
    public void openLoginPanel() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScreen.fxml"));
        Pane pane = null;
        pane = loader.load();

        LoginController loginController = loader.getController();
        loginController.setStackPaneController(stackPaneController);
        stackPaneController.setScreen(pane);

    }

    @FXML
    public void openRegistrationPanel() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/RegistrationScreen.fxml"));
        AnchorPane pane = null;
        pane = loader.load();

        RegistrationController registrationController = loader.getController();
        registrationController.setStackPaneController(stackPaneController);
        stackPaneController.setScreen(pane);
    }

    // for tests

     void setStackPaneController(StackPaneController stackPaneController) {
        this.stackPaneController = stackPaneController;
    }

     StackPaneController getStackPaneController() {
        return stackPaneController;
    }

     StudentModel getStudentModel() {
        return studentModel;
    }

     void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }
}
