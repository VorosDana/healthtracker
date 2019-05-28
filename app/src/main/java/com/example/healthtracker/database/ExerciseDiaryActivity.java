package com.example.healthtracker.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.healthtracker.R;

public class ExerciseDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        ExerciseDatabase exerciseDatabase = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, "ExerciseDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }


}
