package com.dfp.test.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AdInfoDAO {

    @Query("SELECT * FROM adinfo ORDER BY id DESC")
    List<AdInfo> getAll();

    @Insert
    void insertAll(AdInfo... adInfos);

    @Delete
    void delete(AdInfo adInfo);
}
