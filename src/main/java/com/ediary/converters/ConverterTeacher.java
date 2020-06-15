package com.ediary.converters;

import com.ediary.database.models.Teacher;
import com.ediary.modelFx.TeacherFx;


public class ConverterTeacher {

    public static Teacher convertToTeacher(TeacherFx teacherFx){
        Teacher teacher = new Teacher();
        teacher.setId(teacherFx.getId());
        teacher.setName(teacherFx.getName());
        teacher.setSurname(teacherFx.getSurname());
        teacher.setDateOfBirth(teacherFx.getDateOfBirth());
        teacher.setEmail(teacherFx.getEmail());
        teacher.setPhoneNumber(teacherFx.getPhoneNumber());
        teacher.setSchool(ConverterSchool.convertToSchool(teacherFx.getSchoolFx()));
        teacher.setAddress(teacherFx.getAddress());
        teacher.setPassword(teacherFx.getPassword());
        return teacher;
    }

    public static TeacherFx convertToTeacherFx(Teacher teacher){
        TeacherFx teacherFx = new TeacherFx();
        teacherFx.setId(teacher.getId());
        teacherFx.setName(teacher.getName());
        teacherFx.setSurname(teacher.getSurname());
        teacherFx.setDateOfBirth(teacher.getDateOfBirth());
        teacherFx.setEmail(teacher.getEmail());
        teacherFx.setPhoneNumber(teacher.getPhoneNumber());
        teacherFx.setSchoolFx(ConverterSchool.convertToSchoolFx(teacher.getSchool()));
        teacherFx.setAddress(teacher.getAddress());
        teacherFx.setPassword(teacher.getPassword());
        return teacherFx;
    }

}
