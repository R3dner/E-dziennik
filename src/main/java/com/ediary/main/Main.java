package com.ediary.main;

import com.ediary.database.utils.DbManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    static public void main(String[] args){

        DbManager.initDatabase();

        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/StackPane.fxml"));

        StackPane stackPane = loader.load();

        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("E-Dziennik");
        primaryStage.show();


        //database
        DbManager.initDatabase();


    }


}
