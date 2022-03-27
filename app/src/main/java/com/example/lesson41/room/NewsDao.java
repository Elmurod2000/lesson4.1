package com.example.lesson41.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lesson41.ui.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News> sortAll();

    @Query("SELECT * FROM news WHERE title LIKE '%' || :search || '%'")
    List<News> getSearch(String search);
}
