package com.example.emory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class DayMonthYear extends DialogFragment {
    private ArrayList<String> months;
    private Calendar calendar;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(),
                year, month, day);
    }

    public DayMonthYear() {
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

    public String getCurrentFullDate() {
        return calendar.get(Calendar.DATE) + ". " +
                this.months.get(calendar.get(Calendar.MONTH)) + ", " +
                calendar.get(Calendar.YEAR);
    }

    public String getCurrentMonthYear() {
        return this.months.get(calendar.get(Calendar.MONTH)) + ", " + calendar.get(Calendar.YEAR);
    }

    public String getPrevMonthYear(String month, int year) {
        String prevMonth = "";
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


    public String setFullDate(int year, int month, int date) {
        return date + ". " + this.months.get((month)) + ", " + year;
    }
}
