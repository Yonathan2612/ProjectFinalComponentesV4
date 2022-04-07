package com.example.proyectofinal.moduloadministrador.animalesa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Modelo.CRUDanimales;
import com.example.proyectofinal.R;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import com.example.proyectofinal.databinding.FragmentAnimalesaBinding;


public class animalesAFragment extends Fragment {

    private FragmentAnimalesaBinding binding;
    EditText txtNomVulgar,txtNomCientifico,txtFamilia,txtExtincion,txtIdentificacion,txtEspecie,txtGenero,txtNacimiento,txtZoo,txtPaisor,txtContinente;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        animalesAViewModel animalesAViewModel =
                new ViewModelProvider(this).get(animalesAViewModel.class);

        binding = FragmentAnimalesaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnAgregar = (Button) root.findViewById(R.id.btnAgregar);
        txtNomVulgar = root.findViewById(R.id.txtNomVulgar);
        txtNomCientifico = root.findViewById(R.id.txtNomCientifico);
        txtFamilia = root.findViewById(R.id.txtFamilia);
        txtEspecie = root.findViewById(R.id.txtEspecie);
        txtExtincion = root.findViewById(R.id.txtExistinción);
        txtIdentificacion = root.findViewById(R.id.txtIdentificacion);
        txtEspecie = root.findViewById(R.id.txtEspecie);
        txtGenero = root.findViewById(R.id.txtGenero);
        txtNacimiento = root.findViewById(R.id.txtNacimiento);
        txtZoo = root.findViewById(R.id.txtZoo);
        txtPaisor = root.findViewById(R.id.txtPaisor);
        txtContinente = root.findViewById(R.id.txtContinente);
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
            String[] a = {txtNomVulgar.getText().toString(),txtNomCientifico.getText().toString()
                    , txtFamilia.getText().toString(), txtEspecie.getText().toString()
                    , txtExtincion.getText().toString() , txtIdentificacion.getText().toString()
                    , txtNacimiento.getText().toString() , txtGenero.getText().toString()
                    , txtZoo.getText().toString(), txtPaisor.getText().toString()
                    , txtContinente.getText().toString()};
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
            }else if(animalesAFragment.isNumeric(txtIdentificacion.getText().toString())){
                if(animalesAFragment.isNumeric(txtNacimiento.getText().toString()) && txtNacimiento.getText().length()<5 && txtNacimiento.getText().length()>3){
                //llenando el objeto de animales enviandolo a la funcion insertar
                CRUDanimales ob = new CRUDanimales();
                ob.setnom_vulgar(txtNomVulgar.getText().toString().trim());
                ob.setnom_cientifico(txtNomCientifico.getText().toString().trim());
                ob.setfamilia(txtFamilia.getText().toString().trim());
                ob.setespecie(txtEspecie.getText().toString().trim());
                ob.setextincion(txtExtincion.getText().toString().trim());
                ob.setidentificacion(txtIdentificacion.getText().toString().trim());
                ob.setnacimiento(txtNacimiento.getText().toString().trim());
                ob.setgenero(txtGenero.getText().toString().trim());
                ob.setzoo(txtZoo.getText().toString().trim());
                ob.setpaisor(txtPaisor.getText().toString().trim());
                ob.setcontinente(txtContinente.getText().toString().trim());
                insertarAnimal(ob);
                }else{
                    Toast.makeText(getContext(), "Campo fecha nacimiento debe ser numerico y longitud de 4 digitos", Toast.LENGTH_SHORT).show();
                }
            }else
            {
                Toast.makeText(getContext(), "Campo identificación debe ser numerico", Toast.LENGTH_SHORT).show();
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
    private void insertarAnimal(CRUDanimales ob)
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
                params.put("nom_vulgar",ob.getnom_vulgar());
                params.put("nom_cientifico",ob.getnom_cientifico());
                params.put("familia",ob.getfamilia());
                params.put("extincion",ob.getextincion());
                params.put("identificacion",ob.getidentificacion());
                params.put("especie",ob.getespecie());
                params.put("genero",ob.getgenero());
                params.put("nacimiento",ob.getnacimiento());
                params.put("zoo",ob.getzoo());
                params.put("paisor",ob.getpaisor());
                params.put("continente",ob.getcontinente());

                return params;
            }
        };

        //ejecutando la direccion ip dirigida a la base de datos
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}