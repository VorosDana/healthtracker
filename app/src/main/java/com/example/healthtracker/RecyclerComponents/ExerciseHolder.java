package com.example.healthtracker.RecyclerComponents;

import android.view.View;
import android.widget.TextView;

import com.example.healthtracker.R;
import com.example.healthtracker.database.Exercise;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView repsAndSets;
    public TextView description;
    public TextView timestamp;
    public SimpleDateFormat dates;

    public ExerciseHolder(@NonNull View itemView) {
        super(itemView);

        this.title = itemView.findViewById(R.id.text_exercise_title);
        this.repsAndSets = itemView.findViewById(R.id.text_reps_sets);
        this.description = itemView.findViewById(R.id.text_exercise_description);
        this.timestamp = itemView.findViewById(R.id.text_exercise_timestamp);

        dates = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setExercise(Exercise exercise){
        title.setText(exercise.getTitle());
        repsAndSets.setText(exercise.getSets() + " sets of " + exercise.getReps());
        description.setText(exercise.getDescription());
        timestamp.setText(dates.format(exercise.getTimestamp()));
    }
}
