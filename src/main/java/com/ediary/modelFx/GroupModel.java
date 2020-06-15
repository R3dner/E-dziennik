package com.ediary.modelFx;

import com.ediary.converters.ConverterGroup;
import com.ediary.converters.ConverterSchool;
import com.ediary.database.dao.GroupDao;
import com.ediary.database.dao.SchoolDao;
import com.ediary.database.models.Group;
import com.ediary.database.models.School;
import com.ediary.database.utils.DbManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class GroupModel {

    private ObservableList<GroupFx> groupFxObservableList = FXCollections.observableArrayList();
    private ObservableList<SchoolFx> schoolFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<SchoolFx> schoolFxObjectProperty = new SimpleObjectProperty<>();

    private List<GroupFx> groupFxList = new ArrayList<>();

    public void init() {
        GroupDao groupDao = new GroupDao(DbManager.getConnectionSource());
        List<Group> groups = groupDao.queryForAll(Group.class);
        this.groupFxList.clear();

        groups.forEach(group -> {
            this.groupFxList.add(ConverterGroup.convertToGroupFx(group));
        });
        this.groupFxObservableList.setAll(groupFxList);

        initSchools();

        DbManager.closeConnectionSource();
    }

    private void initSchools(){
        SchoolDao schoolDao = new SchoolDao(DbManager.getConnectionSource());
        List<School> schoolList = schoolDao.queryForAll(School.class);
        this.schoolFxObservableList.clear();
        schoolList.forEach(school -> {
            SchoolFx schoolFx = ConverterSchool.convertToSchoolFx(school);
            this.schoolFxObservableList.add(schoolFx);
        });
        DbManager.closeConnectionSource();
    }

    public void initSchoolGroups(SchoolFx schoolFx){
        GroupDao groupDao = new GroupDao(DbManager.getConnectionSource());
        List<Group> groups = groupDao.queryForAll(Group.class);
        this.groupFxList.clear();
/*
        groups.forEach(group -> {
            System.out.println("sprawdzam");
            if(group.getSchool().getId() == ) {
                this.groupFxList.add(ConverterGroup.convertToGroupFx(group));
                System.out.println("dodaje");
            }
        });*/
        this.groupFxObservableList.setAll(groupFxList);

        DbManager.closeConnectionSource();
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

    public ObservableList<SchoolFx> getSchoolFxObservableList() {
        return schoolFxObservableList;
    }

    public void setSchoolFxObservableList(ObservableList<SchoolFx> schoolFxObservableList) {
        this.schoolFxObservableList = schoolFxObservableList;
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
}
