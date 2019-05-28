package com.example.healthtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthtracker.RecyclerComponents.ExerciseAdapter;
import com.example.healthtracker.database.Exercise;
import com.example.healthtracker.database.ExerciseDatabase;

import java.util.List;

public class ExerciseListActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recycler;
    ExerciseDatabase database;
    private SharedPreferences prefs;


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

        prefs = PreferencesHandler.getPrefs(this);
        TextView usernameField = findViewById(R.id.text_username);
        String name = prefs.getString(MainActivity.NAME_KEY, "Click Here");
        usernameField.setText("Hi, " + name + "!");
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView username = findViewById(R.id.text_username);
        String name = prefs.getString(MainActivity.NAME_KEY, "Click here");
        username.setText("Hi, " + name + "!");
    }

    public void addNewExercise(View view) {
        Intent intent = new Intent(this, ExerciseAddActivity.class);
        startActivity(intent);
    }

    public void backButton(View view){
        this.finish();
    }

    private void updateUsername(String name) {
        prefs.edit().putString(MainActivity.NAME_KEY, name).apply();
        TextView nameView = findViewById(R.id.text_username);
        nameView.setText("Hi, " + name + "!");
    }

    public void usernameUpdateButtonHandler(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Your Name!");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateUsername(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }
}
