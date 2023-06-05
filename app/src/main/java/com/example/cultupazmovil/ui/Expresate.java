package com.example.cultupazmovil.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentInformaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;


public class Expresate extends DialogFragment {

    Button buttonenviar;
    EditText temaha, expression;
    private FirebaseFirestore mfirestore;

    private FragmentInformaBinding binding;

    @Override
    public void onCreate (Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInformaBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_expresate, container, false);
        // Inflate the layout for this fragment
        mfirestore = FirebaseFirestore.getInstance();

        temaha = view.findViewById(R.id.temaha);
        expression = view.findViewById(R.id.expression);

        buttonenviar = view.findViewById(R.id.buttonenviar);

        buttonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = temaha.getText().toString().trim();
                String description = expression.getText().toString().trim();


                if (title.isEmpty() && description.isEmpty()){
                    Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postData(title,description);
                }


            }
        });
        return view;
    }

    private void postData(String title, String description) {
        Map<String, Object> map = new HashMap<>();
        map.put("titulo", title);
        map.put("Expresion", description);

        mfirestore.collection("pet").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(@NonNull DocumentReference documentReference) {

                Toast.makeText(getContext(),"Creado exitosamente", Toast.LENGTH_SHORT).show();
              getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();

            }
        });
    }

}