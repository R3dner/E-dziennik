package com.ediary.converters;

import com.ediary.database.models.Group;
import com.ediary.modelFx.GroupFx;

import java.nio.file.attribute.GroupPrincipal;

public class ConverterGroup {

    public static Group convertToGroup(GroupFx groupFx){
        Group group = new Group();
        group.setId(groupFx.getId());
        group.setName(groupFx.getName());
        group.setSchool(ConverterSchool.convertToSchool(groupFx.getSchoolFx()));
        return group;
    }

    public static GroupFx convertToGroupFx(Group group){
        GroupFx groupFx = new GroupFx();
        groupFx.setId(group.getId());
        groupFx.setName(group.getName());
        groupFx.setSchoolFx(ConverterSchool.convertToSchoolFx(group.getSchool()));
        return groupFx;
    }

}
