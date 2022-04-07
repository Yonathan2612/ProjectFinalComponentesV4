package com.example.proyectofinal.moduloadministrador.zooa;

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
import com.example.proyectofinal.Modelo.CRUDzoo;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentZooaBinding;
import com.example.proyectofinal.moduloadministrador.animalesa.animalesAFragment;
import com.example.proyectofinal.moduloadministrador.zooa.zooAFragment;

import java.util.HashMap;
import java.util.Map;

public class zooAFragment extends Fragment {

    private FragmentZooaBinding binding;
    EditText editTextPresupuesto, editTextTamaño, editTextNomzoo, editTextCiudad,editTextNit;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zooAViewModel galleryViewModel =
                new ViewModelProvider(this).get(zooAViewModel.class);

        binding = FragmentZooaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnAgregar = (Button) root.findViewById(R.id.btnAgregar);
        editTextCiudad = root.findViewById(R.id.editTextCiudad);
        editTextNit = root.findViewById(R.id.editTextNom);
        editTextNomzoo = root.findViewById(R.id.editTextNomzoo);
        editTextPresupuesto = root.findViewById(R.id.editTextPresupuesto);
        editTextTamaño = root.findViewById(R.id.editTextTamaño);
        btnAgregar.setOnClickListener(this::onClick);

        return root;
    }
    private void onClick(View v)
    {
        //validacion para los startActivitis
        int id=v.getId();
        if(id==R.id.btnAgregar)
        {
            //comprobacion de que cualquier campo no este vacio
            String[] a = {editTextCiudad.getText().toString(),editTextNit.getText().toString()
                    , editTextNomzoo.getText().toString(), editTextTamaño.getText().toString()};
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
            }else if(zooAFragment.isNumeric(editTextTamaño.getText().toString())){
                if(zooAFragment.isNumeric(editTextPresupuesto.getText().toString()) || editTextPresupuesto == null){
                //llenando el objeto de animales enviandolo a la funcion insertar
                CRUDzoo ob = new CRUDzoo();
                ob.setnit(editTextNit.getText().toString().trim());
                ob.setnombreZoo(editTextNomzoo.getText().toString().trim());
                ob.setfk_ciudad(editTextCiudad.getText().toString().trim());
                ob.settamano(editTextTamaño.getText().toString().trim());
                ob.setpresupuesto_anual(editTextPresupuesto.getText().toString().trim());
                insertarzoo(ob);
                }else{
                    Toast.makeText(getContext(), "El presupuesto debe ser numerico", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getContext(), "El tamaño debe ser numerico", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    private void insertarzoo(CRUDzoo ob)
    {
        // aqui se pone el link de la base de datos de la nube
        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarZoo.php",
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
                params.put("nit",ob.getnit());
                params.put("nombreZoo", ob.getnombreZoo());
                params.put("tamano",ob.gettamano());
                params.put("fk_ciudad",ob.getfk_ciudad());
                params.put("presupuesto_anual",ob.getpresupuesto_anual());

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