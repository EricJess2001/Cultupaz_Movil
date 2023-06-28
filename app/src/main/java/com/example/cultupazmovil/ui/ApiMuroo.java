package com.example.cultupazmovil.ui;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMuroo {
    private static Retrofit retrofit;

    public static Retrofit getApiMuro() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.185.80.109:7000/verPublicaciones")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}