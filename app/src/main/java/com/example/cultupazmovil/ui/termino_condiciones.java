package com.example.cultupazmovil.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cultupazmovil.MainActivity;
import com.example.cultupazmovil.R;


public class termino_condiciones extends AppCompatActivity {

    Button btn_acepta_terminos;
    Button btn_cancelar_terminos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_termino_condiciones);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btn_acepta_terminos = findViewById(R.id.btn_acepta_terminos);
        btn_cancelar_terminos=findViewById(R.id.btn_cancelar_terminos);


        btn_acepta_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irsiguiente=new Intent(termino_condiciones.this, MainActivity.class);
                startActivity(irsiguiente);
            }
        });
        btn_cancelar_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irsiguiente=new Intent(termino_condiciones.this,cultupaz.class);
                startActivity(irsiguiente);
            }
        });
    }


}