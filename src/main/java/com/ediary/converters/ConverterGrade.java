package com.ediary.converters;

import com.ediary.database.models.Grade;
import com.ediary.database.models.School;
import com.ediary.modelFx.GradeFx;
import com.ediary.modelFx.SchoolFx;

public class ConverterGrade {

    public static Grade convertToGrade(GradeFx gradeFx){
        Grade grade = new Grade();
        grade.setId(gradeFx.getId());
        grade.setGrade(gradeFx.getGrade());
        grade.setDesc(gradeFx.getDesc());
        grade.setAdded_date(gradeFx.getAddedDate());
        grade.setStudent(ConverterStudent.convertToStudent(gradeFx.getStudentFx()));
        grade.setSubject(ConverterSubject.convertToSubject(gradeFx.getSubjectFx()));
        grade.setTeacher(ConverterTeacher.convertToTeacher(gradeFx.getTeacherFx()));
        return grade;
    }

    public static GradeFx convertToGradeFx(Grade grade){
        GradeFx gradeFx = new GradeFx();
        gradeFx.setId(grade.getId());
        gradeFx.setGrade(grade.getGrade());
        gradeFx.setDesc(grade.getDesc());
        gradeFx.setAddedDate(grade.getAdded_date());
        gradeFx.setStudentFx(ConverterStudent.convertToStudentFx(grade.getStudent()));
        gradeFx.setSubjectFx(ConverterSubject.convertToSubjectFx(grade.getSubject()));
        gradeFx.setTeacherFx(ConverterTeacher.convertToTeacherFx(grade.getTeacher()));
        return gradeFx;
    }

}
