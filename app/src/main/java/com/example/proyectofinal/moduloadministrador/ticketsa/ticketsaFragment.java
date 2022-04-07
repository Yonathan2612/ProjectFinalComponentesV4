package com.example.proyectofinal.moduloadministrador.ticketsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Modelo.CRUDticket;
import com.example.proyectofinal.ActivityReportes;
import com.example.proyectofinal.Modelo.CRUDzoo;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentTicketsaBinding;

import java.util.HashMap;
import java.util.Map;

public class ticketsaFragment extends Fragment {
    private FragmentTicketsaBinding binding;
    EditText editTextNom, editTextPrecio, editTextBuscarZoo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ticketsaViewModel ticketsaViewModel =
                new ViewModelProvider(this).get(ticketsaViewModel.class);

        binding = FragmentTicketsaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnCargarTicket = (Button) root.findViewById(R.id.btnCargarTicket);
        final Button btnReportes = (Button) root.findViewById(R.id.btnReportes);
        editTextBuscarZoo=root.findViewById(R.id.editTextBuscarZoo);
        editTextNom=root.findViewById(R.id.editTextNom);
        editTextPrecio=root.findViewById(R.id.editTextPrecio);
        btnCargarTicket.setOnClickListener(this::onClick);
        btnReportes.setOnClickListener(this::onClick);

        return root;
    }
    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnReportes){
            Intent intent= new Intent(getActivity(),ActivityReportes.class);
            startActivity(intent);
        }

        if(id==R.id.btnCargarTicket)
        {
            //comprobacion de que cualquier campo no este vacio
            String[] a = {editTextPrecio.getText().toString(),editTextNom.getText().toString()};
            boolean vacio=false;
            for (String aux:a)
            {
                if(aux.isEmpty())
                {
                    vacio=true;
                    break;
                }
            }
//controlando que los campos cumplan con los requisitos de la bd y no se pasen de los caracteres
            if(vacio)
            {
                Toast.makeText(getContext(), "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show();
            }else
            {
                //llenando el objeto de animales enviandolo a la funcion insertar
                CRUDticket ob = new CRUDticket();
                ob.setprecio(editTextPrecio.getText().toString().trim());
                ob.setnombre(editTextNom.getText().toString().trim());
                insertarticket(ob);
            }
        }
    }
    private void insertarticket(CRUDticket ob)
    {
        // aqui se pone el link de la base de datos de la nube
        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarAnimal.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equalsIgnoreCase("Registro con éxito"))
                        {
                            Toast.makeText(getContext(), "Registro no fue éxitoso", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "El registro fue exitoso", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                //con map se envian los parametros por el link con el methodo post
                Map<String,String> params = new HashMap<String,String>();
                params.put("nombre",ob.getnombre());
                params.put("precio", ob.getprecio());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}