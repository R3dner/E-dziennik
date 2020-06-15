package com.ediary.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

public class TeacherScreenControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("/fxml/TeacherScreen.fxml"));
            return parent;
        }catch(IOException ex){

        }
        return parent;
    }

    @Test
    public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //given
                TeacherScreenController controller = new TeacherScreenController();
                BorderPane borderPane = find("#borderPane");
                controller.setBorderPane(borderPane);
                Label loginLabel = find("#loginLabel");
                loginLabel.setText("test");
                controller.setLoginLabel(loginLabel);
                Button logOutButton = find("#logOutButton");
                logOutButton.setText("test");
                controller.setLogOutButton(logOutButton);

                //when
                Label testLabel = new Label();
                testLabel.setText("test");

                //then
                Assert.assertEquals(testLabel.getText(),loginLabel.getText());
                controller.initialize();
            }
        });
    }

    @Test(expected = NullPointerException.class)
    public void initializeWithNullController(){
        //given
        TeacherScreenController controller = null;
        //when
        //then
        controller.initialize();
    }
}
