package com.codesroots.hossam.lordApp.dataLayer.apiData;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientLocal {


    private static final String BASE_URL = "http://192.168.1.8:8080/newparashote/api/";
    private static final int TIMEOUT = 30;
    private static Retrofit retrofit = null;

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return retrofit;
    }
}