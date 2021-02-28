package com.example.emory;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsSingleton {
    private static final String PREF_NAME = "default_settings";
    private static SharedPrefsSingleton sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    public static class Key {
        public static final String SAMPLE = "a_sample_key";
    }

    private SharedPrefsSingleton(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static SharedPrefsSingleton getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new SharedPrefsSingleton(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static void init(Context context) {
        sSharedPrefs = new SharedPrefsSingleton(context);

    }
    public static SharedPrefsSingleton getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    public void put(String key, String val) {
        doEdit();
        mEditor.putString(key, val);
        doCommit();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

}
