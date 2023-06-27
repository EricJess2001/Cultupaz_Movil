package com.example.cultupazmovil.ui;

<<<<<<< HEAD
import android.annotation.SuppressLint;
=======
import static androidx.databinding.DataBindingUtil.setContentView;

>>>>>>> 32aba48a889447a5fff9a0f1f4badbc74e94f917
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.adapter.PetAdapter;
import com.example.cultupazmovil.databinding.FragmentInformaBinding;

public class Expresate extends DialogFragment {

    Button buttonenviar;


    EditText tema, expresion;

    private FragmentInformaBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

<<<<<<< HEAD
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInformaBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_expresate, container, false);
        // Inflate the layout for this fragment
        mfirestore = FirebaseFirestore.getInstance();

        tema = view.findViewById(R.id.temaha);
        expresion = view.findViewById(R.id.expression);

        buttonenviar = view.findViewById(R.id.btn_empezar);

        buttonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tema.getText().toString().trim();
                String description = expresion.getText().toString().trim();

                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postData(title, description);
                }
            }
        });

        // RecyclerView setup
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = view.findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = mFirestore.collection("expresiones");

        FirestoreRecyclerOptions<Pet> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Pet>().setQuery(query, Pet.class).build();

        mAdapter = new PetAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        return view;
    }

    private void postData(String title, String description) {
        Map<String, Object> map = new HashMap<>();
        map.put("titulo", title);
        map.put("Expresion", description);

        mfirestore.collection("expresiones").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(@NonNull DocumentReference documentReference) {
                Toast.makeText(getContext(),"Creado exitosamente", Toast.LENGTH_SHORT).show();
                tema.setText("");
                expresion.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
=======

>>>>>>> 32aba48a889447a5fff9a0f1f4badbc74e94f917
}






