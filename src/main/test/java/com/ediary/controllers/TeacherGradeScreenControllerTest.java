package com.ediary.controllers;

import com.ediary.modelFx.GradeFx;
import com.ediary.modelFx.SubjectFx;
import com.ediary.modelFx.TeacherFx;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesFoundException;

import java.io.IOException;

public class TeacherGradeScreenControllerTest extends GuiTest {
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
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //given
                TeacherGradeScreenController controller = new TeacherGradeScreenController();
                ComboBox<SubjectFx> subjectComboBox = find("#subjectComboBox");
                controller.setSubjectComboBox(subjectComboBox);
                Button deleteGradeButton = find("#deleteGradeButton");
                controller.setDeleteGradeButton(deleteGradeButton);
                TableView<GradeFx> tableView = find("tableView");
                controller.setTableView(tableView);
                TableColumn<GradeFx, Number> gradeColumn = (TableColumn<GradeFx, Number>) tableView.getColumns().get(0);
                gradeColumn.setText("test");
                controller.setGradeColumn(gradeColumn);
                TableColumn<GradeFx, SubjectFx> subjectColumn = (TableColumn<GradeFx, SubjectFx>) tableView.getColumns().get(1);
                controller.setSubjectColumn(subjectColumn);
                TableColumn<GradeFx, String> descColumn = (TableColumn<GradeFx, String>) tableView.getColumns().get(2);
                controller.setDescColumn(descColumn);
                TableColumn<GradeFx, String> dateColumn = (TableColumn<GradeFx, String>) tableView.getColumns().get(3);
                controller.setDateColumn(dateColumn);
                TableColumn<GradeFx, TeacherFx> teacherColumn = (TableColumn<GradeFx, TeacherFx>) tableView.getColumns().get(4);
                controller.setTeacherColumn(teacherColumn);

                //when
                String gradeColumnText = "test";

                //then
                Assert.assertEquals(gradeColumnText, controller.getGradeColumn().getText());
            }
        });
    }

    @Test(expected = NullPointerException.class)
    public void initializeWithNullController(){
        //given
        TeacherGradeScreenController controller = null;

        //when

        //then
        controller.initialize();
    }
}
