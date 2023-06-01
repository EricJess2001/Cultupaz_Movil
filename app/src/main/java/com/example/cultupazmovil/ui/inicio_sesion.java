package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cultupazmovil.R;

public class inicio_sesion extends AppCompatActivity {

    private Button loginButton;

    private TextView registrof;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);

        // Configurar la ActionBar

        registrof = findViewById(R.id.registrof);

        registrof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iregistro=new Intent(inicio_sesion.this,registro.class);
                startActivity(iregistro);
            }
        });


        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irsiguiente=new Intent(inicio_sesion.this,cultupaz.class);
                startActivity(irsiguiente);
            }
        });
    }
}