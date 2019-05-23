package com.example.healthtracker.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM Exercise WHERE title = :title")
    Exercise findByTitle(String title);

    @Insert
    void add(Exercise ex);

    @Update
    void update(Exercise ex);

    @Delete
    void delete(Exercise ex);
}
