package com.example.ali.moviedb.RoomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ali.moviedb.Models.Movie;

import java.util.List;

/**
 * Created by ali on 2/11/2018.
 */

@Dao
public interface MovieDao {


    @Insert
    void insertAll(Movie... movies);

    @Query("SELECT * FROM Movie")
    List<Movie> getAll();

    @Delete
    void delete(Movie movie);
}
