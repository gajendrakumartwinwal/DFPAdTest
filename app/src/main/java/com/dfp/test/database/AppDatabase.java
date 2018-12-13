package com.dfp.test.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {AdInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AdInfoDAO adInfoDAO();
}