package com.ediary.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StackPaneController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() throws IOException {
        openStartScreen();
    }

    public void openStartScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/StartScreen.fxml"));
        Pane pane = null;

        pane = loader.load();

        StartScreenController startScreenController = loader.getController();
        startScreenController.setStackPaneController(this);
        setScreen(pane);
    }

    public void setScreen(Parent parent) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(parent);
    }

    //for tests

    StackPane getMainStackPane() {
        return mainStackPane;
    }

    void setMainStackPane(StackPane mainStackPane) {
        this.mainStackPane = mainStackPane;
    }
}
