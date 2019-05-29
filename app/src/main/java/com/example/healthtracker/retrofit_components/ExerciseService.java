package com.example.healthtracker.retrofit_components;

import com.example.healthtracker.database.Exercise;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExerciseService {
    @GET("all")
    Call<Exercise> getAllExercises();

    @POST("new")
    Call<Exercise> createExercise(@Body Exercise exercise);
}
