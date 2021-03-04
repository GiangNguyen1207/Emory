package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ActionList extends BaseAdapter {
    private Context context;
    private ArrayList<Action> actionList;

    public ActionList(Context context) {
        this.context = context;
        this.actionList = new ArrayList<>();
        this.actionList.add(new Action("family", R.drawable.action_ic_family));
        this.actionList.add(new Action("friends", R.drawable.action_ic_friends));
        this.actionList.add(new Action("favourite", R.drawable.action_ic_favourite));
        this.actionList.add(new Action("fitness", R.drawable.action_ic_fitness));
        this.actionList.add(new Action("walking", R.drawable.action_ic_walking));
        this.actionList.add(new Action("movie", R.drawable.action_ic_movie));
        this.actionList.add(new Action("sleep", R.drawable.action_ic_sleep));
        this.actionList.add(new Action("travel", R.drawable.action_ic_travel));
        this.actionList.add(new Action("study", R.drawable.action_ic_study));
        this.actionList.add(new Action("cleaning", R.drawable.action_ic_cleaning));
        this.actionList.add(new Action("work", R.drawable.action_ic_work));
        this.actionList.add(new Action("shopping", R.drawable.action_ic_shopping));
        this.actionList.add(new Action("games", R.drawable.action_ic_games));
        this.actionList.add(new Action("celebration", R.drawable.action_ic_celebration));
        this.actionList.add(new Action("beach", R.drawable.action_ic_beach));
        this.actionList.add(new Action("sick", R.drawable.action_ic_sick));
    }

    public ActionList(Context context, ArrayList<Action> allActions) {
        this.context = context;
        this.actionList = allActions;
    }

    public int getSize() {
        return this.actionList.size();
    }

    public Action getAction(int position) {
        return this.actionList.get(position);
    }

    public ArrayList<Action> getAllActions() {
        return this.actionList;
    }

    @Override
    public int getCount() {
        return this.getSize();
    }

    @Override
    public Object getItem(int position) {
        return this.getAction(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Action action = this.actionList.get(position);

        if (convertView == null) {
            convertView = View.inflate(this.context, R.layout.write_note_single_action, null);
        }

        ImageView itemView = convertView.findViewById(R.id.imageView);
        TextView itemActionText = convertView.findViewById(R.id.itemActionText);

        Drawable actionIcon = ContextCompat.getDrawable(context, action.retrieveName());
        itemView.setImageDrawable(actionIcon);
        itemActionText.setText(action.getName());

        return convertView;
    }
}
