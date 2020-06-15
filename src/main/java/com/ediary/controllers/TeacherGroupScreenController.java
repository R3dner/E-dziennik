package com.ediary.controllers;

import com.ediary.converters.ConverterTeacher;
import com.ediary.database.models.Grade;
import com.ediary.modelFx.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Date;

public class TeacherGroupScreenController {

    private TeacherModel teacherModel;
    private GradeModel gradeModel;

    @FXML
    private ComboBox<GroupFx> groupComboBox;

    @FXML
    private ComboBox<SubjectFx> subjectsComboBox;

    @FXML
    private ComboBox<Integer> gradesComboBox;

    @FXML
    private TableView<StudentFx> tableView;

    @FXML
    private TableColumn<StudentFx, String> nameColumn;

    @FXML
    private TableColumn<StudentFx, String> surnameColumn;

    @FXML
    private TableColumn<StudentFx, String> birthColumn;

    @FXML
    private TableColumn<StudentFx, String> addressColumn;

    @FXML
    private TableColumn<StudentFx, String> emailColumn;

    @FXML
    private TableColumn<StudentFx, String> phoneColumn;

    @FXML
    private TextField descField;


    @FXML
    public void initialize(){
        teacherModel = new TeacherModel();
        gradeModel = new GradeModel();

        //ładowanie comboboxa
        //grupy

        teacherModel.initGroupsByLoggedTeacherSchool();

        System.out.println(teacherModel.getFilteredGroupFxList());
        System.out.println(teacherModel.getLoggedTeacher());


        if(teacherModel.getFilteredGroupFxList().isEmpty())
        {
            System.out.println("pusta lista");
        }
        else {
            System.out.println(teacherModel.getFilteredGroupFxList());
            groupComboBox.getItems().addAll(teacherModel.getFilteredGroupFxList());
        }

        //przedmioty
        teacherModel.initSubjects();
        subjectsComboBox.getItems().addAll(teacherModel.getSubjectFxList());

        // oceny
        gradesComboBox.getItems().addAll(0,1,2,3,4,5,6);

        //listener do table view
        groupComboBox.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
            teacherModel.groupFxObjectPropertyProperty().bind(this.groupComboBox.valueProperty());
            this.teacherModel.initStudentsByGroup();
            this.tableView.setItems(this.teacherModel.getStudentFxObservableList());
            this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
            this.birthColumn.setCellValueFactory(cellData -> cellData.getValue().dateOfBirthProperty());
            this.addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
            this.emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
            this.phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        });



    }

    @FXML
    public void addGrade(){

        int grade = gradesComboBox.getSelectionModel().getSelectedIndex();
        Date date = new Date();
        String desc = descField.getText();
        String addedDate = date.toString();
        StudentFx studentFx = tableView.getSelectionModel().getSelectedItem();
        SubjectFx subjectFx = subjectsComboBox.getSelectionModel().getSelectedItem();

        if(!descField.getText().isEmpty() && !gradesComboBox.getSelectionModel().isEmpty()
        && !subjectsComboBox.getSelectionModel().isEmpty() && !tableView.getSelectionModel().isEmpty()){

            gradeModel.saveGradeInDatabase(grade,desc,addedDate,studentFx,subjectFx,
                    ConverterTeacher.convertToTeacherFx(this.teacherModel.getLoggedTeacher()));

        }
    }


}
