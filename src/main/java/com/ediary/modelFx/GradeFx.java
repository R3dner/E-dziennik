package com.ediary.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class GradeFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty grade = new SimpleIntegerProperty();
    private StringProperty desc = new SimpleStringProperty();
    private StringProperty addedDate = new SimpleStringProperty();
    private ObjectProperty<StudentFx> studentFx = new SimpleObjectProperty<>();
    private ObjectProperty<SubjectFx> subjectFx = new SimpleObjectProperty<>();
    private ObjectProperty<TeacherFx> teacherFx = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getGrade() {
        return grade.get();
    }

    public IntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public String getAddedDate() {
        return addedDate.get();
    }

    public StringProperty addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate.set(addedDate);
    }

    public StudentFx getStudentFx() {
        return studentFx.get();
    }

    public ObjectProperty<StudentFx> studentFxProperty() {
        return studentFx;
    }

    public void setStudentFx(StudentFx studentFx) {
        this.studentFx.set(studentFx);
    }

    public SubjectFx getSubjectFx() {
        return subjectFx.get();
    }

    public ObjectProperty<SubjectFx> subjectFxProperty() {
        return subjectFx;
    }

    public void setSubjectFx(SubjectFx subjectFx) {
        this.subjectFx.set(subjectFx);
    }

    public TeacherFx getTeacherFx() {
        return teacherFx.get();
    }

    public ObjectProperty<TeacherFx> teacherFxProperty() {
        return teacherFx;
    }

    public void setTeacherFx(TeacherFx teacherFx) {
        this.teacherFx.set(teacherFx);
    }
}
