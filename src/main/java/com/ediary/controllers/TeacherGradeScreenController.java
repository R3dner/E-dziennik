package com.ediary.controllers;

import com.ediary.modelFx.*;
import com.sun.javafx.css.converters.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

public class TeacherGradeScreenController {

    private GradeModel gradeModel;
    private SubjectModel subjectModel;
    private TeacherModel teacherModel;

    @FXML
    private ComboBox<SubjectFx> subjectComboBox;

    @FXML
    private Button deleteGradeButton;

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
    public void  displayAllGrades(){
        gradeModel.initGradesForLoggedTeacher(teacherModel.getLoggedTeacher());
        addGradesToTableView();
    }

    @FXML
    public void initialize() {
        gradeModel = new GradeModel();
        subjectModel = new SubjectModel();
        teacherModel = new TeacherModel();

        //ładowanie przedmiotów i comboBoxa
        subjectModel.initSubjects();
        subjectComboBox.getItems().setAll(subjectModel.getSubjectFxList());

        //ładowanie ocen dla nauczyciela
        gradeModel.initGradesForLoggedTeacher(teacherModel.getLoggedTeacher());

        //table view
        addGradesToTableView();

        //listener do  combobox - przedmioty
        subjectComboBox.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
            gradeModel.subjectFxObjectPropertyProperty().bind(this.subjectComboBox.valueProperty());
            gradeModel.initGradesBySubjectForTeacher(teacherModel.getLoggedTeacher());
            addGradesToTableView();
        });

        //listener do przycisku usuwania ocen
        this.deleteGradeButton.disableProperty().bind(this.gradeModel.gradeFxFxObjectPropertyProperty().isNull());

        tableView.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
            gradeModel.gradeFxFxObjectPropertyProperty().bind(this.tableView.getSelectionModel().selectedItemProperty());
        });
        

    }

    public void addGradesToTableView() {
        this.tableView.setItems(this.gradeModel.getFilteredGradeFxObservableList());
        this.gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        this.subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectFxProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descProperty());
        this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().addedDateProperty());
        this.teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherFxProperty());
    }

    @FXML
    public void deleteGrade() {
        //gradeModel.setGradeFxFxObjectProperty(this.tableView.getSelectionModel().getSelectedItem());
        gradeModel.deleteGradeById();
    }

    //for tests


    ComboBox<SubjectFx> getSubjectComboBox() {
        return subjectComboBox;
    }

    void setSubjectComboBox(ComboBox<SubjectFx> subjectComboBox) {
        this.subjectComboBox = subjectComboBox;
    }

    Button getDeleteGradeButton() {
        return deleteGradeButton;
    }

    void setDeleteGradeButton(Button deleteGradeButton) {
        this.deleteGradeButton = deleteGradeButton;
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

    GradeModel getGradeModel() {
        return gradeModel;
    }

    void setGradeModel(GradeModel gradeModel) {
        this.gradeModel = gradeModel;
    }

    SubjectModel getSubjectModel() {
        return subjectModel;
    }

    void setSubjectModel(SubjectModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    TeacherModel getTeacherModel() {
        return teacherModel;
    }

    void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }
}
