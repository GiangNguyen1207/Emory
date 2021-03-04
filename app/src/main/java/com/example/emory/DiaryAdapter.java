package com.example.emory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Diary> diaries;
    private ArrayList<Action> actions;

    public DiaryAdapter(Context context, ArrayList<Diary> diaries) {
        this.context = context;
        this.diaries = diaries;
        this.actions = new ArrayList<>();
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
        actions = diary.getActions();

        if (convertView == null) {
            convertView = View.inflate(this.context, R.layout.single_diary_view, null);
        }
        ImageView itemMood = convertView.findViewById(R.id.itemMood);
        ImageView itemPic = convertView.findViewById(R.id.itemPic);
        TextView itemNote = convertView.findViewById(R.id.itemNote);

        Drawable mood = ContextCompat.getDrawable(context, diary.retrieveMoodIdFromName());
        itemMood.setImageDrawable(mood);

        GridView activityList = convertView.findViewById(R.id.activityList);
        if (!actions.isEmpty()) {
            activityList.setAdapter(new ActionAdapter(this.context, this.actions));
        }

        itemNote.setText("Note: " + diary.getNote());
        Bitmap pic = diary.decodePic();
        if (pic != null) {
            itemPic.setVisibility(View.VISIBLE);
            itemPic.setImageBitmap(pic);
        }
        Log.d("pic", String.valueOf(pic));
        return convertView;
    }
}
