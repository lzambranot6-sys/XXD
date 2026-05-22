package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class actdatos extends AppCompatActivity {

    TextView tvCedula, tvNombres, tvFechaNacimiento, tvCiudad, tvCorreo, tvTelefono, tvGenero;
    MaterialButton btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actdatos);

        tvCedula            = findViewById(R.id.tvCedula);
        tvNombres           = findViewById(R.id.tvNombres);
        tvFechaNacimiento   = findViewById(R.id.tvFechaNacimiento);
        tvCiudad            = findViewById(R.id.tvCiudad);
        tvCorreo            = findViewById(R.id.tvCorreo);
        tvTelefono          = findViewById(R.id.tvTelefono);
        tvGenero            = findViewById(R.id.tvGenero);
        btnRegresar         = findViewById(R.id.btnRegresar);

        Intent intent = getIntent();
        tvCedula.setText(intent.getStringExtra("cedula"));
        tvNombres.setText(intent.getStringExtra("nombres"));
        tvFechaNacimiento.setText(intent.getStringExtra("fecha"));
        tvCiudad.setText(intent.getStringExtra("ciudad"));
        tvCorreo.setText(intent.getStringExtra("correo"));
        tvTelefono.setText(intent.getStringExtra("telefono"));
        tvGenero.setText(intent.getStringExtra("genero"));

        btnRegresar.setOnClickListener(v -> finish());
    }
}