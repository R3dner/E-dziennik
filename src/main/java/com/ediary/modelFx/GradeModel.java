package com.ediary.modelFx;

import com.ediary.converters.ConverterGrade;
import com.ediary.converters.ConverterStudent;
import com.ediary.converters.ConverterSubject;
import com.ediary.converters.ConverterTeacher;
import com.ediary.database.dao.GradeDao;
import com.ediary.database.dao.StudentDao;
import com.ediary.database.models.Grade;
import com.ediary.database.models.Student;
import com.ediary.database.models.Teacher;
import com.ediary.database.utils.DbManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GradeModel {

    private ObservableList<GradeFx> filteredGradeFxObservableList = FXCollections.observableArrayList();
    private List<GradeFx> filteredGradeFxList = new ArrayList<>();
    private ObjectProperty<SubjectFx> subjectFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<GradeFx> gradeFxFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<GroupFx> groupFxFxObjectProperty = new SimpleObjectProperty<>();


    public void saveGradeInDatabase(int grade, String desc, String added_date,
                                    StudentFx studentFx, SubjectFx subjectFx, TeacherFx teacherFx){

        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        Grade gradeObj = new Grade();
        gradeObj.setGrade(grade);
        gradeObj.setDesc(desc);
        gradeObj.setAdded_date(added_date);
        gradeObj.setStudent(ConverterStudent.convertToStudent(studentFx));
        gradeObj.setSubject(ConverterSubject.convertToSubject(subjectFx));
        gradeObj.setTeacher(ConverterTeacher.convertToTeacher(teacherFx));
        gradeDao.createOrUpdate(gradeObj);
        DbManager.closeConnectionSource();

    }

    public void init(){
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        List<Grade> studentList = gradeDao.queryForAll(Grade.class);
        this.filteredGradeFxList.clear();
        this.filteredGradeFxObservableList.clear();
        studentList.forEach( grade -> {
                System.out.println("dodaje ocene");
                this.filteredGradeFxList.add((ConverterGrade.convertToGradeFx(grade)));
        });
        this.filteredGradeFxObservableList.setAll(filteredGradeFxList);
    }

    public void initGradesForLoggedStudent(Student student){
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        List<Grade> gradeList = gradeDao.queryForAll(Grade.class);
        this.filteredGradeFxList.clear();
        this.filteredGradeFxObservableList.clear();
        gradeList.forEach( grade -> {
            if(grade.getStudent().getId() == student.getId()){
                System.out.println("dodaje ocene");
                this.filteredGradeFxList.add((ConverterGrade.convertToGradeFx(grade)));
            }
        });
        this.filteredGradeFxObservableList.setAll(filteredGradeFxList);
    }

    public void initGradesForLoggedTeacher(Teacher teacher){
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        List<Grade> gradeList = gradeDao.queryForAll(Grade.class);
        this.filteredGradeFxList.clear();
        this.filteredGradeFxObservableList.clear();
        gradeList.forEach( grade -> {
            if(grade.getTeacher().getId() == teacher.getId()){
                System.out.println("dodaje ocene");
                this.filteredGradeFxList.add((ConverterGrade.convertToGradeFx(grade)));
            }
        });
        this.filteredGradeFxObservableList.setAll(filteredGradeFxList);
    }

    public void initGradesBySubjectForStudent(Student student){
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        List<Grade> gradeList = gradeDao.queryForAll(Grade.class);
        this.filteredGradeFxList.clear();
        this.filteredGradeFxObservableList.clear();
        gradeList.forEach( grade -> {
            if(grade.getSubject().getId() == this.getSubjectFxObjectProperty().getId()
            && grade.getStudent().getId() == student.getId()){
                System.out.println("dodaje ocene");
                this.filteredGradeFxList.add((ConverterGrade.convertToGradeFx(grade)));
            }
        });
        this.filteredGradeFxObservableList.setAll(filteredGradeFxList);
    }

    public void initGradesBySubjectForTeacher(Teacher teacher){
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        List<Grade> gradeList = gradeDao.queryForAll(Grade.class);
        this.filteredGradeFxList.clear();
        this.filteredGradeFxObservableList.clear();
        gradeList.forEach( grade -> {
            if(grade.getSubject().getId() == this.getSubjectFxObjectProperty().getId()
                    && grade.getTeacher().getId() == teacher.getId()){
                System.out.println("dodaje ocene");
                this.filteredGradeFxList.add((ConverterGrade.convertToGradeFx(grade)));
            }
        });
        this.filteredGradeFxObservableList.setAll(filteredGradeFxList);
    }

    public void deleteGradeById() {
        GradeDao gradeDao = new GradeDao(DbManager.getConnectionSource());
        gradeDao.deleteById(Grade.class, gradeFxFxObjectProperty.getValue().getId());
        init();
    }

    public GradeFx getGradeFxFxObjectProperty() {
        return gradeFxFxObjectProperty.get();
    }

    public ObjectProperty<GradeFx> gradeFxFxObjectPropertyProperty() {
        return gradeFxFxObjectProperty;
    }

    public void setGradeFxFxObjectProperty(GradeFx gradeFxFxObjectProperty) {
        this.gradeFxFxObjectProperty.set(gradeFxFxObjectProperty);
    }

    public SubjectFx getSubjectFxObjectProperty() {
        return subjectFxObjectProperty.get();
    }

    public ObjectProperty<SubjectFx> subjectFxObjectPropertyProperty() {
        return subjectFxObjectProperty;
    }

    public void setSubjectFxObjectProperty(SubjectFx subjectFxObjectProperty) {
        this.subjectFxObjectProperty.set(subjectFxObjectProperty);
    }

    public ObservableList<GradeFx> getFilteredGradeFxObservableList() {
        return filteredGradeFxObservableList;
    }

    public void setFilteredGradeFxObservableList(ObservableList<GradeFx> filteredGradeFxObservableList) {
        this.filteredGradeFxObservableList = filteredGradeFxObservableList;
    }

    public List<GradeFx> getFilteredGradeFxList() {
        return filteredGradeFxList;
    }

    public void setFilteredGradeFxList(List<GradeFx> filteredGradeFxList) {
        this.filteredGradeFxList = filteredGradeFxList;
    }
}
