package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.healthtracker.database.Exercise;
import com.example.healthtracker.database.ExerciseDatabase;
import com.example.healthtracker.retrofit_components.ExerciseService;

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

        postToRestApi(exercise);

        ExerciseListActivity parent = (ExerciseListActivity) this.getParent();
        if(parent != null) {
            parent.adapter.notifyDataSetChanged();
        }

        this.finish();
    }


    public void finishActivity(View view){
        this.finish();
    }

    public void postToRestApi(Exercise exercise){

        // method to retrieve string courtesy of
        // https://stackoverflow.com/questions/7493287/android-how-do-i-get-string-from-resources-using-its-name
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.rest_api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExerciseService exService = retrofit.create(ExerciseService.class);
        exService.createExercise(exercise).enqueue(new Callback<Exercise>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                Log.d("Exercise POST success!", response.message());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<Exercise> call, Throwable t) {
                Log.e("Exercise POST failure: ", t.toString());
            }
        });
    }
}
