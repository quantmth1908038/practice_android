package com.demo.truongminhquan.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.truongminhquan.dao.ProductDao;
import com.demo.truongminhquan.entity.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract ProductDao productDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "Practice").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

}
