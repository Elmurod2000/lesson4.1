package com.example.lesson41;

import android.app.Application;

import androidx.room.Room;

import com.example.lesson41.room.AppDatabase;

public class App extends Application {
 private static AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        database= Room
                .databaseBuilder(this,AppDatabase.class,"database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getDatabase(){
        return database;
    }
}
