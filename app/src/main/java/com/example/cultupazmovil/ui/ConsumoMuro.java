package com.example.cultupazmovil.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.cultupazmovil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumoMuro extends AppCompatActivity {

    private LinearLayout muroLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muro);

        muroLayout = findViewById(R.id.linerMuro);

        SharedPreferences sharedPreferences = getSharedPreferences("Cultupaz", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("idUsuario", "");
        Toast.makeText(this, "idUsuario: " + idUsuario, Toast.LENGTH_SHORT).show();
        mostrarNotificacionesPro(idUsuario);
    }

    private void mostrarNotificacionesPro(String idUsuario) {
        new HttpGetTaskPro().execute("http://192.168.20.8:7000/verMisPublicaciones/" + idUsuario);
    }

    private class HttpGetTaskPro extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            convertirAResultado(response);
        }

        private void convertirAResultado(String response) {
            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String titulo = jsonObject.getString("titulo");
                    String descripcion = jsonObject.getString("descripcion");

                    // Crear un CardView para cada elemento
                    CardView cardView = new CardView(ConsumoMuro.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(16, 16, 16, 16); // Ajusta el margen inferior
                    cardView.setLayoutParams(layoutParams);
                    cardView.setCardElevation(8); // Controla la sombra y la apariencia flotante

                    // Crear un LinearLayout para contener los textos en el CardView
                    LinearLayout cardContentLayout = new LinearLayout(ConsumoMuro.this);
                    cardContentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    cardContentLayout.setOrientation(LinearLayout.VERTICAL);
                    cardContentLayout.setPadding(16, 16, 16, 16);

                    // Crear los TextView y añadirlos al cardContentLayout
                    TextView tituloTextView = new TextView(ConsumoMuro.this);
                    tituloTextView.setText(titulo);
                    tituloTextView.setTextColor(getResources().getColor(R.color.black));
                    tituloTextView.setTextSize(20);
                    cardContentLayout.addView(tituloTextView);

                    TextView descripcionTextView = new TextView(ConsumoMuro.this);
                    descripcionTextView.setText(descripcion);
                    descripcionTextView.setTextSize(20);
                    cardContentLayout.addView(descripcionTextView);

                    // Añadir el cardContentLayout al CardView
                    cardView.addView(cardContentLayout);

                    // Añadir el CardView al muroLayout
                    muroLayout.addView(cardView);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}