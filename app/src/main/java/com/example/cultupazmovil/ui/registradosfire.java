package com.example.cultupazmovil.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cultupazmovil.MainActivity;
import com.example.cultupazmovil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registradosfire extends AppCompatActivity {

    Button btn_register;
    EditText name, email, password, lastname, number, identification, user;

    ImageView regreso;

    FirebaseFirestore mFirestore;

    FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registradosfire);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        regreso = findViewById(R.id.regreso);

        name = findViewById(R.id.nombre);
        lastname = findViewById(R.id.apellido);
        number = findViewById(R.id.telefono);
        identification = findViewById(R.id.Numeroiden);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contraseña);
        user = findViewById(R.id.usuario);
        btn_register = findViewById(R.id.btn_registro);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


// Boton de regreso al login

                Intent regreso=new Intent(registradosfire.this,inicio_sesion.class);
                startActivity(regreso);

                // Funcion

                String nameUser = name.getText().toString().trim();
                String lastUser = lastname.getText().toString().trim();
                String numberUser = number.getText().toString().trim();
                String identificationUser = identification.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();
                String userUser = user.getText().toString().trim();

                if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty() && lastUser.isEmpty() && numberUser.isEmpty() && identificationUser.isEmpty()){

                    Toast.makeText(registradosfire.this, "Complete los datos", Toast.LENGTH_SHORT).show();

                }else {

                    registerUser(nameUser, emailUser, passUser, lastUser, numberUser, identificationUser, userUser);


                }


            }

            private void registerUser(String nameUser, String emailUser, String passUser, String lastUser, String numberUser, String identificationUser, String userUser) {

                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String id = mAuth.getCurrentUser().getUid();

                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id);
                        map.put("name", nameUser);
                        map.put("lastname", lastUser );
                        map.put("number", numberUser);
                        map.put("identification", identificationUser);
                        map.put("email", emailUser);
                        map.put("password", passUser);
                        map.put("user", userUser);


                        mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                finish();

                                startActivity(new Intent(registradosfire.this, MainActivity.class));
                                Toast.makeText(registradosfire.this, "Usuario guardado exitosamente", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(registradosfire.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registradosfire.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}