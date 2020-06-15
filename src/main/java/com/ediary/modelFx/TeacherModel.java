package com.ediary.modelFx;

import com.ediary.converters.*;
import com.ediary.database.dao.GroupDao;
import com.ediary.database.dao.StudentDao;
import com.ediary.database.dao.SubjectDao;
import com.ediary.database.dao.TeacherDao;
import com.ediary.database.models.Group;
import com.ediary.database.models.Student;
import com.ediary.database.models.Subject;
import com.ediary.database.models.Teacher;
import com.ediary.database.utils.DbManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TeacherModel {
    private ObservableList<TeacherFx> teacherFxObservableList = FXCollections.observableArrayList();
    private ObservableList<GroupFx> groupFxObservableList = FXCollections.observableArrayList();
    private ObservableList<StudentFx> studentFxObservableList = FXCollections.observableArrayList();
    private ObservableList<SubjectFx> subjectFxObservableList = FXCollections.observableArrayList();

    private List<GroupFx> groupFxList = new ArrayList<>();
    private List<TeacherFx> teacherFxList = new ArrayList<>();
    private List<StudentFx> studentFxList = new ArrayList<>();
    private List<SubjectFx> subjectFxList = new ArrayList<>();
    private static Teacher loggedTeacher;

    private ObjectProperty<SchoolFx> schoolFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<GroupFx> groupFxObjectProperty = new SimpleObjectProperty<>();

    private List<GroupFx> filteredGroupFxList = new ArrayList<>();
    private List<StudentFx> filteredStudentFxList = new ArrayList<>();

    public void init() {
        TeacherDao teacherDao = new TeacherDao(DbManager.getConnectionSource());
        List<Teacher> teachers = teacherDao.queryForAll(Teacher.class);
        this.teacherFxList.clear();

        teachers.forEach(teacher -> {
            this.teacherFxList.add(ConverterTeacher.convertToTeacherFx(teacher));
        });
        this.teacherFxObservableList.setAll(teacherFxList);

        initGroups();
        //initGroupsByLoggedTeacherSchool();

        DbManager.closeConnectionSource();
    }

    private void initGroups(){
        GroupDao groupDao = new GroupDao(DbManager.getConnectionSource());
        List<Group> groupList = groupDao.queryForAll(Group.class);
        this.groupFxObservableList.clear();
        groupList.forEach(group -> {
            GroupFx groupFx = ConverterGroup.convertToGroupFx(group);
            this.groupFxList.add(groupFx);
        });
        this.groupFxObservableList.addAll(groupFxList);
        DbManager.closeConnectionSource();
    }

    public void initStudentsByGroup(){
        StudentDao studentDao = new StudentDao(DbManager.getConnectionSource());
        List<Student> studentList = studentDao.queryForAll(Student.class);
        this.studentFxObservableList.clear();
        this.filteredStudentFxList.clear();
        studentList.forEach( student -> {
            if(student.getGroup().getId() == this.getGroupFxObjectProperty().getId()){
                System.out.println("dodaje studenta");
                this.filteredStudentFxList.add((ConverterStudent.convertToStudentFx(student)));
            }
        });
        this.studentFxObservableList.setAll(filteredStudentFxList);
    }

    public void initSubjects(){
        SubjectDao subjectDao = new SubjectDao(DbManager.getConnectionSource());
        List<Subject> subjectList = subjectDao.queryForAll(Subject.class);
        this.subjectFxObservableList.clear();
        subjectList.forEach(subject -> {
            SubjectFx subjectFx = ConverterSubject.convertToSubjectFx(subject);
            this.subjectFxList.add(subjectFx);
        });
        this.subjectFxObservableList.addAll(subjectFxList);
        DbManager.closeConnectionSource();
    }

    public void initGroupsByLoggedTeacherSchool(){
        GroupDao groupDao = new GroupDao(DbManager.getConnectionSource());
        List<Group> groups = groupDao.queryForAll(Group.class);
        this.filteredGroupFxList.clear();

        groups.forEach(group -> {
            System.out.println("sprawdzam lgt" + loggedTeacher.getSchool().getId());
            if(group.getSchool().getId() == this.loggedTeacher.getSchool().getId()) {
                this.filteredGroupFxList.add(ConverterGroup.convertToGroupFx(group));
                System.out.println("dodaje");
            }
        });
        this.groupFxObservableList.setAll(filteredGroupFxList);

        DbManager.closeConnectionSource();
    }

    public void saveTeacherInDatabase(String name, String surname, String dateOfBirth,
                                      String phoneNumber, String address,
                                      String email, String password, SchoolFx schoolFx){
        TeacherDao teacherDao = new TeacherDao(DbManager.getConnectionSource());
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setDateOfBirth(dateOfBirth);
        teacher.setAddress(address);
        teacher.setPassword(password);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setEmail(email);
        teacher.setSchool(ConverterSchool.convertToSchool(schoolFx));
        teacherDao.createOrUpdate(teacher);
        DbManager.closeConnectionSource();
        init();
    }

    public boolean emailIsInDatabase(String email){
        TeacherDao teacherDao = new TeacherDao(DbManager.getConnectionSource());
        List<Teacher> teachers = teacherDao.queryForAll(Teacher.class);
        this.teacherFxObservableList.clear();
        for (Teacher t : teachers) {
            if (email.equals(t.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogIn(String email, String password){
        TeacherDao teacherDao = new TeacherDao(DbManager.getConnectionSource());
        List<Teacher> teachers = teacherDao.queryForAll(Teacher.class);
        this.teacherFxObservableList.clear();
        for (Teacher t : teachers) {
            if (email.equals(t.getEmail()) && password.equals(t.getPassword())) {
                loggedTeacher = t;
                return true;
            }
        }return false;
    }

    public ObservableList<SubjectFx> getSubjectFxObservableList() {
        return subjectFxObservableList;
    }

    public void setSubjectFxObservableList(ObservableList<SubjectFx> subjectFxObservableList) {
        this.subjectFxObservableList = subjectFxObservableList;
    }

    public List<SubjectFx> getSubjectFxList() {
        return subjectFxList;
    }

    public void setSubjectFxList(List<SubjectFx> subjectFxList) {
        this.subjectFxList = subjectFxList;
    }

    public List<StudentFx> getFilteredStudentFxList() {
        return filteredStudentFxList;
    }

    public void setFilteredStudentFxList(List<StudentFx> filteredStudentFxList) {
        this.filteredStudentFxList = filteredStudentFxList;
    }

    public ObservableList<TeacherFx> getTeacherFxObservableList() {
        return teacherFxObservableList;
    }

    public void setTeacherFxObservableList(ObservableList<TeacherFx> teacherFxObservableList) {
        this.teacherFxObservableList = teacherFxObservableList;
    }

    public static Teacher getLoggedTeacher() {
        return loggedTeacher;
    }

    public static void setLoggedTeacher(Teacher loggedTeacher) {
        TeacherModel.loggedTeacher = loggedTeacher;
    }

    public ObservableList<GroupFx> getGroupFxObservableList() {
        return groupFxObservableList;
    }

    public void setGroupFxObservableList(ObservableList<GroupFx> groupFxObservableList) {
        this.groupFxObservableList = groupFxObservableList;
    }

    public List<GroupFx> getGroupFxList() {
        return groupFxList;
    }

    public void setGroupFxList(List<GroupFx> groupFxList) {
        this.groupFxList = groupFxList;
    }

    public List<TeacherFx> getTeacherFxList() {
        return teacherFxList;
    }

    public void setTeacherFxList(List<TeacherFx> teacherFxList) {
        this.teacherFxList = teacherFxList;
    }

    public List<GroupFx> getFilteredGroupFxList() {
        return filteredGroupFxList;
    }

    public void setFilteredGroupFxList(List<GroupFx> filteredGroupFxList) {
        this.filteredGroupFxList = filteredGroupFxList;
    }

    public SchoolFx getSchoolFxObjectProperty() {
        return schoolFxObjectProperty.get();
    }

    public ObjectProperty<SchoolFx> schoolFxObjectPropertyProperty() {
        return schoolFxObjectProperty;
    }

    public void setSchoolFxObjectProperty(SchoolFx schoolFxObjectProperty) {
        this.schoolFxObjectProperty.set(schoolFxObjectProperty);
    }

    public GroupFx getGroupFxObjectProperty() {
        return groupFxObjectProperty.get();
    }

    public ObjectProperty<GroupFx> groupFxObjectPropertyProperty() {
        return groupFxObjectProperty;
    }

    public void setGroupFxObjectProperty(GroupFx groupFxObjectProperty) {
        this.groupFxObjectProperty.set(groupFxObjectProperty);
    }

    public ObservableList<StudentFx> getStudentFxObservableList() {
        return studentFxObservableList;
    }

    public void setStudentFxObservableList(ObservableList<StudentFx> studentFxObservableList) {
        this.studentFxObservableList = studentFxObservableList;
    }

    public List<StudentFx> getStudentFxList() {
        return studentFxList;
    }

    public void setStudentFxList(List<StudentFx> studentFxList) {
        this.studentFxList = studentFxList;
    }
}
