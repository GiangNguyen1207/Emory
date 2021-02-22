package com.example.emory;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthYear {
    private ArrayList<String> months;
    private Calendar calendar;

    public MonthYear() {
        this.months = new ArrayList<>();
        this.calendar = Calendar.getInstance();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    public String getCurrentMonthYear() {
        return this.months.get(calendar.get(Calendar.MONTH)) + ", " + calendar.get(Calendar.YEAR);
    }

    public String getPrevMonthYear(String month, int year) {
        String prevMonth =  "";
        int curYear = year;

        if (month.equals("January")) {
            prevMonth = "December";
            curYear -= 1;
            return prevMonth + ", " + curYear;
        }

        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).equals(month)) {
                prevMonth = months.get(i - 1);
            }
        }
        return prevMonth + ", " + curYear;
    }

    public String getNextMonthYear(String month, int year) {
        String nextMonth = "";
        int curYear = year;

        if (month.equals("December")) {
            nextMonth = "January";
            curYear += 1;
            return nextMonth + ", " + curYear;
        }

        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).equals(month)) {
                nextMonth = months.get(i + 1);
            }
        }
        return nextMonth + ", " + curYear;
    }
}
