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
import com.example.cultupazmovil.databinding.FragmentLgtbiBinding;

public class lgtbi extends Fragment {


    private FragmentLgtbiBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLgtbiBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_lgtbi, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})


        ImageView regresoinf2 = (ImageView) view.findViewById(R.id.regresoinf2);

        regresoinf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Informa menussinf = new Informa();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, menussinf)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}