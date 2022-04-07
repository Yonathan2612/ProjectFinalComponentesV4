package com.example.proyectofinal.modulousuario.zoou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.ActivityIngresar;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentZoouBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class zooUFragment extends Fragment {
    TextView nomZOO,especiesZOO,tamaZOO, nombrezoo;
    RequestQueue requestQueue;
    private FragmentZoouBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            zooUViewModel zooUViewModel =
                    new ViewModelProvider(this).get(zooUViewModel.class);

            binding = FragmentZoouBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            final Button btnBuscarZoo = (Button) root.findViewById(R.id.btnBuscarZOO);
            nomZOO=(EditText)root.findViewById(R.id.nomZoo2);
             especiesZOO=(EditText)root.findViewById(R.id.especiesZOO);
             tamaZOO=(EditText)root.findViewById(R.id.tamaZOO);
            nombrezoo=(EditText) root.findViewById(R.id.nomZoo);
             btnBuscarZoo.setOnClickListener(this::onClick);


            return root;
    }

    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnBuscarZOO)
        {
            String nombre=nomZOO.getText().toString().trim();
            if(nombre.isEmpty())
            {
                Toast.makeText(getContext(), "El campo debe ser valido", Toast.LENGTH_SHORT).show();


            }else
            {
                verZOO(nombre);
            }

        }
    }




    private void verZOO(String Nom)
    {
        String URL="https://compbdzoo.000webhostapp.com/mostrarZOO.php?nombre="+Nom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                        String nombre, Especies,Tamano;
                        try {
                            nombre = response.getString("nombreZoo");
                            Especies = response.getString("especies");
                            Tamano = response.getString("tamano");
                            nombrezoo.setText(nombre);
                            especiesZOO.setText(Especies);
                            tamaZOO.setText(Tamano);
                        } catch (JSONException e)
                        {
                            Toast.makeText(getContext(), "ERROR EN: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getContext(), "El ZOO no fue encontrado ", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}