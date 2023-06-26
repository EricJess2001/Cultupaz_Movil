package com.example.cultupazmovil.ui;

import static com.example.cultupazmovil.ui.registro.isValidEmail;

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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class inicio_sesion extends AppCompatActivity {


    EditText reemail, contraseña;
    Button btn_login;
    TextView registrof;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);

        //

        reemail = findViewById(R.id.username);
        contraseña = findViewById(R.id.contraseña);
        btn_login = findViewById(R.id.loginButton);


        registrof = findViewById(R.id.registrof);

        registrof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iregistro = new Intent(inicio_sesion.this, registro.class);
                startActivity(iregistro);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = reemail.getText().toString();
                String passw = contraseña.getText().toString();


                // Verificar si el correo ingresado es válido
                if (!isValidEmail(correo)) {
                    Toast.makeText(inicio_sesion.this, "Correo inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String jsonBody = "{\"correo\":\"" + correo + "\","
                        + "\"passw\":\"" + passw + "\",";


                // Crear el cuerpo de la solicitud POST como JSON
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

                // Crear la solicitud POST a la URL de Vercel
                Request request = new Request.Builder()
                        .url("http://10.185.82.21:7000/loginUsuarios")
                        .post(requestBody)
                        .build();

                // Crear el cliente HTTP
                OkHttpClient client = new OkHttpClient();


                // Enviar la solicitud asíncronamente
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Manejo del error en caso de fallo de la solicitud
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(inicio_sesion.this, "Error solicitud login", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // Manejo de la respuesta de la solicitud
                        final String responseBody = response.body().string();
                     runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    Toast.makeText(inicio_sesion.this, "Inicio Sesión Exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(inicio_sesion.this, cultupaz.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(inicio_sesion.this, "No se pudo iniciar sesión: ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });

    }
}