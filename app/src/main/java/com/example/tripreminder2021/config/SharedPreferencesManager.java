package com.example.tripreminder2021.config;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    private static final String PREF_NAME = "SHARED_PREFERENCE";

    // Shared preferences for Intro_Pager
    private static final String IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";

    // Shared preferences for User_Data
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String PASSWORD_SHARED_PREF = "password";

    public static final String IS_USER_LOGIN = "isUserLogin";

    public static final String IS_USER_DATA_SAVED="false";



    public SharedPreferencesManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /***********************************************************************************************
     */
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    /***********************************************************************************************
     */

    public void setIsUserLogin(boolean isUserLogin) {
        editor.putBoolean(IS_USER_LOGIN, isUserLogin);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public void setUserData(String email,String password) {
        editor.putString(EMAIL_SHARED_PREF, email);
        editor.putString(PASSWORD_SHARED_PREF, password);
        editor.putBoolean(IS_USER_DATA_SAVED,true);

        editor.commit();
    }

    public boolean isUserDataSaved(){
        return pref.getBoolean(IS_USER_DATA_SAVED,false);
    }
    public String [] getUSerData()
    {
        String [] userData = new String[2];
        String email=pref.getString(EMAIL_SHARED_PREF,"email");
        String password=pref.getString(PASSWORD_SHARED_PREF,"email");
        userData[0]=email;
        userData[1]=password;
        return userData;
    }




}
