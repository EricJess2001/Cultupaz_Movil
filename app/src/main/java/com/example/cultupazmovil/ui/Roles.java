package com.example.cultupazmovil.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cultupazmovil.R;

public class Roles extends AppCompatActivity {

    Button aprendiz, admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_roles);
        // Inflate the layout for this fragment


        aprendiz = findViewById(R.id.button2);

        aprendiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iraprendiz = new Intent(Roles.this,inicio_sesion.class);
                startActivity(iraprendiz);
            }
        });



        admin = findViewById(R.id.button);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iraprendiz = new Intent(Roles.this,inicio_Admin.class);
                startActivity(iraprendiz);
            }
        });





    }
}