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
import com.example.proyectofinal.moduloadministrador.zooa.zooAFragment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CRUDzoo extends zooAFragment
{
    private String nit,nombreZoo,tamano,fk_ciudad,presupuesto_anual;
    public CRUDzoo(String nit, String nombreZoo, String tamano, String fk_ciudad, String presupuesto_anual)
    {
        this.nit = nit;
        this.fk_ciudad = fk_ciudad;
        this.nombreZoo = nombreZoo;
        this.presupuesto_anual = presupuesto_anual;
        this.tamano = tamano;
    }
    public CRUDzoo(){ }

    public boolean insertar(CRUDzoo ob)
    {
        boolean[] estado = {false};

        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarZoologico.php",
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

        return estado[0];
    }

    public  void Editar(CRUDzoo ob)
    {

    }

    public boolean ComprobarExistencia()
    {
        return true;
    }

    public List<CRUDzoo> Listazoo()
    {
        List<CRUDzoo> lista = new List<CRUDzoo>() {
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
            public Iterator<CRUDzoo> iterator() {
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
            public boolean add(CRUDzoo cruDzoo) {
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
            public boolean addAll(@NonNull Collection<? extends CRUDzoo> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends CRUDzoo> c) {
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
            public CRUDzoo get(int index) {
                return null;
            }

            @Override
            public CRUDzoo set(int index, CRUDzoo element) {
                return null;
            }

            @Override
            public void add(int index, CRUDzoo element) {

            }

            @Override
            public CRUDzoo remove(int index) {
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
            public ListIterator<CRUDzoo> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<CRUDzoo> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<CRUDzoo> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        return lista;
    }

    public String TOSTRING()
    {
        return "nit:"+this.getnit()+"\n" +
                "nombreZoo: "+this.getnombreZoo()+"\n" +
                "tamano: "+this.gettamano()+"\n" +
                "fk_ciudad: "+this.getfk_ciudad()+"\n"+
        "presupuesto_anual:"+this.getpresupuesto_anual()+"\n";
    }

    public String getnit() {
        return nit;
    }

    public void setnit(String nit) {
        this.nit = nit;
    }

    public String getnombreZoo() {
        return nombreZoo;
    }

    public void setnombreZoo(String nombreZoo) {
        this.nombreZoo = nombreZoo;
    }

    public String gettamano() {
        return tamano;
    }

    public void settamano(String tamano) {
        this.tamano = tamano;
    }

    public String getfk_ciudad() {
        return fk_ciudad;
    }

    public void setfk_ciudad(String fk_ciudad) {
        this.fk_ciudad = fk_ciudad;
    }

    public String getpresupuesto_anual() {
        return presupuesto_anual;
    }

    public void setpresupuesto_anual(String presupuesto_anual) {this.presupuesto_anual = presupuesto_anual;}

}
