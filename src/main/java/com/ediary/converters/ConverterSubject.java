package com.ediary.converters;

import com.ediary.database.models.Subject;
import com.ediary.modelFx.SubjectFx;

public class ConverterSubject {

    public static Subject convertToSubject(SubjectFx subjectFx){
        Subject subject = new Subject();
        subject.setId(subjectFx.getId());
        subject.setName(subjectFx.getName());
        return subject;
    }

    public static SubjectFx convertToSubjectFx(Subject subject){
        SubjectFx subjectFx = new SubjectFx();
        subjectFx.setId(subject.getId());
        subjectFx.setName(subject.getName());
        return subjectFx;
    }

}
