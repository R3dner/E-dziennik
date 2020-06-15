package com.ediary.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

public class StackPaneControllerTest extends GuiTest {

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

    @Test(expected = NullPointerException.class)
    public void setScreenWithNullParent() {
        //given
        StackPaneController controller = new StackPaneController();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/StartScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //when

        //then
        controller.setScreen(parent);
    }
}
