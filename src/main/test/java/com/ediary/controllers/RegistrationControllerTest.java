package com.ediary.controllers;

import com.ediary.modelFx.SchoolFx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;


import java.io.IOException;


public class RegistrationControllerTest extends GuiTest {


    @Test
    public void testWithInitialize(){
        //given
        RegistrationController controller = new RegistrationController();
        ChoiceBox<String> studentTeacherChoiceBox = find("#studentTeacherChoiceBox");
        controller.setStudentTeacherChoiceBox(studentTeacherChoiceBox);
        ChoiceBox<SchoolFx> schoolChoiceBox= find("#schoolChoiceBox");
        controller.setSchoolChoiceBox(schoolChoiceBox);
        Button acceptButton = find("#acceptButton");
        controller.setAcceptButton(acceptButton);
        TextField nameField = find("#nameField");
        nameField.setText("nameField");
        controller.setNameField(nameField);
        TextField surnameField = find("#surnameField");
        controller.setSurnameField(surnameField);
        TextField birthField = find("#birthField");
        controller.setBirthField(birthField);
        TextField mailField = find("#mailField");
        controller.setMailField(mailField);
        TextField phoneField = find("#phoneField");
        controller.setPhoneField(phoneField);
        TextField addressField = find("#addressField");
        controller.setAddressField(addressField);
        PasswordField passwordField = find("#passwordField");
        controller.setPasswordField(passwordField);
        PasswordField repPasswordField = find("#repPasswordField");
        controller.setRepPasswordField(repPasswordField);

        //when

        //then
        controller.initialize();
        Assert.assertEquals("nameField",nameField.getText());
    }

    @Test(expected = NullPointerException.class)
    public void testWithInitializeNullController(){
        //given
        RegistrationController controller = null;

        //then
        controller.initialize();
    }

    @Test
    public void testWithoutInitialize() throws IOException {
        //given
        RegistrationController controller = new RegistrationController();

        //when

        //then

        Assert.assertNull(controller.getStudentModel());
        Assert.assertNull(controller.getTeacherModel());
    }


    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("/fxml/RegistrationScreen.fxml"));
            return parent;
        }catch(IOException ex){

        }
        return parent;
    }
}
