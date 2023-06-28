package com.example.cultupazmovil.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.adapter.MuroAdapter;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumoMuro extends Fragment {

    private List<Muro> verMuro;
    private RecyclerView recyclerViewmuro1;
    private MuroAdapter muroAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expresate, container, false);

        recyclerViewmuro1 = view.findViewById(R.id.recyclerViewmuro1);
        recyclerViewmuro1.setLayoutManager(new GridLayoutManager(getContext(), 1));

        muroAdapter = new MuroAdapter(verMuro, getContext());
        recyclerViewmuro1.setAdapter(muroAdapter);

        showverMuro();

        return view;
    }

    public void showverMuro() {
        Call<List<Muro>> call = ApiMuroo.getApiMuro().create(Apimuro.class).getverMuro();
        call.enqueue(new Callback<List<Muro>>() {
            @Override
            public void onResponse(Call<List<Muro>> call, Response<List<Muro>> response) {
                if (response.isSuccessful()) {
                    verMuro = response.body();
                    muroAdapter = new MuroAdapter(verMuro, getContext());
                    recyclerViewmuro1.setAdapter(muroAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Muro>> call, Throwable t) {
                Toast.makeText(getActivity(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }
}