package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cultupazmovil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class inicio_sesion extends AppCompatActivity {



     EditText email, password;
     Button btn_login;
     TextView registrof;


     FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);

        //
        mAuth = FirebaseAuth.getInstance();

        // Boton de ir a registro

        registrof = findViewById(R.id.registrof);

        registrof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iregistro=new Intent(inicio_sesion.this,registro.class);
                startActivity(iregistro);
            }
        });

        //Funcion para inicio de sesion en firebase

        email = findViewById(R.id.username);
        password = findViewById(R.id.contraseña);
        btn_login = findViewById(R.id.loginButton);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(inicio_sesion.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();

                }else{
                    loginUser(emailUser, passUser);
                }


            }

            private void loginUser(String emailUser, String passUser) {
                mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(inicio_sesion.this, cultupaz.class));
                            Toast.makeText(inicio_sesion.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(inicio_sesion.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(inicio_sesion.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }
}