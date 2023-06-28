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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Load the image resource with scaling
        loadScaledImage();
    }

    private void loadScaledImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // Scale down the image by a factor of 2

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.artesania, options);
        // Set the scaled bitmap to an ImageView
        binding.imgsvg1.setImageBitmap(bitmap);
    }
}