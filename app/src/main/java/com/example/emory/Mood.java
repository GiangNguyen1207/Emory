package com.example.emory;

import java.util.ArrayList;

public class Mood {
    private String mood;
    private ArrayList<Action> activities;

    public Mood(String mood) {
        this.mood = mood;
        this.activities = new ArrayList<>();
    }

    public String getMood() {
        return this.mood;
    }

    public void addActivity(Action act) {
        this.activities.add(act);
    }

    public void removeActivity(Action act) {
        this.activities.remove(act);
    }

    public ArrayList<Action> getActivities() {
        return this.activities;
    }

}
