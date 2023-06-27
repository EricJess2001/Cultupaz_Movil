package com.example.cultupazmovil.ui;

import static com.example.cultupazmovil.ui.registro.isValidEmail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.cultupazmovil.MainActivity;
import com.example.cultupazmovil.R;

import org.json.JSONException;
import org.json.JSONObject;

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
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio_sesion);

        reemail = findViewById(R.id.username);
        contraseña = findViewById(R.id.contraseña);
        btn_login = findViewById(R.id.loginButton);
        registrof = findViewById(R.id.registrof);

        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Verificar si el inicio de sesión ya está activo
        boolean estadoInicioSesion = sp.getBoolean("estado_inicio_sesion", false);
        if (estadoInicioSesion) {
            // Redirigir a la actividad principal
            Intent intent = new Intent(inicio_sesion.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() || !validatePassword()) {
                    // Validación fallida
                    return;
                }
                checkUser();
            }
        });

        registrof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirigir a la actividad de registro
                Intent intent = new Intent(inicio_sesion.this, registro.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUsername() {
        String val = reemail.getText().toString().trim();
        if (val.isEmpty()) {
            reemail.setError("Username cannot be empty");
            return false;
        } else {
            reemail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = contraseña.getText().toString().trim();
        if (val.isEmpty()) {
            contraseña.setError("Password cannot be empty");
            return false;
        } else {
            contraseña.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String correo = reemail.getText().toString().trim();
        String passw = contraseña.getText().toString().trim();

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("correo", correo);
            jsonBody.put("passw", passw);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody.toString());

        Request request = new Request.Builder()
                .url("http://10.185.80.70:7000/loginUsuarios")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(inicio_sesion.this, "Error al enviar la solicitud", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseBody = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            try {
                                String responseBody = response.body().string();
                                JSONObject responseJson = new JSONObject(responseBody);
                                String token = responseJson.getString("token");
                                String id = extractIdFromToken(token);

                                if (id != null) {
                                    // Guardar el token y el id en SharedPreferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("estado_inicio_sesion", true);
                                    editor.putString("token", token);
                                    editor.putString("id", id);
                                    editor.apply();

                                    // Mostrar mensaje de éxito
                                    Toast.makeText(inicio_sesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                                    // Redirigir a la siguiente actividad
                                    Intent intent = new Intent(inicio_sesion.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(inicio_sesion.this, "Error al parsear la respuesta", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(inicio_sesion.this, "Error al obtener la respuesta del servidor", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(inicio_sesion.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String extractIdFromToken(String token) {
        String[] chunks = token.split("\\.");
        String payload = new String(android.util.Base64.decode(chunks[1], android.util.Base64.DEFAULT));
        try {
            JSONObject payloadJson = new JSONObject(payload);
            return payloadJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}