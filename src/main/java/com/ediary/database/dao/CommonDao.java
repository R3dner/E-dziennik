package com.ediary.database.dao;

import com.ediary.database.models.BaseModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import org.omg.CORBA.portable.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;


    public CommonDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public <T extends BaseModel, I> void createOrUpdate( BaseModel baseModel){
        Dao<T, I> dao = (Dao<T, I>) getDao(baseModel.getClass());
        try {
            dao.createOrUpdate((T)baseModel);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id){
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
        return null;
    }

    public <T extends BaseModel, I> void refresh( BaseModel baseModel){
        Dao<T, I> dao = (Dao<T, I>) getDao(baseModel.getClass());
        try {
            dao.refresh((T)baseModel);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id){
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void delete( BaseModel baseModel){
        Dao<T, I> dao = (Dao<T, I>) getDao(baseModel.getClass());
        try {
            dao.delete((T)baseModel);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
    }

    public <T extends BaseModel, I> Dao<T,I> getDao(Class<T> cls){
        try {
            return DaoManager.createDao(connectionSource,cls);
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage());
        }
        return null;
    }

    private void closeDbConnection(){
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls){
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
        return null;
    }

}
