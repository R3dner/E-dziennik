package com.ediary.modelFx;

import com.ediary.converters.ConverterGroup;
import com.ediary.converters.ConverterSchool;
import com.ediary.converters.ConverterStudent;
import com.ediary.database.dao.GroupDao;
import com.ediary.database.dao.SchoolDao;
import com.ediary.database.dao.StudentDao;
import com.ediary.database.models.Group;
import com.ediary.database.models.School;
import com.ediary.database.models.Student;
import com.ediary.database.utils.DbManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class StudentModel {

    private ObservableList<StudentFx> studentFxObservableList = FXCollections.observableArrayList();
    private ObservableList<GroupFx> groupFxObservableList = FXCollections.observableArrayList();
    private ObservableList<SchoolFx> schoolFxObservableList = FXCollections.observableArrayList();

    private List<StudentFx> studentFxList = new ArrayList<>();
    private List<SchoolFx> schoolFxList = new ArrayList<>();
    private List<GroupFx> groupFxList = new ArrayList<>();

    private List<GroupFx> filteredGroupFxList = new ArrayList<>();

    private ObjectProperty<SchoolFx> schoolFxObjectProperty = new SimpleObjectProperty<>();

    private static Student loggedStudent = new Student();


    public void init() {
        StudentDao studentDao = new StudentDao(DbManager.getConnectionSource());
        List<Student> students = studentDao.queryForAll(Student.class);
        this.studentFxList.clear();

        students.forEach(student -> {
            this.studentFxList.add(ConverterStudent.convertToStudentFx(student));
        });
        this.studentFxObservableList.setAll(studentFxList);

        initGroups();
        initSchools();

        DbManager.closeConnectionSource();

        /*if (!students.isEmpty()) {
            students.forEach(c -> {
                StudentFx studentFx = new StudentFx();
                studentFx.setId(c.getId());
                studentFx.setName(c.getName());
                studentFx.setSurname(c.getSurname());
                studentFx.setAddress(c.getAddress());
                studentFx.setDateOfBirth(c.getDateOfBirth());
                studentFx.setPhoneNumber(c.getPhoneNumber());
                studentFx.setGroupFx(ConverterGroup.convertToGroupFx(c.getGroup()));
                studentFx.setEmail(c.getEmail());
                studentFx.setPassword(c.getPassword());
                studentFx.setSchoolFx(ConverterSchool.convertToSchoolFx(c.getSchool()));
                this.studentFxObservableList.add(studentFx);
            });
            DbManager.closeConnectionSource();*/
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

    private void initSchools(){
        SchoolDao schoolDao = new SchoolDao(DbManager.getConnectionSource());
        List<School> schoolList = schoolDao.queryForAll(School.class);
        this.schoolFxObservableList.clear();
        schoolList.forEach(school -> {
            SchoolFx schoolFx = ConverterSchool.convertToSchoolFx(school);
            this.schoolFxList.add(schoolFx);
        });
        this.schoolFxObservableList.addAll(schoolFxList);
        DbManager.closeConnectionSource();
    }

    public void initSchoolGroups(){
        GroupDao groupDao = new GroupDao(DbManager.getConnectionSource());
        List<Group> groups = groupDao.queryForAll(Group.class);
        this.filteredGroupFxList.clear();

        groups.forEach(group -> {
            System.out.println("sprawdzam");
            if(group.getSchool().getId() == this.getSchoolFxObjectProperty().getId()) {
                this.filteredGroupFxList.add(ConverterGroup.convertToGroupFx(group));
                System.out.println("dodaje");
            }
        });
        this.groupFxObservableList.setAll(filteredGroupFxList);

        DbManager.closeConnectionSource();
    }


    public boolean emailIsInDatabase(String email){
        StudentDao studentDao = new StudentDao(DbManager.getConnectionSource());
        List<Student> students = studentDao.queryForAll(Student.class);
        this.studentFxObservableList.clear();
        for (Student s : students) {
            if (email.equals(s.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogIn(String email, String password){
        StudentDao studentDao = new StudentDao(DbManager.getConnectionSource());
        List<Student> students = studentDao.queryForAll(Student.class);
        this.studentFxObservableList.clear();
        for (Student s : students) {
            if (email.equals(s.getEmail()) && password.equals(s.getPassword())) {
                this.loggedStudent = s;
                return true;
            }
        }return false;
    }

    public void saveStudentInDatabase(String name, String surname, String dateOfBirth,
                                      String phoneNumber, String address,
                                      String email, String password,SchoolFx schoolFx,
                                      GroupFx groupFx){
        StudentDao studentDao = new StudentDao(DbManager.getConnectionSource());
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setDateOfBirth(dateOfBirth);
        student.setAddress(address);
        student.setPassword(password);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);
        student.setSchool(ConverterSchool.convertToSchool(schoolFx));
        student.setGroup(ConverterGroup.convertToGroup(groupFx));
        studentDao.createOrUpdate(student);
        DbManager.closeConnectionSource();
        init();
    }

    public ObservableList<StudentFx> getStudentFxObservableList() {
        return studentFxObservableList;
    }

    public void setStudentFxObservableList(ObservableList<StudentFx> studentFxObservableList) {
        this.studentFxObservableList = studentFxObservableList;
    }

    public ObservableList<GroupFx> getGroupFxObservableList() {
        return groupFxObservableList;
    }

    public void setGroupFxObservableList(ObservableList<GroupFx> groupFxObservableList) {
        this.groupFxObservableList = groupFxObservableList;
    }

    public ObservableList<SchoolFx> getSchoolFxObservableList() {
        return schoolFxObservableList;
    }

    public void setSchoolFxObservableList(ObservableList<SchoolFx> schoolFxObservableList) {
        this.schoolFxObservableList = schoolFxObservableList;
    }

    public List<StudentFx> getStudentFxList() {
        return studentFxList;
    }

    public void setStudentFxList(List<StudentFx> studentFxList) {
        this.studentFxList = studentFxList;
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }

    public List<SchoolFx> getSchoolFxList() {
        return schoolFxList;
    }

    public void setSchoolFxList(List<SchoolFx> schoolFxList) {
        this.schoolFxList = schoolFxList;
    }

    public List<GroupFx> getGroupFxList() {
        return groupFxList;
    }

    public void setGroupFxList(List<GroupFx> groupFxList) {
        this.groupFxList = groupFxList;
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

    public List<GroupFx> getFilteredGroupFxList() {
        return filteredGroupFxList;
    }

    public void setFilteredGroupFxList(List<GroupFx> filteredGroupFxList) {
        this.filteredGroupFxList = filteredGroupFxList;
    }

}
