package com.ediary.converters;

import com.ediary.database.models.School;
import com.ediary.modelFx.SchoolFx;

public class ConverterSchool {

    public static School convertToSchool(SchoolFx schoolFx){
        School school = new School();
        school.setId(schoolFx.getId());
        school.setName(schoolFx.getName());
        school.setAddress(schoolFx.getAddress());
        return school;
    }

    public static SchoolFx convertToSchoolFx(School school){
        SchoolFx schoolFx = new SchoolFx();
        schoolFx.setId(school.getId());
        schoolFx.setName(school.getName());
        schoolFx.setAddress(school.getAddress());
        return schoolFx;
    }

}
