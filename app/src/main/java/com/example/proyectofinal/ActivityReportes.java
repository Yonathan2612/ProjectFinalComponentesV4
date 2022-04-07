package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityReportes extends AppCompatActivity {
    Button btnVerReportes;
    TextView txtFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
    btnVerReportes=(Button) findViewById(R.id.btnVerReporte);
    txtFecha=(TextView) findViewById(R.id.txtDate);


    }
}