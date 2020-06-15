package com.ediary.controllers;

import com.ediary.modelFx.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentGradeScreenController {

    private GradeModel gradeModel;
    private SubjectModel subjectModel;
    private StudentModel studentModel;

    @FXML
    private ComboBox<SubjectFx> subjectComboBox;

    @FXML
    private TableView<GradeFx> tableView;

    @FXML
    private TableColumn<GradeFx, Number> gradeColumn;

    @FXML
    private TableColumn<GradeFx, SubjectFx> subjectColumn;

    @FXML
    private TableColumn<GradeFx, String> descColumn;

    @FXML
    private TableColumn<GradeFx, String> dateColumn;

    @FXML
    private TableColumn<GradeFx, TeacherFx> teacherColumn;


    @FXML
    public void initialize() {
        gradeModel = new GradeModel();
        subjectModel = new SubjectModel();
        studentModel = new StudentModel();

        //ładowanie przedmiotów i comboBoxa
        subjectModel.initSubjects();
        subjectComboBox.getItems().setAll(subjectModel.getSubjectFxList());

        //ładowanie ocen dla ucznia
        gradeModel.initGradesForLoggedStudent(studentModel.getLoggedStudent());

        //table view
        addStudentGradesToTableView();

        //listener do table view - przedmioty
        subjectComboBox.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
            gradeModel.subjectFxObjectPropertyProperty().bind(this.subjectComboBox.valueProperty());
            gradeModel.initGradesBySubjectForStudent(studentModel.getLoggedStudent());
            addStudentGradesToTableView();
        });
    }

    @FXML
    public void displayAllGrades(){
        gradeModel.initGradesForLoggedStudent(studentModel.getLoggedStudent());
        addStudentGradesToTableView();
    }

    public void addStudentGradesToTableView() {
        this.tableView.setItems(this.gradeModel.getFilteredGradeFxObservableList());
        this.gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        this.subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectFxProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descProperty());
        this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().addedDateProperty());
        this.teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherFxProperty());
    }

    // for tests

     ComboBox<SubjectFx> getSubjectComboBox() {
        return subjectComboBox;
    }

     void setSubjectComboBox(ComboBox<SubjectFx> subjectComboBox) {
        this.subjectComboBox = subjectComboBox;
    }

     TableView<GradeFx> getTableView() {
        return tableView;
    }

     void setTableView(TableView<GradeFx> tableView) {
        this.tableView = tableView;
    }

     TableColumn<GradeFx, Number> getGradeColumn() {
        return gradeColumn;
    }

     void setGradeColumn(TableColumn<GradeFx, Number> gradeColumn) {
        this.gradeColumn = gradeColumn;
    }

     TableColumn<GradeFx, SubjectFx> getSubjectColumn() {
        return subjectColumn;
    }

     void setSubjectColumn(TableColumn<GradeFx, SubjectFx> subjectColumn) {
        this.subjectColumn = subjectColumn;
    }

     TableColumn<GradeFx, String> getDescColumn() {
        return descColumn;
    }

     void setDescColumn(TableColumn<GradeFx, String> descColumn) {
        this.descColumn = descColumn;
    }

     TableColumn<GradeFx, String> getDateColumn() {
        return dateColumn;
    }

     void setDateColumn(TableColumn<GradeFx, String> dateColumn) {
        this.dateColumn = dateColumn;
    }

     TableColumn<GradeFx, TeacherFx> getTeacherColumn() {
        return teacherColumn;
    }

     void setTeacherColumn(TableColumn<GradeFx, TeacherFx> teacherColumn) {
        this.teacherColumn = teacherColumn;
    }
}
