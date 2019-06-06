package com.hondorchen.mvpapp.db.dao;

import com.hondorchen.mvpapp.db.entity.NewsTypeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

/**
 * Created by hondor on 2019/6/5
 */
@Dao
public interface NewsTypeDao {

    @Insert
    void insert(NewsTypeEntity NewsTypeEntity);

    @Insert
    void insertAll(List<NewsTypeEntity> userEntities);

    @Delete
    void delete(NewsTypeEntity NewsTypeEntity);

    @Delete
    void deleteAll(List<NewsTypeEntity> userEntities);

    @Update
    void update(NewsTypeEntity NewsTypeEntity);

    @Query("SELECT * FROM NewsTypeEntity")
    Single<List<NewsTypeEntity>> getAll();

    @Query("SELECT * FROM NewsTypeEntity WHERE uid  = :uid")
    NewsTypeEntity getByUid(int uid);
}
