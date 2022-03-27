package com.example.lesson41.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lesson41.ui.model.News;

@Database(entities = {News.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

}
