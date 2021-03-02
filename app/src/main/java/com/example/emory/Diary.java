package com.example.emory;

import java.util.ArrayList;

public class Diary {
    private String mood;
    private ArrayList<Action> actions;
    private String note;

    public Diary(String mood, ArrayList<Action> actions, String note) {
        this.mood = mood;
        this.actions = actions;
        this.note = note;
    }

    public String getMood() {
        return this.mood;
    }

    public String getNote() {
        if (this.note.isEmpty()) {
            return "Nothing was written...";
        }
        return this.note;
    }

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public String toString() {
        return this.mood + " " + String.valueOf(this.actions);
    }
}


