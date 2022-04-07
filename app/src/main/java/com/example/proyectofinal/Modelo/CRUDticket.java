package com.example.proyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.moduloadministrador.ticketsa.ticketsaFragment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CRUDticket extends ticketsaFragment
{
    private String nombre,precio;
    public CRUDticket(String nombre, String precio)
    {
        this.nombre = nombre;
        this.precio = precio;
    }
    public CRUDticket(){ }

    public boolean insertar(CRUDticket ob)
    {
        boolean[] estado = {false};

        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarAnimal.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Registro con éxito")){
                           // Toast.makeText(ActivityRegistrar.this, "Registro no fue éxitoso", Toast.LENGTH_SHORT).show();
                            estado[0]=false;
                        }
                        else{
                            //Toast.makeText(ActivityRegistrar.this, "El registro fue exitoso por favor ingrese", Toast.LENGTH_SHORT).show();
                            estado[0]=true;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("nombre",ob.getnombre());
                params.put("precio", ob.getprecio());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

        return estado[0];
    }

    public  void Editar(CRUDticket ob)
    {

    }

    public boolean ComprobarExistencia()
    {
        return true;
    }

    public List<CRUDticket> Listazoo()
    {
        List<CRUDticket> lista = new List<CRUDticket>() {
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
            public Iterator<CRUDticket> iterator() {
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
            public boolean add(CRUDticket cruDticket) {
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
            public boolean addAll(@NonNull Collection<? extends CRUDticket> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends CRUDticket> c) {
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
            public CRUDticket get(int index) {
                return null;
            }

            @Override
            public CRUDticket set(int index, CRUDticket element) {
                return null;
            }

            @Override
            public void add(int index, CRUDticket element) {

            }

            @Override
            public CRUDticket remove(int index) {
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
            public ListIterator<CRUDticket> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<CRUDticket> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<CRUDticket> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        return lista;
    }

    public String TOSTRING()
    {
        return "nombre:"+this.getnombre()+"\n" +
                "precio: "+this.getprecio()+"\n";
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getprecio() {
        return precio;
    }

    public void setprecio(String precio) {
        this.precio = precio;
    }
}
