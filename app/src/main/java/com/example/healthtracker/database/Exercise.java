package com.example.healthtracker.database;

import java.util.Date;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    private String title;
    private int reps;
    private int sets;
    private String description;
    private long timestamp;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public Exercise(){

    }

    @Ignore
    public Exercise(String title, int reps, int sets, String description){
        this.title = title;
        this.reps = reps;
        this.sets = sets;
        this.description = description;
        this.timestamp = new Date().getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
