package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        if (convertView == null) {
            convertView = View.inflate(this.context, R.layout.item_view, null);
        }
        ImageView itemMood = convertView.findViewById(R.id.itemMood);
        TextView itemNote = convertView.findViewById(R.id.itemNote);
        Drawable mood = ContextCompat.getDrawable(context, diary.getMood());
        LinearLayout activityList = convertView.findViewById(R.id.activityList);
        LinearLayout singleActivity = convertView.findViewById(R.id.singleActivity);
        ImageView itemActionIcon = convertView.findViewById(R.id.itemActionIcon);
        TextView itemActionText = convertView.findViewById(R.id.itemActionText);
        actions = diary.getActions();

        for (Action action: actions) {
            //Drawable actionIcon = ContextCompat.getDrawable(context, action.getIcon());
            String actionName = action.getName();
            //itemActionIcon.setImageDrawable(actionIcon);
            itemActionText.setText(actionName);
            //singleActivity = (LinearLayout) View.inflate(this.context, R.layout.activity_item, null);
            //singleActivity.addView(itemActionIcon);
            singleActivity.addView(itemActionText, actions.indexOf(action));
            activityList.addView(singleActivity, actions.indexOf(action));
        }

        itemMood.setImageDrawable(mood);
        itemNote.setText("Note: " + diary.getNote());

        return convertView;
    }

}
