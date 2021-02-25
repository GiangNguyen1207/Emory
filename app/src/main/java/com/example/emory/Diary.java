package com.example.emory;

import java.util.ArrayList;

public class Diary {

    private int mood;
    private ArrayList<Activities> activities;
    private String note;

    public Diary(int mood, ArrayList<Activities> activities, String note) {
        this.mood = mood;
        this.note = note;
    }

    public int getMood() {
        return this.mood;
    }

    public String getNote() {
        return this.note;
    }

}


