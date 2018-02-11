package com.example.ali.moviedb.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ali on 2/11/2018.
 */

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static final String DATABASE_NAME = "movie";
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile RoomDB sInstance;

    public static RoomDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, RoomDB.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    public abstract MovieDao movieDao();

}
