package com.example.healthtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int taps;
    private Carousel images;
    private Button tapButton;
    private SharedPreferences prefs;
    public static final String NAME_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapButton = findViewById(R.id.button_tap_page);
        taps = 0;

        images = new Carousel();
        images.add(R.drawable.royal_cat, "Tap for your queen!");
        images.add(R.drawable.shadowy_cat, "The cat watches. The cat approves of your tapping.");
        images.add(R.drawable.sleepy_cat, "The cat sleeps now. You sleep after you tap!");

        prefs = PreferencesHandler.getPrefs(this);
        TextView usernameField = findViewById(R.id.text_username);
        String name = prefs.getString(NAME_KEY, "Click Here");
        usernameField.setText("Hi, " + name + "!");
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView username = findViewById(R.id.text_username);
        String name = prefs.getString(MainActivity.NAME_KEY, "Click here");
        username.setText("Hi, " + name + "!");
    }



    public void leftButtonHandler(View in) {
        images.prev();
    }

    public void rightButtonHandler(View in) {
        images.next();
    }

    public void tapHandler(View in) {
        Intent intent = new Intent(this, TapperActivity.class);
        startActivity(intent);
    }

    public void exerciseDiaryButtonHandler(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        startActivity(intent);
    }

    private class Carousel{
        private Node current;
        private Node head;
        private Node tail;
        ImageView display = findViewById(R.id.image_carousel);
        TextView captionHolder = findViewById(R.id.caption_holder);

        public void add(int imageId, String caption) {
            if(tail == null){
                tail = new Node();
                tail.imageId = imageId;
                tail.index = 1;
                tail.caption = caption;
                head = tail;
                tail.next = tail;
                tail.prev = tail;
                current = tail;
                show();
                return;
            }

            Node node = new Node();
            node.caption = caption;
            node.prev = tail;
            tail.next = node;
            node.imageId = imageId;
            node.index = tail.index + 1;
            tail = node;
            tail.next = head;
            head.prev = tail;
            show();
        }

        public void next() {
            current = current.next;
            show();
        }

        public void prev() {
            current = current.prev;
            show();
        }

        private void show(){
            display.setImageResource(current.imageId);
            captionHolder.setText("Image " + current.index + "/" +tail.index + "\n" +
                                        current.caption);
        }
    }

    private class Node {
        Node next;
        Node prev;
        int index;
        int imageId;
        String caption;
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

    private void updateUsername(String name) {
        prefs.edit().putString(NAME_KEY, name).apply();
        TextView nameView = findViewById(R.id.text_username);
        nameView.setText("Hi, " + name + "!");
    }
}
