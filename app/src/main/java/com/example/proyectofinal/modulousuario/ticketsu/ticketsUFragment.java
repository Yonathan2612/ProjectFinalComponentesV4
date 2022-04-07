package com.example.proyectofinal.modulousuario.ticketsu;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.proyectofinal.ActivityIngresar;
import com.example.proyectofinal.ActivityRegistrar;
import com.example.proyectofinal.R;
import com.example.proyectofinal.UsuarioActivity;
import com.example.proyectofinal.databinding.FragmentTicketsuBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ticketsUFragment extends Fragment
{

    private FragmentTicketsuBinding binding;
    private EditText txtID, txtBoletos;
    private Button btnComprar;
    private Spinner SpinZoo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        ticketsUViewModel ticketsUViewModel =
                new ViewModelProvider(this).get(ticketsUViewModel.class);

        binding = FragmentTicketsuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtID = (EditText) root.findViewById(R.id.edtIdTicket);
        txtBoletos =(EditText) root.findViewById(R.id.edtCantidadBoletos);
        SpinZoo =(Spinner) root.findViewById(R.id.spinnerZOO);
        btnComprar = (Button) root.findViewById(R.id.btnComprarT);

        btnComprar.setOnClickListener(this::Comprar);














        return root;


    }

    private void Comprar(View view)
    {
        if(txtID.getText().equals("") || txtID.getText()==null || txtBoletos.getText().equals("") || txtBoletos.getText()== null)
        {
            Toast.makeText(getContext(), "Los valores deben ser válidos", Toast.LENGTH_SHORT).show();
        }else
        {

            String id = txtID.getText().toString().trim();
            String Boletos = txtBoletos.getText().toString().trim();
            Time fecha = new Time(Time.getCurrentTimezone());
            fecha.setToNow();



            String txtFecha = "Fecha de hoy: "+fecha.year +"-"+(fecha.month+1)+"-"+(fecha.monthDay);

            InsertarTicket(id, Boletos, txtFecha, Boletos);


        }


    }

    private void InsertarTicket(String id, String boletos, String txtFecha, String cantidad)
    {
        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/InsertarTicket.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        if(response.equalsIgnoreCase("BOLETOS COMPRADOS CON ÉXITO"))
                        {
                            Toast.makeText(getContext(), "HUBO UN ERROR EN LA COMPRA", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "BOLETOS COMPRADOS CON ÉXITO", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getContext(), UsuarioActivity.class);
                            startActivity(intent1);
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
                params.put("fk_zoo","2152");
                params.put("fk_idusuario",id);
                params.put("FechaCompra",txtFecha);
                params.put("CantidadBoletos",cantidad);

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