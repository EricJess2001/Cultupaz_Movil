package com.example.cultupazmovil.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cultupazmovil.R;

public class registro extends AppCompatActivity {

 private ImageView regreso;
 private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        regreso = findViewById(R.id.regreso);
        spinner = findViewById(R.id.spinner);

        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regreso=new Intent(registro.this,inicio_sesion.class);
                startActivity(regreso);
            }
        });

     //spinner seecionar genero

        String[] respuestas = {"Seleccione el género", "Masculino", "Femenino", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, respuestas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0); // Establecer la selección inicial como el hint





    }
}
