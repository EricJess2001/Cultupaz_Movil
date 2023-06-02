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
import com.example.cultupazmovil.databinding.FragmentImagensBinding;
import com.example.cultupazmovil.databinding.FragmentInformaBinding;


public class imagens extends Fragment {

    private FragmentImagensBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentImagensBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_imagens, container, false);
        // Inflate the layout for this fragment

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView regresomenu =(ImageView) view.findViewById(R.id.regresomenu);

         regresomenu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 menu menusm = new menu();

                 FragmentManager fragmentManager = getFragmentManager();

                 fragmentManager.beginTransaction()
                         .replace(R.id.nav_host_fragment_activity_main, menusm)
                         .commit();

             }
         });

        return view;
    }
}