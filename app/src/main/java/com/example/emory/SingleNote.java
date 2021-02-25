package com.example.emory;

import java.util.ArrayList;

public class SingleNote {
    String date;
    ArrayList<Mood> moods;
    String note;
    String pic;

    public SingleNote(ArrayList<Mood>moods, String note, String pic) {
        this.moods = moods;
        this.note = note;
        this.pic = pic;
    }

    public SingleNote(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String getNote() {
        return this.note;
    }

    public String getPic() {
        return this.pic;
    }
}
