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
import com.example.proyectofinal.moduloadministrador.animalesa.animalesAFragment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CRUDanimales extends animalesAFragment
{
    private String nom_vulgar,nom_cientifico,familia,extincion,identificacion,especie,genero,nacimiento,zoo,paisor,continente;
    public CRUDanimales(String nom_vulgar, String nom_cientifico, String txtfamilia, String txtextincion, String txtidentificacion, String txtespecie, String txtgenero, String txtnacimiento, String txtzoo, String txtpaisor, String txtcontinente)
    {
        this.nom_vulgar = nom_vulgar;
        this.nom_cientifico = nom_cientifico;
        this.familia = txtfamilia;
        this.extincion = txtextincion;
        this.identificacion = txtidentificacion;
        this.especie = txtespecie;
        this.genero = txtgenero;
        this.nacimiento = txtnacimiento;
        this.zoo = txtzoo;
        this.paisor = txtpaisor;
        this.continente = txtcontinente;
    }
    public CRUDanimales(){ }

    public boolean insertar(CRUDanimales ob)
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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);


        return estado[0];
    }

    public  void Editar(CRUDanimales ob)
    {

    }

    public boolean ComprobarExistencia()
    {
        return true;
    }

    public List<CRUDanimales> ListaAnimales()
    {
        List<CRUDanimales> lista = new List<CRUDanimales>() {
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

        return lista;
    }

    public boolean Login(String correo, String password)
    {
        return false;
    }

    public String TOSTRING()
    {
        return "nom_vulgar:"+this.getnom_vulgar()+"\n" +
                "nom_cientifico: "+this.getnom_cientifico()+"\n" +
                "familia: "+this.getfamilia()+"\n" +
                "extincion: "+this.getextincion()+"\n"+
        "identificacion:"+this.getidentificacion()+"\n" +
                "especie: "+this.getespecie()+"\n" +
                "genero: "+this.getgenero()+"\n" +
                "nacimiento: "+this.getnacimiento()+"\n"+
        "oo:"+this.getzoo()+"\n" +
                "paisor: "+this.getpaisor()+"\n" +
                "continente: "+this.getcontinente()+"\n" ;
    }

    public String getnom_vulgar() {
        return nom_vulgar;
    }

    public void setnom_vulgar(String nom_vulgar) {
        this.nom_vulgar = nom_vulgar;
    }

    public String getnom_cientifico() {
        return nom_cientifico;
    }

    public void setnom_cientifico(String nom_cientifico) {
        this.nom_cientifico = nom_cientifico;
    }

    public String getfamilia() {
        return familia;
    }

    public void setfamilia(String familia) {
        this.familia = familia;
    }

    public String getextincion() {
        return extincion;
    }

    public void setextincion(String extincion) {
        this.extincion = extincion;
    }

    public String getidentificacion() {
        return identificacion;
    }

    public void setidentificacion(String identificacion) {this.identificacion = identificacion;}

    public String getespecie() {
        return especie;
    }

    public void setespecie(String especie) {
        this.especie = especie;
    }

    public String getgenero() {
        return genero;
    }

    public void setgenero(String genero) {
        this.genero = genero;
    }

    public String getnacimiento() {
        return nacimiento;
    }

    public void setnacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getzoo() {
        return zoo;
    }

    public void setzoo(String zoo) {
        this.zoo = zoo;
    }

    public String getpaisor() {
        return paisor;
    }

    public void setpaisor(String paisor) {
        this.paisor = paisor;
    }

    public String getcontinente() {
        return continente;
    }

    public void setcontinente(String continente) {
        this.continente = continente;
    }
}
