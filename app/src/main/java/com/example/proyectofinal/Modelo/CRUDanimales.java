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
    private String nom_vulgar,nom_cientifico,txtfamilia,txtextincion,txtidentificacion,txtespecie,txtgenero,txtnacimiento,txtzoo,txtpaisor,txtcontinente;
    public CRUDanimales(String nom_vulgar, String nom_cientifico, String txtfamilia, String txtextincion, String txtidentificacion, String txtespecie, String txtgenero, String txtnacimiento, String txtzoo, String txtpaisor, String txtcontinente)
    {
        this.nom_vulgar = nom_vulgar;
        this.nom_cientifico = nom_cientifico;
        this.txtfamilia = txtfamilia;
        this.txtextincion = txtextincion;
        this.txtidentificacion = txtidentificacion;
        this.txtespecie = txtespecie;
        this.txtgenero = txtgenero;
        this.txtnacimiento = txtnacimiento;
        this.txtzoo = txtzoo;
        this.txtpaisor = txtpaisor;
        this.txtcontinente = txtcontinente;
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
                "txtfamilia: "+this.gettxtfamilia()+"\n" +
                "txtextincion: "+this.gettxtextincion()+"\n"+
        "txtidentificacion:"+this.gettxtidentificacion()+"\n" +
                "txtespecie: "+this.gettxtespecie()+"\n" +
                "txtgenero: "+this.gettxtgenero()+"\n" +
                "txtnacimiento: "+this.gettxtnacimiento()+"\n"+
        "txtzoo:"+this.gettxtzoo()+"\n" +
                "txtpaisor: "+this.gettxtpaisor()+"\n" +
                "txtcontinente: "+this.gettxtcontinente()+"\n" ;
    }

    public String getnom_vulgar() {
        return nom_vulgar;
    }

    public void setnom_vulgar(String nombre) {
        this.nom_vulgar = nombre;
    }

    public String getnom_cientifico() {
        return nom_cientifico;
    }

    public void setnom_cientifico(String correo) {
        this.nom_cientifico = correo;
    }

    public String gettxtfamilia() {
        return txtfamilia;
    }

    public void settxtfamilia(String id) {
        this.txtfamilia = id;
    }

    public String gettxtextincion() {
        return txtextincion;
    }

    public void settxtextincion(String password) {
        this.txtextincion = password;
    }

    public String gettxtidentificacion() {
        return txtidentificacion;
    }

    public void settxtidentificacion(String tel) {this.txtidentificacion = tel;}

    public String gettxtespecie() {
        return txtespecie;
    }

    public void settxtespecie(String tel) {
        this.txtespecie = tel;
    }

    public String gettxtgenero() {
        return txtgenero;
    }

    public void settxtgenero(String tel) {
        this.txtgenero = tel;
    }
    public String gettxtnacimiento() {
        return txtnacimiento;
    }

    public void settxtnacimiento(String tel) {
        this.txtnacimiento = tel;
    }
    public String gettxtzoo() {
        return txtzoo;
    }

    public void settxtzoo(String tel) {
        this.txtzoo = tel;
    }

    public String gettxtpaisor() {
        return txtpaisor;
    }

    public void settxtpaisor(String tel) {
        this.txtpaisor = tel;
    }

    public String gettxtcontinente() {
        return txtcontinente;
    }

    public void settxtcontinente(String tel) {
        this.txtcontinente = tel;
    }
}
