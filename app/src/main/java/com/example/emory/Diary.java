package com.example.emory;

import java.util.ArrayList;

public class Diary {
    private int mood;
    private ArrayList<Activities> activities;
    private String note;

    public Diary(int mood, ArrayList<Activities> activities, String note) {
        this.mood = mood;
        this.activities = activities;
        this.note = note;
    }

    public int getMood() {
        return this.mood;
    }

    public String getNote() {
        if (this.note.isEmpty()) {
            return "Nothing was written...";
        }
        return this.note;
    }

    public String toString() {
        return String.valueOf(this.mood);
    }
}


