package com.example.cultupazmovil.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Apimuro {
    @POST("publicacion")
    Call<List<Muro>> postmuro(@Body Muro request);
}
