package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Diary> diaries;

    public DiaryAdapter(Context context, ArrayList<Diary> diaries) {
        this.context = context;
        this.diaries = diaries;
    }

    @Override
    public int getCount() {
        return diaries.size();
    }

    @Override
    public Object getItem(int position) {
        return diaries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diary diary = diaries.get(position);

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_view, null);
        }
        ImageView itemMood = convertView.findViewById(R.id.itemMood);
        TextView itemNote = convertView.findViewById(R.id.itemNote);
        Drawable drawable = ContextCompat.getDrawable(context, diary.getMood());

        itemMood.setImageDrawable(drawable);
        itemNote.setText("Note: " + diary.getNote());

        return convertView;
    }

}
