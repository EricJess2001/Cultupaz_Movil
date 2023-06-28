package com.example.cultupazmovil.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentInfoWebBinding;

public class info_web extends Fragment {

    private FragmentInfoWebBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInfoWebBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }




}