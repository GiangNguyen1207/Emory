package com.example.emory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiaryListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DiaryList> diaryLists;

    public DiaryListAdapter(Context context, ArrayList<DiaryList> diaryLists) {
        this.context = context;
        this.diaryLists = diaryLists;
    }

    @Override
    public int getCount() {
        return diaryLists.size();
    }

    @Override
    public Object getItem(int position) {
        return diaryLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiaryList diaryList = diaryLists.get(position);

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.card_view, null);
        }
        TextView date = convertView.findViewById(R.id.date);
        ListView itemView = convertView.findViewById(R.id.itemView);
        DiaryAdapter diaryAdapter = new DiaryAdapter(this.context, diaryList.getDiaryData());

        date.setText(String.valueOf(diaryList.getDate()));
        itemView.setAdapter(diaryAdapter);

        return convertView;
    }

}
