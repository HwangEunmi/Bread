package com.bread.hwang.bread.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bread.hwang.bread.MyApplication;

/**
 * Created by Hwang on 2016-10-20.
 */

public class PropertyManager {
    private static PropertyManager instance;

    public static PropertyManager getInstance() {
        if (instance == null)
            instance = new PropertyManager();

        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_REGISTRATION_ID = "regid";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_USER_PASSWORD = "userpass";

    private PropertyManager() {
        Context context = MyApplication.getContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setRegisterationId(String regid) {
        mEditor.putString(KEY_REGISTRATION_ID, regid);
        mEditor.commit();
    }

    public String getRegistrationId() {
        return mPrefs.getString(KEY_REGISTRATION_ID, "");
    }

    public void setUserId(String userId) {
        mEditor.putString(KEY_USER_ID, userId);
        mEditor.commit();
    }

    public String getUserId() {
        return mPrefs.getString(KEY_USER_ID, "");
    }

    public void setUserName(String username) {
        mEditor.putString(KEY_USER_NAME, username);
        mEditor.commit();
    }

    public String getUserName() {
        return mPrefs.getString(KEY_USER_NAME, "");
    }

    public void setUserPassword(String userpass) {
        mEditor.putString(KEY_USER_PASSWORD, userpass);
        mEditor.commit();
    }

    public String getUserPassword() {
        return mPrefs.getString(KEY_USER_PASSWORD, "");
    }

}
