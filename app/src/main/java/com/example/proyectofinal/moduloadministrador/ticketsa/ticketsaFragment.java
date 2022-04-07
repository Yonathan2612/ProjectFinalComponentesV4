package com.example.proyectofinal.moduloadministrador.ticketsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.ActivityAdmin;
import com.example.proyectofinal.ActivityAdministrador;
import com.example.proyectofinal.ActivityReportes;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentTicketsaBinding;

public class ticketsaFragment extends Fragment {
    Button btnReportes;
    private FragmentTicketsaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ticketsaViewModel ticketsaViewModel =
                new ViewModelProvider(this).get(ticketsaViewModel.class);

        binding = FragmentTicketsaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnReportes = (Button) root.findViewById(R.id.btnReportes);
        btnReportes.setOnClickListener(this::onClick);
        return root;
    }
    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnReportes){
            Intent intent= new Intent(getActivity(),ActivityReportes.class);
            startActivity(intent);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}