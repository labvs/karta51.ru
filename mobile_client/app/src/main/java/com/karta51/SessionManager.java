package com.karta51;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String NUMBER = "NUMBER";
    public static final String CARD1 = "CARD1";
    public static final String CARD2 = "CARD2";
    public static final String CARD3 = "CARD3";
    public static final String ID = "ID";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession (String number, String card1, String card2, String card3, String id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(NUMBER, number);
        editor.putString(CARD1, card1);
        editor.putString(CARD2, card2);
        editor.putString(CARD3, card3);
        editor.putString(ID, id);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLogin()) {
            Intent i = new Intent (context, sign_in_page.class);
            context.startActivity(i);
            ((personal_account)context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap <String, String> user = new HashMap<>();
        user.put(NUMBER, sharedPreferences.getString(NUMBER, null));
        user.put(CARD1, sharedPreferences.getString(CARD1, null));
        user.put(CARD2, sharedPreferences.getString(CARD2, null));
        user.put(CARD3, sharedPreferences.getString(CARD3, null));
        user.put(ID, sharedPreferences.getString(ID, null));


        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent (context, MainMenuActivity.class);
        context.startActivity(i);
        ((personal_account)context).finish();
    }
}
