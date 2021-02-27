package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DiaryAdapter extends ArrayAdapter<Diary> {

    public DiaryAdapter(@NonNull Context context, ArrayList<Diary> diaries) {
        super(context, 0, diaries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diary diary = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }
        ImageView itemMood = convertView.findViewById(R.id.itemMood);
        TextView itemNote = convertView.findViewById(R.id.itemNote);
        Drawable drawable = ContextCompat.getDrawable(getContext(), diary.getMood());
        itemMood.setImageDrawable(drawable);
        itemNote.setText(diary.getNote());

        return convertView;
    }

}
