package com.example.cultupazmovil.ui;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMuroo {
    private static Retrofit retrofit;

    public static Retrofit getApiMuro() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.8:7000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}