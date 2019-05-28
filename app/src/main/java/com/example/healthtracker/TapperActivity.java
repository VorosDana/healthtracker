package com.example.healthtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TapperActivity extends AppCompatActivity {

    int taps;
    Button tapButton;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapper);
        taps = 0;
        tapButton = findViewById(R.id.button_tap);

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

    public void tapHandler(View in) {
        taps++;
        tapButton.setText(taps + " taps! Keep going!");
    }

    public void backButton(View view) {
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
