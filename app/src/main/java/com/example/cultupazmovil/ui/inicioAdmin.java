package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.cultupazmovil.R;


public class inicioAdmin extends AppCompatActivity {


    private Button btn_adminemp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_admin);
        // Inflate the layout for this fragment


        btn_adminemp = findViewById(R.id.btn_empeadmin);

        btn_adminemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vistadmin=new Intent(inicioAdmin.this, Peticiones.class);
                startActivity(vistadmin);
            }
        });

    }
}