package com.example.emory;

import java.util.ArrayList;

public class Mood {
    private String mood;
    private ArrayList<String> activities;

    public Mood(String mood, ArrayList<String> activities) {
        this.mood = mood;
        this.activities = activities;
    }

    public String getMood() {
        return this.mood;
    }

    public ArrayList<String> getActivities() {
        return this.activities;
    }
}
