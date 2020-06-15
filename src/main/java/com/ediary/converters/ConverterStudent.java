package com.ediary.converters;

import com.ediary.database.models.Student;
import com.ediary.modelFx.StudentFx;

public class ConverterStudent {

    public static Student convertToStudent(StudentFx studentFx){
        Student student = new Student();
        student.setId(studentFx.getId());
        student.setName(studentFx.getName());
        student.setSurname(studentFx.getSurname());
        student.setDateOfBirth(studentFx.getDateOfBirth());
        student.setEmail(studentFx.getEmail());
        student.setPhoneNumber(studentFx.getPhoneNumber());
        student.setGroup(ConverterGroup.convertToGroup(studentFx.getGroupFx()));
        student.setSchool(ConverterSchool.convertToSchool(studentFx.getSchoolFx()));
        student.setAddress(studentFx.getAddress());
        student.setPassword(studentFx.getPassword());
        return student;
    }

    public static StudentFx convertToStudentFx(Student student){
        StudentFx studentFx = new StudentFx();
        studentFx.setId(student.getId());
        studentFx.setName(student.getName());
        studentFx.setSurname(student.getSurname());
        studentFx.setDateOfBirth(student.getDateOfBirth());
        studentFx.setEmail(student.getEmail());
        studentFx.setPhoneNumber(student.getPhoneNumber());
        studentFx.setGroupFx(ConverterGroup.convertToGroupFx(student.getGroup()));
        studentFx.setSchoolFx(ConverterSchool.convertToSchoolFx(student.getSchool()));
        studentFx.setAddress(student.getAddress());
        studentFx.setPassword(student.getPassword());
        return studentFx;
    }

}
