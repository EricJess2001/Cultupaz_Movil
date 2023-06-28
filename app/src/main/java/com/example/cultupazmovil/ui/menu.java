package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cultupazmovil.MainActivity;
import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentMenuBinding;


public class menu extends Fragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        LinearLayout tem1 = (LinearLayout) view.findViewById(R.id.tem1);
        LinearLayout tem3 = (LinearLayout) view.findViewById(R.id.tem3);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button open = (Button) view.findViewById(R.id.closedsesion);


        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), inicio_sesion.class);
                startActivity(intent);

            }
        });


        tem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Informa probando4 = new Informa();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, probando4)
                        .commit();
            }
        });

        tem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagens imagenesp = new imagens();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, imagenesp)
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