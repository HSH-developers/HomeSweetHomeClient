package com.example.hsh.homesweethome.Network.Models;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashSet;

import okhttp3.Response;

public class Session extends Application {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    public static final String CURRENT_COOKIE_USER = "CURRENT_COOKIE_USER";
    private static final String TAG = "Session";
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();

    public Session(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefsEditor=prefs.edit();
    }

    public void addCookies(Response originalResponse){
        HashSet<String> cookies = (HashSet<String>) prefs.getStringSet(PREF_COOKIES, new HashSet<String>());
        String currentCookieUser = prefs.getString(CURRENT_COOKIE_USER,null);

        if(currentCookieUser == null){
            Log.e(TAG,"Assigning a cookie to the current user for persistent login");

            for (String cookieSID : originalResponse.headers("Set-Cookie")) {
                cookies.add(cookieSID);
                currentCookieUser = cookieSID;
                break;
            }
        }
        else{
            Log.e(TAG,"A cookie already exists for the current user!!");
        }

        //Committing to phone storage
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(CURRENT_COOKIE_USER,currentCookieUser).apply();
        prefsEditor.putStringSet(PREF_COOKIES, cookies).apply();
        //memes.remove("PREF_COOKIES").apply();
        prefsEditor.commit();
    }

    public String retrieveCurrentUserCookie(){
        return prefs.getString(CURRENT_COOKIE_USER,null);
    }


    public void addCookiesData(String cookie,String username,String password, String userID, String email){

        //find cookie data that is associated with this cookie in the arguments

        String cookieData = prefs.getString(cookie,null);

        if(cookieData == null){
            Log.e(TAG,"Cookie data is empty");
            CookieData userCookieData = new CookieData();
            userCookieData.setSID(cookie);
            userCookieData.setUsername(username);
            userCookieData.setPassword(password);
            userCookieData.setEmail(email);
            userCookieData.setUserID(userID);

            String jsonUserCookieData = gson.toJson(userCookieData);

            prefsEditor.putString(cookie,jsonUserCookieData).apply();
            prefsEditor.commit();
        }
        else{
            CookieData userCookieData = new GsonBuilder().setPrettyPrinting().create().fromJson(cookieData,CookieData.class);
            Log.e(TAG,"Cookie data : " + new GsonBuilder().setPrettyPrinting().create().toJson(userCookieData));
        }
    }

    public CookieData retrieveCookiesData(String cookie){
        String jsonUserCookieData = prefs.getString(cookie,null);
        CookieData userCookieData = new CookieData();
        if(jsonUserCookieData!=null){
            Log.e(TAG,"User has cookie data stored");
            userCookieData = gson.fromJson(jsonUserCookieData,CookieData.class);
            return userCookieData;
        }
        else{
            Log.e(TAG,"User does not have cookie data stored");
            return userCookieData;
        }
    }

}
