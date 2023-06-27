package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentInfoWebBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Expresate extends DialogFragment {

    private Button buttonenviar;
    private EditText tema, expresion;

    private FragmentInfoWebBinding binding;

    private String idUsuario;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoWebBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_expresate, container, false);

        tema = view.findViewById(R.id.temaha);
        idUsuario = "1";
        expresion = view.findViewById(R.id.expression);
        buttonenviar = view.findViewById(R.id.buttonenviar);

        buttonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = tema.getText().toString();
                String descripcion = expresion.getText().toString();

                Toast.makeText(getActivity(), "Tema: " + titulo + ", Descripcion: " + descripcion, Toast.LENGTH_SHORT).show();

                String jsonBody = "{\"titulo\":\"" + titulo + "\","
                        + "\"idUsuario\":\"" + idUsuario + "\","
                        + "\"descripcion\":\"" + descripcion + "\",";

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

                Request request = new Request.Builder()
                        .url("http://10.185.81.234:7000/publicacion")
                        .post(requestBody)
                        .build();

                OkHttpClient client = new OkHttpClient();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Error al enviar la solicitud", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseBody = response.body().string();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Tu respuesta fue enviada exitosamente", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), inicio_sesion.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getActivity(), "Error al subir", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        return view;
    }

}
