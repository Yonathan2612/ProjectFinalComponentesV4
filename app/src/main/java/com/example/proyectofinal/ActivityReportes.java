package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityReportes extends AppCompatActivity {
    Button btnVerReportes;
    TextView txtFecha,idticket,NombreZoo,nomUser,cantidadB;
    RequestQueue requestQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
    btnVerReportes=(Button) findViewById(R.id.btnVerReporte);
    txtFecha=(TextView) findViewById(R.id.txtDate);
    idticket=(TextView) findViewById(R.id.idTicket);
    NombreZoo=(TextView) findViewById(R.id.NombreZOO);
    nomUser=(TextView) findViewById(R.id.nomUser);
    cantidadB=(TextView) findViewById(R.id.cantidadB);
    }

    private void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnBuscarAnimal){
            String fecha=txtFecha.getText().toString().trim();
            verReporte(fecha);
        }
    }


    private void verReporte(String fecha){
        String URL="https://compbdzoo.000webhostapp.com/mostrarReporte.php?fecha="+fecha;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String id_ticket,zoo,Nombre,cantidadBoletos;
                        try {
                            id_ticket = response.getString("id_ticket");
                            zoo= response.getString("zoo");
                            Nombre = response.getString("Nombre");
                            cantidadBoletos = response.getString("cantidadBoletos");
                            idticket.setText(id_ticket);
                            NombreZoo.setText(zoo);
                            nomUser.setText(Nombre);
                            cantidadB.setText(cantidadBoletos);
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




}