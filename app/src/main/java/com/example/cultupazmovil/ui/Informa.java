package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentInformaBinding;
import com.example.cultupazmovil.databinding.FragmentMenuBinding;


public class Informa extends Fragment {

   private FragmentInformaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInformaBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_informa, container, false);


        ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);


        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cultura culturaProbando = new Cultura();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, culturaProbando)
                        .commit();

            }
        });


        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lgtbi lgtbiprobando = new lgtbi();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, lgtbiprobando)
                        .commit();

            }
        });




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}