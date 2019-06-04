package com.example.hsh.homesweethome.network;

import com.example.hsh.homesweethome.MyApplication;
import com.example.hsh.homesweethome.network.Interceptors.AddCookiesInterceptor;
import com.example.hsh.homesweethome.network.Interceptors.ReceivedCookiesInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient(String baseUrl) {

        if (retrofit==null) {
            OkHttpClient client ;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

//            builder.addInterceptor(new AddCookiesInterceptor(MyApplication.getAppContext())); // VERY VERY IMPORTANT
//            builder.addInterceptor(new ReceivedCookiesInterceptor(MyApplication.getAppContext())); // VERY VERY IMPORTANT
            client = builder.build();
            client.retryOnConnectionFailure();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
