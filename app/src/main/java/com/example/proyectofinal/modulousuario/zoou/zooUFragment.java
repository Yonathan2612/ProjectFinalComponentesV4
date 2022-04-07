package com.example.proyectofinal.modulousuario.zoou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentZoouBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class zooUFragment extends Fragment {
    TextView nomZOO,especiesZOO,tamaZOO;
    RequestQueue requestQueue;
    private FragmentZoouBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            zooUViewModel zooUViewModel =
                    new ViewModelProvider(this).get(zooUViewModel.class);

            binding = FragmentZoouBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
        final Button btnBuscarZoo = (Button) root.findViewById(R.id.btnBuscarZOO);
        nomZOO=root.findViewById(R.id.nomZoo);
        especiesZOO=root.findViewById(R.id.especiesZOO);
        tamaZOO=root.findViewById(R.id.tamaZOO);
        btnBuscarZoo.setOnClickListener(this::onClick);


            return root;
    }

    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnBuscarZOO)
        {
            String nombre=nomZOO.getText().toString().trim();
            verZOO(nombre);
        }
    }




    private void verZOO(String Nom){
        String URL="https://compbdzoo.000webhostapp.com/mostrarZOO.php?nombre="+Nom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String nombre, Especies,Tamano;
                        try {
                            nombre = response.getString("nombre");
                            Especies = response.getString("Especies");
                            Tamano = response.getString("Tamano");
                            nomZOO.setText(nombre);
                            especiesZOO.setText(Especies);
                            tamaZOO.setText(Tamano);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}