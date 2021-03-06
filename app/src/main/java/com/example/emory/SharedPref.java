package com.example.emory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPreferences mySharedPref;
    public static final String TODOLIST = "TODOLIST";
    public static final String GET_CHECKED = "BOOLEAN";

    private SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mySharedPref == null)
            mySharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mySharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mySharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mySharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mySharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }


}