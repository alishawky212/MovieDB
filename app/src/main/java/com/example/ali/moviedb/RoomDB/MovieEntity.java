package com.example.ali.moviedb.RoomDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ali on 2/11/2018.
 */

@Entity(tableName = "Movie")
public class MovieEntity {

    @PrimaryKey
    public Integer id;
    public String Title;
    public String Image;
    public String Image2;
    public String OverView;
    public Integer Rating;
    public String Date;


}
