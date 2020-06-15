package com.ediary.controllers;

import com.ediary.modelFx.GradeFx;
import com.ediary.modelFx.SubjectFx;
import com.ediary.modelFx.TeacherFx;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

public class StudentGradeScreenControllerTest extends GuiTest {
    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("/fxml/StudentGradeScreen.fxml"));
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
                StudentGradeScreenController controller = new StudentGradeScreenController();
                ComboBox<SubjectFx> subjectComboBox = find("#subjectComboBox");
                controller.setSubjectComboBox(subjectComboBox);
                TableView<GradeFx> tableView = find("#tableView");
                controller.setTableView(tableView);
                TableColumn<GradeFx, Number> gradeColumn = (TableColumn<GradeFx, Number>) tableView.getColumns().get(0);
                gradeColumn.setText("tmp");
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

                //then
                Assert.assertEquals("tmp", gradeColumn.getText());
                controller.initialize();
            }
        });
    }

    @Test(expected = NullPointerException.class)
    public void initializeWithNullController(){
        //given
        StudentGradeScreenController controller = null;

        //when

        //then
        controller.initialize();
    }
}
