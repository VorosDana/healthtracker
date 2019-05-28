package com.example.healthtracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={Exercise.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
}
