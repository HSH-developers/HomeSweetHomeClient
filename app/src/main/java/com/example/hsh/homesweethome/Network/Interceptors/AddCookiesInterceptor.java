package com.example.hsh.homesweethome.Network.Interceptors;

import android.content.Context;
import android.util.Log;


import com.example.hsh.homesweethome.Network.Models.Session;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 This interceptor put all the Cookies in Preferences in the Request.
 **/

public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    private String TAG = "AddCookiesInterceptor";
    // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        Session session = new Session(context);
        String cookieCurrentUser = session.retrieveCurrentUserCookie();

        if(cookieCurrentUser!=null) {
            Log.e(TAG,"We have a cookie for the current user");
            builder.addHeader("Cookie", cookieCurrentUser);
            Log.e(TAG, cookieCurrentUser);
        }

        else{
            Log.e(TAG,"Current user do not have cookies");
        }

        return chain.proceed(builder.build());
    }
}
