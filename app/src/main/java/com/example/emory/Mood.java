package com.example.emory;

import java.util.ArrayList;

public class Mood {
    private String mood;
    private ArrayList<Activities> activities;

    public Mood(String mood, ArrayList<Activities> activities) {
        this.mood = mood;
        this.activities = activities;
    }

    public String getMood() {
        return this.mood;
    }

    public ArrayList<Activities> getActivities() {
        return this.activities;
    }
}
