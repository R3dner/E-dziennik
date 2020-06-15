package com.ediary.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

import static org.junit.Assert.*;


public class LoginControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
            return parent;
        }catch(IOException ex){

        }
        return parent;
    }

    @Test(expected = NullPointerException.class)
     public void testLoginForStudentNullController() {
        //given
        LoginController controller = null;

        //when
        String result = "Konto z podanym adresem \n e-mail nie istnieje";

        //then
        try{
            controller.logIn();
        }catch(IOException exception){
            assertEquals(result,exception.getMessage());
        }
    }


    @Test(expected = NullPointerException.class)
     public void testGoBackNullController(){
        //given
        LoginController controller = null;

        //when
        NullPointerException result = new NullPointerException();

        //then
        try {
            controller.getStackPaneController().openStartScreen();
        } catch (IOException exception) {
            assertEquals(result,exception);
        }
    }

    @Test
     public void testInitialize()
    {
        //given
        LoginController controller = new LoginController();

        //when


        //then
        controller.initialize();
        assertNotNull(controller.getStudentModel());
        assertNotNull(controller.getTeacherModel());
    }

    @Test
    public void testWithoutInitialize()
    {
        //given
        LoginController controller = new LoginController();

        //when


        //then
        assertNull(controller.getStudentModel());
        assertNull(controller.getTeacherModel());
    }


}


