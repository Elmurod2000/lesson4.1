package com.example.lesson41;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context){
        preferences=context.getSharedPreferences("settings",Context.MODE_PRIVATE);

    }

    public void saveBoardState(){
        preferences.edit().putBoolean("isBoardShown",true).apply();
    }

    public boolean  isBoardShown(){
        return preferences.getBoolean("isBoardShown", false);
    }

    public void saveName(String name){
        preferences.edit().putString("name",name).apply();
    }

    public String getName() {
     return preferences.getString("name","");
    }
    public void saveImage(String image){
        preferences.edit().putString("image",image).apply();
    }

    public String getImage() {
     return preferences.getString("image","");
    }

    public void clear(Context context){
        preferences=context.getSharedPreferences("settings",Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}
