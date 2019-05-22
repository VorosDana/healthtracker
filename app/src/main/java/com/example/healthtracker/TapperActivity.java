package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TapperActivity extends AppCompatActivity {

    int taps;
    Button tapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapper);
        taps = 0;
        tapButton = findViewById(R.id.button_tap);
    }

    public void tapHandler(View in) {
        taps++;
        tapButton.setText(taps + " taps! Keep going!");
    }

    public void backButton(View view) {
        this.finish();
    }
}
