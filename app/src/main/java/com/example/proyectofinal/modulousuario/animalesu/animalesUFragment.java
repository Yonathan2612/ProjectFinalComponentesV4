package com.example.proyectofinal.modulousuario.animalesu;

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
import com.example.proyectofinal.databinding.FragmentAnimalesuBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class animalesUFragment extends Fragment
{
    TextView nomAnimal,nom_vulgar,espAnimal,pesoAnim;
    RequestQueue requestQueue;
    private FragmentAnimalesuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        animalesUViewModel animalesUViewModel =
                new ViewModelProvider(this).get(animalesUViewModel.class);

        binding = FragmentAnimalesuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnBuscarAnimal = (Button) root.findViewById(R.id.btnBuscarAnimal);
        nomAnimal=root.findViewById(R.id.nomAnimal);
        nom_vulgar=root.findViewById(R.id.nom_vulgar);
        espAnimal=root.findViewById(R.id.espAnimal);
        pesoAnim=root.findViewById(R.id.pesoAnim);
        btnBuscarAnimal.setOnClickListener(this::onClick);
        return root;
    }

    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnBuscarAnimal){
            String nombre=nomAnimal.getText().toString().trim();
            verAnimal(nombre);
        }
    }


    private void verAnimal(String Nom){
        String URL="https://compbdzoo.000webhostapp.com/mostrarAnimales.php?nombre="+Nom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String nom_vul,espAni,pesoAni;
                        try {
                            espAni = response.getString("Nombre_especie");
                            nom_vul= response.getString("nombre_vulgar");
                            pesoAni = response.getString("peso");
                            espAnimal.setText(espAni);
                            nom_vulgar.setText(nom_vul);
                            pesoAnim.setText(pesoAni);
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