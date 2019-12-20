package com.example.restoran.ui.Setting;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    SharedPreferences mySharedPref;

    public SharedPreference(Context context){
        mySharedPref = (SharedPreferences) context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    // Method untuk menyimpan mode nightmode : True atau False

    public void setNightMOdeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightModeState(){
        Boolean state = mySharedPref.getBoolean("NightMode", false);
        return state;
    }

}
