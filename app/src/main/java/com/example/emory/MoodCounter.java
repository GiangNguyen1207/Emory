package com.example.emory;

public class MoodCounter {
    private int count;

    public MoodCounter() {
        this.count = 0;
    }

    public void addValue() {
        this.count += 1;
    }

    public int getCount() {
        return this.count;
    }
}
