package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Action> actions;

    public ActionAdapter(Context context, ArrayList<Action> actions) {
        this.context = context;
        this.actions = actions;
    }

    @Override
    public int getCount() {
        return actions.size();
    }

    @Override
    public Object getItem(int position) {
        return actions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Action action = actions.get(position);

        if (convertView == null) {
            convertView = View.inflate(this.context, R.layout.single_action_view, null);
        }

        ImageView itemActionIcon = convertView.findViewById(R.id.itemActionIcon);
        TextView itemActionText = convertView.findViewById(R.id.itemActionText);

        Drawable actionIcon = ContextCompat.getDrawable(context, action.retrieveName());
        itemActionIcon.setImageDrawable(actionIcon);
        itemActionText.setText(action.getName());

        return convertView;
    }
}
