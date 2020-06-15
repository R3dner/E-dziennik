package com.ediary.database.dao;

import com.ediary.database.models.Grade;
import com.j256.ormlite.support.ConnectionSource;

public class GradeDao extends CommonDao {
    public GradeDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }
}
