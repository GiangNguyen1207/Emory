package com.example.emory;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MoodGraph {
    private DataPoint[] dataPoints;
    private LineGraphSeries<DataPoint> series;

    public MoodGraph() {
    }

    public MoodGraph(int size) {
        this.dataPoints = new DataPoint[size];
        this.series = new LineGraphSeries<>();
    }

    public int getMaxX(int currentDay, int daysInMonth) {
        if (currentDay < 5) {
            return 6;
        }

        if (currentDay < 10) {
            return 11;
        }

        if (currentDay < 15) {
            return 16;
        }

        if (currentDay < 20) {
            return 21;
        }

        if (currentDay < 25) {
            return 26;
        }

        return daysInMonth;
    }

    public double getMoodAverage(ArrayList<Diary> diaries) {
        double sum = 0;
        for (Diary diary : diaries) {
            if (diary.getMood().equals("excited")) {
                sum += 5;
                continue;
            }

            if (diary.getMood().equals("happy")) {
                sum += 4;
                continue;
            }

            if (diary.getMood().equals("good")) {
                sum += 3;
                continue;
            }

            if (diary.getMood().equals("sad")) {
                sum += 2;
                continue;
            }

            if (diary.getMood().equals("awful")) {
                sum += 1;
                continue;
            }
        }

        if (diaries.size() > 0) {
            return sum / diaries.size();
        } else {
            return 0;
        }
    }

    public double getPreviousDataPoint(int index) {
        DataPoint prevDataPoint = this.dataPoints[index];
        return prevDataPoint.getY();
    }

    public void addToDataPoints(int index, double y) {
        this.dataPoints[0] = new DataPoint(0, 0);

        if (index > 0 && y == 0) {
            double prevY = this.getPreviousDataPoint(index - 1);
            this.dataPoints[index] = new DataPoint(index, prevY);
        } else {
            this.dataPoints[index] = new DataPoint(index, y);
        }
    }

    public void addToSeries() {
        this.series = new LineGraphSeries<>(dataPoints);
    }

    public LineGraphSeries<DataPoint> getSeries() {
        return this.series;
    }
}