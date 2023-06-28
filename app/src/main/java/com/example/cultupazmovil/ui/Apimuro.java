package com.example.cultupazmovil.ui;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
public interface Apimuro {
    @GET("verPublicaciones")
    Call<List<Muro>> getverMuro();
}
