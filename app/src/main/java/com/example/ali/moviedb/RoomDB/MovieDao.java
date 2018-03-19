package com.example.ali.moviedb.RoomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ali.moviedb.Models.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by ali on 2/11/2018.
 */

@Dao
public interface MovieDao {


    @Insert
    void insert(Movie... movies);

    @Query("SELECT * FROM Movie WHERE id = :Id")
    Maybe<Movie> ISFavorit(int Id);


    @Query("SELECT * FROM Movie")
    Flowable<List<Movie>> getAll();

    @Delete
    void delete(Movie movie);
}
