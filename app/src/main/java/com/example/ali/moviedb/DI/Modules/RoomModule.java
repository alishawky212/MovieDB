package com.example.ali.moviedb.DI.Modules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ali.moviedb.RoomDB.MovieDao;
import com.example.ali.moviedb.RoomDB.RoomDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ali on 2/12/2018.
 */

@Module
public class RoomModule {

    private Context context;

    public RoomModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public RoomDB provideMyDatabase(Context context) {
        return Room.databaseBuilder(context, RoomDB.class, "movie").build();
    }

    @Singleton
    @Provides
    public MovieDao provideUserDao(RoomDB myDatabase) {
        return myDatabase.movieDao();
    }
}

