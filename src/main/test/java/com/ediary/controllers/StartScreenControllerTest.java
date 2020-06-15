package com.ediary.controllers;

import com.ediary.modelFx.StudentModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

public class StartScreenControllerTest extends GuiTest {
    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("/fxml/StartScreen.fxml"));
            return parent;
        }catch(IOException ex){

        }
        return parent;
    }

    @Test
    public void initialize(){
        //given
        StartScreenController controller = new StartScreenController();

        //then
        controller.initialize();
    }

    @Test(expected = NullPointerException.class)
    public void initializeWithNullCOntroller(){
        StartScreenController controller = null;

        //then
        controller.initialize();
    }

}
