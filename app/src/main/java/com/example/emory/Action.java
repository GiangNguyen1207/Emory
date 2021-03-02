package com.example.emory;

import java.lang.reflect.Field;

public class Action {
    private String name;
    private int icon;

    public Action(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String getResourceName() {
        return "action_ic_" + this.name;
    }

    public int getIcon() {
        return this.icon;
    }

    public int retrieveName() {
        try {
            Field field = R.drawable.class.getDeclaredField(this.getResourceName());
            return field.getInt(field);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}