package com.example.emory;

import java.util.ArrayList;

public class Diary {
    String month;
    ArrayList<SingleNote> notes;

    public Diary(String month, ArrayList<SingleNote> notes) {
        this.month = month;
        this.notes = notes;
    }
}


