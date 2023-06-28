package com.example.cultupazmovil.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cultupazmovil.R;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registro extends AppCompatActivity {

    ImageView regreso;

    EditText contraseña, renombre, reapellido,
            reNumeroiden, reemail, retelefono, reusuario, confcontraseña;
    Spinner spinner_documento, spinner_genero;

    String idTipo, estadoUsuario, foto;

    Button registrarse;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        regreso = findViewById(R.id.regreso);

        idTipo = "1";
        estadoUsuario = "1";
        foto = "null";

        contraseña = findViewById(R.id.contraseña);
        confcontraseña = findViewById(R.id.confirmarcontraseña);
        renombre = findViewById(R.id.nombre);
        reapellido = findViewById(R.id.apellido);
        reNumeroiden = findViewById(R.id.Numeroiden);
        reemail = findViewById(R.id.email);
        retelefono = findViewById(R.id.telefono);
        registrarse = findViewById(R.id.registrarse);
        spinner_documento = findViewById(R.id.spinner_documento);
        spinner_genero = findViewById(R.id.spinner_generoo);
        reusuario = findViewById(R.id.usuario);

        // Configurar los adaptadores para los spinners
        ArrayAdapter<CharSequence> tipoAdapter = ArrayAdapter.createFromResource(this, R.array.documento_array, android.R.layout.simple_spinner_item);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_documento.setAdapter(tipoAdapter);

        ArrayAdapter<CharSequence> generoAdapter = ArrayAdapter.createFromResource(this, R.array.genero_array, android.R.layout.simple_spinner_item);
        generoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genero.setAdapter(generoAdapter);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos ingresados por el usuario
                String confirPassw = confcontraseña.getText().toString();
                String passw = contraseña.getText().toString();
                String nombres = renombre.getText().toString();
                String apellidos = reapellido.getText().toString();
                String tipoDocumento = spinner_documento.getSelectedItem().toString();
                String numeroDocumento = reNumeroiden.getText().toString();
                String genero = spinner_genero.getSelectedItem().toString();
                String correo = reemail.getText().toString();
                String telefono = retelefono.getText().toString();
                String usuario = reusuario.getText().toString();

                // Boton de regreso al login
                // ...

                // Verificar si el correo ingresado es válido
                if (!isValidEmail(correo)) {
                    Toast.makeText(registro.this, "Correo inválido", Toast.LENGTH_SHORT).show();
                    return;
                }



                // Crear el objeto JSON con los datos del usuario
                String jsonBody = "{\"confirPassw\":\"" + confirPassw + "\","
                        + "\"passw\":\"" + passw + "\","
                        + "\"foto\":\"" + foto + "\","
                        + "\"nombres\":\"" + nombres + "\","
                        + "\"apellidos\":\"" + apellidos + "\","
                        + "\"tipoDocumento\":\"" + tipoDocumento + "\","
                        + "\"numeroDocumento\":\"" + numeroDocumento + "\","
                        + "\"correo\":\"" + correo + "\","
                        + "\"idTipo\":\"" + idTipo + "\","
                        + "\"telefono\":\"" + telefono + "\","
                        + "\"usuario\":\"" + usuario + "\","
                        + "\"estadoUsuario\":\"" + estadoUsuario + "\","
                        + "\"genero\":\"" + genero + "\"}";

                // Crear el cuerpo de la solicitud POST como JSON
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

                // Crear la solicitud POST a la URL de Vercel
                Request request = new Request.Builder()
                        .url("http://10.185.81.241:7000/registroUsuarios")
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
                                Toast.makeText(registro.this, "Error al enviar la solicitud", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(registro.this, "Te has registrado correctamente", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registro.this, inicio_sesion.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(registro.this, "Error al registrar: " + responseBody, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    // Verificar si el correo es válido utilizando una expresión regular
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Verificar si la contraseña cumple con los requisitos mínimos utilizando una expresión regular
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@.#$%¿)><(*´}{°|~`^&+=!?¡]).*$";
        return password.matches(passwordRegex);
    }


}
