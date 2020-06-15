package com.ediary.database.utils;

import com.ediary.database.models.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:database.db";
    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        //dropTables();
        createTables();
        closeConnectionSource();
    }

    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null)
            createConnectionSource();

        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource!=null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTables(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, School.class);
            TableUtils.createTableIfNotExists(connectionSource, Group.class);
            TableUtils.createTableIfNotExists(connectionSource, Subject.class);
            TableUtils.createTableIfNotExists(connectionSource, Student.class);
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            TableUtils.createTableIfNotExists(connectionSource, Grade.class);


        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

    private static void dropTables(){
        try {
            TableUtils.dropTable(connectionSource, Student.class, true);
            TableUtils.dropTable(connectionSource, Teacher.class, true);
            TableUtils.dropTable(connectionSource, School.class, true);
            TableUtils.dropTable(connectionSource, Group.class, true);
            TableUtils.dropTable(connectionSource, Grade.class, true);
            TableUtils.dropTable(connectionSource, Subject.class, true);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

}
