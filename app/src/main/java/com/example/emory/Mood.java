package com.example.emory;

import java.util.ArrayList;

public class Mood {
    private String mood;
    private ArrayList<Activities> activities;

    public Mood(String mood) {
        this.mood = mood;
        this.activities = new ArrayList<>();
    }

    public String getMood() {
        return this.mood;
    }

    public void addActivity(Activities act) {
        this.activities.add(act);
    }

    public void removeActivity(Activities act) {
        this.activities.remove(act);
    }

    public ArrayList<Activities> getActivities() {
        return this.activities;
    }
}
