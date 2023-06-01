package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentCulturaBinding;
import com.example.cultupazmovil.databinding.FragmentInformaBinding;


public class Cultura extends Fragment {

    private FragmentCulturaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCulturaBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_cultura, container, false);


        ImageView regresoin = (ImageView) view.findViewById(R.id.regresoin);

        regresoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Informa menussin = new Informa();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, menussin)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}