package com.example.healthtracker.RecyclerComponents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthtracker.R;
import com.example.healthtracker.database.Exercise;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> {
    private List<Exercise> exercises;

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.exercise_diary_layout, parent, false);

        ExerciseHolder holder = new ExerciseHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.setExercise(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public ExerciseAdapter(List<Exercise> exercises){
        this.exercises = exercises;
    }

    public void setExercises(List<Exercise> exercises){
        this.exercises = exercises;
        this.notifyDataSetChanged();
    }
}
