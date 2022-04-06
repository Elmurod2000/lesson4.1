package com.example.lesson41;

import android.app.Application;

import androidx.room.Room;

import com.example.lesson41.room.AppDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class App extends Application {
 private static AppDatabase database;

private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
