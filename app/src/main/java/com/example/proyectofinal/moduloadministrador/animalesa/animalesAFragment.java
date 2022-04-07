package com.example.proyectofinal.moduloadministrador.animalesa;

import android.content.Intent;
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
import com.example.proyectofinal.ActivityRegistrar;
import com.example.proyectofinal.Modelo.CRUDanimales;
import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentAnimalesaBinding;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class animalesAFragment extends Fragment {

    private FragmentAnimalesaBinding binding;
    EditText txtNomVulgar,txtNomCientifico,txtFamilia,txtExtincion,txtIdentificacion,txtEspecie,txtGenero,txtNacimiento,txtZoo,txtPaisor,txtContinente;
    Button btnCargarZoo;
    public static List<CRUDanimales> lista = new List<CRUDanimales>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<CRUDanimales> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(CRUDanimales cruDanimales) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends CRUDanimales> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends CRUDanimales> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public CRUDanimales get(int index) {
                return null;
            }

            @Override
            public CRUDanimales set(int index, CRUDanimales element) {
                return null;
            }

            @Override
            public void add(int index, CRUDanimales element) {

            }

            @Override
            public CRUDanimales remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<CRUDanimales> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<CRUDanimales> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<CRUDanimales> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

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
        txtPaisor = root.findViewById(R.id.txtPaisOr);
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
            }else
            {
                //llenando el objeto de Usuario enviandolo a la funcion insertar

                CRUDanimales ob = new CRUDanimales();
                ob.setnom_vulgar(txtNomVulgar.getText().toString().trim());
                ob.setnom_cientifico(txtNomCientifico.getText().toString().trim());
                ob.settxtfamilia(txtFamilia.getText().toString().trim());
                ob.settxtespecie(txtEspecie.getText().toString().trim());
                ob.settxtextincion(txtExtincion.getText().toString().trim());
                ob.settxtidentificacion(txtIdentificacion.getText().toString().trim());
                ob.settxtnacimiento(txtNacimiento.getText().toString().trim());
                ob.settxtgenero(txtGenero.getText().toString().trim());
                ob.settxtzoo(txtZoo.getText().toString().trim());
                ob.settxtcontinente(txtContinente.getText().toString().trim());
                insertarAnimal(ob);

            }
            //direccionando el boton volver al login
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
                params.put("txtfamilia",ob.gettxtfamilia());
                params.put("txtextincion",ob.gettxtextincion());
                params.put("txtidentificacion",ob.gettxtidentificacion());
                params.put("txtespecie",ob.gettxtespecie());
                params.put("txtgenero",ob.gettxtgenero());
                params.put("txtnacimiento",ob.gettxtnacimiento());
                params.put("txtzoo",ob.gettxtzoo());
                params.put("txtpaisor",ob.gettxtpaisor());
                params.put("txtcontinente",ob.gettxtcontinente());

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