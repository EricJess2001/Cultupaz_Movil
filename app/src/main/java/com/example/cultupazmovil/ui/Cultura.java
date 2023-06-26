package com.example.cultupazmovil.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentCulturaBinding;

public class Cultura extends Fragment {

    private FragmentCulturaBinding binding;
    TextView textView8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCulturaBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_cultura, container, false);

        ImageView regresoin = view.findViewById(R.id.regresoin);

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

        textView8 = view.findViewById(R.id.textView8);
        textView8.setMovementMethod(LinkMovementMethod.getInstance());
        textView8.setLinkTextColor(Color.BLUE);

        return view;
    }
}
