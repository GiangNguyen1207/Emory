package com.example.emory;

import java.util.ArrayList;

public class Diary {
    private int mood;
    private ArrayList<Action> actions;
    private String note;

    public Diary(int mood, ArrayList<Action> actions, String note) {
        this.mood = mood;
        this.actions = actions;
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

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public boolean checkExistingMood(ArrayList<Diary> diaries, int mood) {
        Boolean existedMood = false;
        for (Diary diary: diaries) {
            if (diary.getMood() == mood) {
                existedMood = true;
            }
        }
        return existedMood;
    }

    public String toString() {
        return this.mood + " " + String.valueOf(this.actions);
    }
}


