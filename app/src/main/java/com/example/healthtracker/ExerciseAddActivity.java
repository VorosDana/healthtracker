package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.healthtracker.database.Exercise;
import com.example.healthtracker.database.ExerciseDatabase;

public class ExerciseAddActivity extends AppCompatActivity {

    ExerciseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_add);

        database = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, "exercise-diary")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public void addExercise(View view){
        Exercise exercise = new Exercise();

        EditText title = findViewById(R.id.edit_text_exercise_title);
        EditText sets = findViewById(R.id.edit_text_exercise_sets);
        EditText reps = findViewById(R.id.edit_text_exercise_reps);
        EditText description = findViewById(R.id.edit_text_exercise_description);


        exercise.setTitle(title.getText().toString());
        exercise.setSets(Integer.parseInt(sets.getText().toString()));
        exercise.setReps(Integer.parseInt(reps.getText().toString()));
        exercise.setDescription(description.getText().toString());

        database.exerciseDao().add(exercise);

        ExerciseListActivity parent = (ExerciseListActivity) this.getParent();
        parent.adapter.notifyDataSetChanged();

        this.finish();
    }


    public void finishActivity(View view){
        this.finish();
    }
}
