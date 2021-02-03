package com.example.tripreminder2021.config;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private Context _context;

    private static final String PREF_NAME = "SHARED_PREFERENCE";

    // Shared preferences for Intro_Pager
    private static final String IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";

    // Shared preferences for User_Data
    private static final String IS_USER_DATA_SAVED="IS_USER_DATA_SAVED";
    private static final String EMAIL_SHARED_PREF = "EMAIL_SHARED_PREF";
    private static final String PASSWORD_SHARED_PREF = "PASSWORD_SHARED_PREF";


    // Shared preferences for Login
    private static final String IS_USER_LOGIN = "IS_USER_LOGIN";
    private static final String CURRENT_USER_ID="CURRENT_USER_ID";
    private static final String CURRENT_USER_EMAIL="user@CURRENT_USER_EMAIL.com";

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
    public boolean isUserDataSaved(){
        return pref.getBoolean(IS_USER_DATA_SAVED,false);
    }

    public void setUserData(String email,String password) {

        editor.putString(EMAIL_SHARED_PREF, email);
        editor.putString(PASSWORD_SHARED_PREF, password);
        editor.commit();
    }

    public void setUserDataSaved(boolean key)
    {
        editor.putBoolean(IS_USER_DATA_SAVED,key);
        editor.commit();
    }
    public String [] getUSerData() {
        String [] userData = new String[2];
        String email=pref.getString(EMAIL_SHARED_PREF,"email");
        String password=pref.getString(PASSWORD_SHARED_PREF,"pass");
        userData[0]=email;
        userData[1]=password;
        return userData;
    }

    /***********************************************************************************************
     */


    public void setUserLogin(boolean key) {
        editor.putBoolean(IS_USER_LOGIN, key);
        editor.commit();
    }
    public boolean isUserLogin() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
    public void setCurrentUserID(String user_id) {
        editor.putString(CURRENT_USER_ID, user_id);
        editor.commit();
    }
    public void setCurrentUserEmail(String currentUserEmail) {
        editor.putString(CURRENT_USER_EMAIL, currentUserEmail);
        editor.commit();
    }
    public String getCurrentUserId()
    {
        return pref.getString(CURRENT_USER_ID, "");
    }
    public String getCurrentUserEmail()
    {
        return pref.getString(CURRENT_USER_EMAIL, "");
    }




}
