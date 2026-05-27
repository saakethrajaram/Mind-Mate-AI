package com.example.mindmateai;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorageHelper {

    private static final String PREF_NAME = "MindMateLocalData";
    private static final String KEY_NOTES = "saved_notes";
    private static final String KEY_HISTORY = "ai_history";

    private final SharedPreferences preferences;

    public LocalStorageHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveNotes(String notes) {
        preferences.edit().putString(KEY_NOTES, notes).apply();
    }

    public String getNotes() {
        return preferences.getString(KEY_NOTES, "No notes saved yet.");
    }

    public void saveHistory(String entry) {
        String oldHistory = preferences.getString(KEY_HISTORY, "");
        String updatedHistory = oldHistory + "\n\n----------------------\n" + entry;
        preferences.edit().putString(KEY_HISTORY, updatedHistory).apply();
    }

    public String getHistory() {
        return preferences.getString(KEY_HISTORY, "No AI history available yet.");
    }

    public void clearHistory() {
        preferences.edit().remove(KEY_HISTORY).apply();
    }
}