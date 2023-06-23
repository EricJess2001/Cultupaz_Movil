package com.example.cultupazmovil.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.adapter.PetAdapter;
import com.google.firebase.firestore.FirebaseFirestore;


public class Peticiones extends DialogFragment {

    RecyclerView mRecycler;

    PetAdapter mAdapter;

    FirebaseFirestore mFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peticiones, container, false);
    }

}