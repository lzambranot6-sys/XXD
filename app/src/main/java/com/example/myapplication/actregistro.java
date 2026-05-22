package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;

public class actregistro extends AppCompatActivity {

    // Campos del formulario
    TextInputLayout tilCedula, tilNombres, tilFechaNacimiento, tilCiudad, tilCorreo, tilTelefono;
    TextInputEditText etCedula, etNombres, etFechaNacimiento, etCiudad, etCorreo, etTelefono;
    RadioGroup radioGroupGenero;
    MaterialButton btnLimpiar, btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actregistro);

        // Enlazar vistas
        tilCedula           = findViewById(R.id.tilCedula);
        tilNombres          = findViewById(R.id.tilNombres);
        tilFechaNacimiento  = findViewById(R.id.tilFechaNacimiento);
        tilCiudad           = findViewById(R.id.tilCiudad);
        tilCorreo           = findViewById(R.id.tilCorreo);
        tilTelefono         = findViewById(R.id.tilTelefono);

        etCedula            = findViewById(R.id.etCedula);
        etNombres           = findViewById(R.id.etNombres);
        etFechaNacimiento   = findViewById(R.id.etFechaNacimiento);
        etCiudad            = findViewById(R.id.etCiudad);
        etCorreo            = findViewById(R.id.etCorreo);
        etTelefono          = findViewById(R.id.etTelefono);

        radioGroupGenero    = findViewById(R.id.radioGroupGenero);
        btnLimpiar          = findViewById(R.id.btnLimpiar);
        btnEnviar           = findViewById(R.id.btnEnviar);

        // DatePicker al tocar el campo de fecha
        etFechaNacimiento.setOnClickListener(v -> abrirDatePicker());

        // Botón Limpiar
        btnLimpiar.setOnClickListener(v -> limpiarFormulario());

        // Botón Enviar
        btnEnviar.setOnClickListener(v -> {
            if (validarFormulario()) {
                enviarDatos();
            }
        });
    }

    private void abrirDatePicker() {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes  = calendario.get(Calendar.MONTH);
        int dia  = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    // Formato: DD/MM/YYYY
                    String fecha = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    etFechaNacimiento.setText(fecha);
                }, anio, mes, dia);

        dialog.show();
    }

    private boolean validarFormulario() {
        boolean valido = true;

        // Cédula: requerida, exactamente 10 dígitos
        String cedula = etCedula.getText().toString().trim();
        if (cedula.isEmpty()) {
            tilCedula.setError("La cédula es requerida");
            valido = false;
        } else if (cedula.length() != 10) {
            tilCedula.setError("La cédula debe tener 10 dígitos");
            valido = false;
        } else {
            tilCedula.setError(null);
        }

        // Nombres: requerido
        String nombres = etNombres.getText().toString().trim();
        if (nombres.isEmpty()) {
            tilNombres.setError("El nombre es requerido");
            valido = false;
        } else {
            tilNombres.setError(null);
        }

        // Fecha: requerida
        String fecha = etFechaNacimiento.getText().toString().trim();
        if (fecha.isEmpty()) {
            tilFechaNacimiento.setError("Selecciona una fecha");
            valido = false;
        } else {
            tilFechaNacimiento.setError(null);
        }

        // Ciudad: requerida
        String ciudad = etCiudad.getText().toString().trim();
        if (ciudad.isEmpty()) {
            tilCiudad.setError("La ciudad es requerida");
            valido = false;
        } else {
            tilCiudad.setError(null);
        }

        // Correo: requerido y formato válido
        String correo = etCorreo.getText().toString().trim();
        if (correo.isEmpty()) {
            tilCorreo.setError("El correo es requerido");
            valido = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Formato de correo inválido");
            valido = false;
        } else {
            tilCorreo.setError(null);
        }

        // Teléfono: requerido
        String telefono = etTelefono.getText().toString().trim();
        if (telefono.isEmpty()) {
            tilTelefono.setError("El teléfono es requerido");
            valido = false;
        } else {
            tilTelefono.setError(null);
        }

        return valido;
    }

    private void enviarDatos() {
        // Obtener género seleccionado
        String genero;
        int radioSeleccionado = radioGroupGenero.getCheckedRadioButtonId();
        if (radioSeleccionado == R.id.rbMasculino) {
            genero = "Masculino";
        } else if (radioSeleccionado == R.id.rbFemenino) {
            genero = "Femenino";
        } else {
            genero = "Otro";
        }

        // Pasar datos a la segunda Activity via Intent
        Intent intent = new Intent(this, actdatos.class);
        intent.putExtra("cedula",   etCedula.getText().toString().trim());
        intent.putExtra("nombres",  etNombres.getText().toString().trim());
        intent.putExtra("fecha",    etFechaNacimiento.getText().toString().trim());
        intent.putExtra("ciudad",   etCiudad.getText().toString().trim());
        intent.putExtra("correo",   etCorreo.getText().toString().trim());
        intent.putExtra("telefono", etTelefono.getText().toString().trim());
        intent.putExtra("genero",   genero);
        startActivity(intent);
    }

    private void limpiarFormulario() {
        etCedula.setText("");
        etNombres.setText("");
        etFechaNacimiento.setText("");
        etCiudad.setText("");
        etCorreo.setText("");
        etTelefono.setText("");
        radioGroupGenero.check(R.id.rbMasculino);

        // Limpiar errores
        tilCedula.setError(null);
        tilNombres.setError(null);
        tilFechaNacimiento.setError(null);
        tilCiudad.setError(null);
        tilCorreo.setError(null);
        tilTelefono.setError(null);
    }
}