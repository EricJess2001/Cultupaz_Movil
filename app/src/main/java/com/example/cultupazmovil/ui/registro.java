package com.example.cultupazmovil.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cultupazmovil.R;

public class registro extends AppCompatActivity {

 private ImageView regreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        regreso = findViewById(R.id.regreso);

        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regreso=new Intent(registro.this,inicio_sesion.class);
                startActivity(regreso);
            }
        });







    }
}
