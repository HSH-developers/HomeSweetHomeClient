package com.example.hsh.homesweethome.network.Interceptors;

import android.content.Context;
import android.util.Log;

import com.example.hsh.homesweethome.network.Models.Session;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;
    private String TAG = "Received cookies interceptor";

    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    } // AddCookiesInterceptor()

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            Log.e(TAG, "Setting cookies");
            Session session = new Session(context);
            session.addCookies(originalResponse);
        }

        return originalResponse;
    }
}