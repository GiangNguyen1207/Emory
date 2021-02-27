package com.example.emory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiaryListAdapter extends ArrayAdapter<DiaryList> {

    public DiaryListAdapter(@NonNull Context context, ArrayList<DiaryList> diaryList) {
        super(context, 0, diaryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiaryList diaryList = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view, parent, false);
        }
        TextView date = convertView.findViewById(R.id.date);
        ListView itemView = convertView.findViewById(R.id.itemView);
        DiaryAdapter diaryAdapter = new DiaryAdapter(getContext(), diaryList.getDiaryData());

        date.setText(String.valueOf(diaryList.getDate()));
        itemView.setAdapter(diaryAdapter);

        return convertView;
    }
}
