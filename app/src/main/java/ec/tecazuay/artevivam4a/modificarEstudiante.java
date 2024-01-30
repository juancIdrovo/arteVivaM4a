package ec.tecazuay.artevivam4a;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

import ec.tecazuay.artevivam4a.modelo.Estudiante;

public class modificarEstudiante extends AppCompatActivity {
    EditText txtNombres, txtApellidos, txtCorreo, txtDireccion, txtTelefono, txtFoto, txtFechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_datos_estudiante);

        // Inicializar los campos de texto
        txtNombres = findViewById(R.id.txtnombres);
        txtApellidos = findViewById(R.id.txtapellidos);
        txtCorreo = findViewById(R.id.txtcorreo);
        txtDireccion = findViewById(R.id.txtdireccion);
        txtTelefono = findViewById(R.id.txttelf);
        txtFoto = findViewById(R.id.txtfoto);

        // Obtener datos del estudiante desde la actividad anterior o la API
        Estudiante estudiante = obtenerDatosEstudianteActual();
        mostrarDatosEstudiante(estudiante);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener datos modificados
                String nuevosNombres = txtNombres.getText().toString();
                String nuevosApellidos = txtApellidos.getText().toString();
                String nuevoCorreo = txtCorreo.getText().toString();
                String nuevaDireccion = txtDireccion.getText().toString();
                String nuevoTelefono = txtTelefono.getText().toString();
                String nuevaFoto = txtFoto.getText().toString();

                // Mostrar los datos que se están enviando antes de la solicitud PUT
                Log.d("Depuración", "Datos a enviar al servidor: " +
                        "Nombres: " + nuevosNombres +
                        ", Apellidos: " + nuevosApellidos +
                        ", Correo: " + nuevoCorreo +
                        ", Dirección: " + nuevaDireccion +
                        ", Teléfono: " + nuevoTelefono +
                        ", Foto: " + nuevaFoto);

                // Crear objeto JSON con los datos modificados
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("nombres", nuevosNombres);
                    jsonBody.put("apellidos", nuevosApellidos);
                    jsonBody.put("correo", nuevoCorreo);
                    jsonBody.put("direccion", nuevaDireccion);
                    jsonBody.put("telefono", nuevoTelefono);
                    jsonBody.put("foto", nuevaFoto);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Enviar solicitud PUT al servidor
                String url = "http://192.168.40.85:8080/api/estudiantes/" + estudiante.getCedula();
                enviarSolicitudPut(url, jsonBody);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agregar el código para ir a la actividad "perfil_usuario"
                Intent intent = new Intent(modificarEstudiante.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para obtener los datos del estudiante actual
    private Estudiante obtenerDatosEstudianteActual() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("estudiante")) {
            Estudiante estudiante = (Estudiante) intent.getSerializableExtra("estudiante");
            Log.d("Depuración", "Estudiante obtenido correctamente: " + estudiante.toString());
            return estudiante;
        } else {
            Log.d("Depuración", "No se proporcionaron datos del estudiante. Creando un nuevo objeto Estudiante vacío.");
            return new Estudiante();
        }
    }

    // Método para mostrar los datos del estudiante en los campos de texto
    private void mostrarDatosEstudiante(Estudiante estudiante) {
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtCorreo.setText(estudiante.getCorreo());
        txtDireccion.setText(estudiante.getDireccion());
        txtTelefono.setText(estudiante.getTelf());
        txtFoto.setText(estudiante.getFoto());
    }

    // Método para enviar una solicitud PUT al servidor
    private void enviarSolicitudPut(String url, JSONObject jsonBody) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(modificarEstudiante.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(modificarEstudiante.this, "Error al actualizar datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

      public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            String selectedDate = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, day);
            ((TextInputEditText) getActivity().findViewById(R.id.txtFechaNac)).setText(selectedDate);
        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new RegistroEstudiante.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}


