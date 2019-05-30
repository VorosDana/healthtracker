package com.example.healthtracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public abstract class PreferencesHandler {

    public static SharedPreferences getPrefs(Activity activity) {
        String filename = activity.getString(R.string.user_prefs_file);
        SharedPreferences preferences = activity.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences;
    }
}
