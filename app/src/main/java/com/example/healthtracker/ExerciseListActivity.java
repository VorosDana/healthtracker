package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthtracker.RecyclerComponents.ExerciseAdapter;
import com.example.healthtracker.database.Exercise;
import com.example.healthtracker.database.ExerciseDatabase;

import java.util.List;

public class ExerciseListActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recycler;
    ExerciseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        database = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, "exercise-diary")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
        RecyclerView recyclerView = findViewById(R.id.recycler_exercises);

        List<Exercise> exercises = database.exerciseDao().getAll();

        if(exercises.size() == 0){
            exercises.add(new Exercise("Test", 3, 2, "Testing things"));
            exercises.add(new Exercise("Test", 3, 2, "Testing things"));
            exercises.add(new Exercise("Test", 3, 2, "Testing things"));
        }

        recycler = findViewById(R.id.recycler_exercises);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExerciseAdapter(exercises);
        recyclerView.setAdapter(adapter);
    }

    public void addNewExercise(View view) {
        Intent intent = new Intent(this, ExerciseAddActivity.class);
        startActivity(intent);
    }

    public void backButton(View view){
        this.finish();
    }
}
