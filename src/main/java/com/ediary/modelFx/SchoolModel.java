package com.ediary.modelFx;

import com.ediary.converters.ConverterSchool;
import com.ediary.converters.ConverterStudent;
import com.ediary.database.dao.SchoolDao;
import com.ediary.database.dao.StudentDao;
import com.ediary.database.models.School;
import com.ediary.database.models.Student;
import com.ediary.database.utils.DbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SchoolModel {

    private ObservableList<SchoolFx> schoolFxObservableList = FXCollections.observableArrayList();

    private List<SchoolFx> schoolFxList = new ArrayList<>();

    public void init() {
        SchoolDao schoolDao = new SchoolDao(DbManager.getConnectionSource());
        List<School> schools = schoolDao.queryForAll(School.class);
        this.schoolFxList.clear();

        schools.forEach(school -> {
            this.schoolFxList.add(ConverterSchool.convertToSchoolFx(school));
        });
        this.schoolFxObservableList.setAll(schoolFxList);

        DbManager.closeConnectionSource();
    }

    public ObservableList<SchoolFx> getSchoolFxObservableList() {
        return schoolFxObservableList;
    }

    public void setSchoolFxObservableList(ObservableList<SchoolFx> schoolFxObservableList) {
        this.schoolFxObservableList = schoolFxObservableList;
    }

    public List<SchoolFx> getSchoolFxList() {
        return schoolFxList;
    }

    public void setSchoolFxList(List<SchoolFx> schoolFxList) {
        this.schoolFxList = schoolFxList;
    }

}
