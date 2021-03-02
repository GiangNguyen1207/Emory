package com.example.emory;

public class Action implements Comparable<Action>{
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

    public int getIcon() {
        return this.icon;
    }


    @Override
    public int compareTo(Action o) {
        return this.name.compareTo(o.name);
    }
}