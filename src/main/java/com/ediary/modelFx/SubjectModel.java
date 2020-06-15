package com.ediary.modelFx;

import com.ediary.converters.ConverterSubject;
import com.ediary.database.dao.SubjectDao;
import com.ediary.database.models.Subject;
import com.ediary.database.utils.DbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SubjectModel {

    private ObservableList<SubjectFx> subjectFxObservableList = FXCollections.observableArrayList();
    private List<SubjectFx> subjectFxList = new ArrayList<>();

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
}
