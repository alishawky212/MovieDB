package com.example.ali.moviedb;

import android.app.Application;
import android.content.Context;

import com.example.ali.moviedb.DI.Components.APPComponent;
import com.example.ali.moviedb.DI.Components.DaggerAPPComponent;
import com.example.ali.moviedb.DI.Modules.RoomModule;


/**
 * Created by ali on 2/4/2018.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static APPComponent appComponent;

    private static MyApplication instance;


    public static Context getContext(){
        return mContext;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        setInstance(this);

        appComponent = DaggerAPPComponent.builder().roomModule(new RoomModule(this)).build();

    }

    public  APPComponent getComponent() {
        return appComponent;
    }

}
