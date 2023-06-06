package com.example.cultupazmovil.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.databinding.FragmentExpresateBinding;

public class Expresate extends DialogFragment {

    private FragmentExpresateBinding binding;


    Button button;
    EditText temaha, expression;


    @Override

    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}



        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_expresate, container, false);
        }
    }
