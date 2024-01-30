package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
                String url = "http://192.168.18.17:8080/api/estudiantes/" + estudiante.getCedula();
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

    // Método para obtener los datos del estudiante actual (puede variar según tu implementación)
    private Estudiante obtenerDatosEstudianteActual() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("estudiante")) {
            return (Estudiante) intent.getSerializableExtra("estudiante");
        } else {
            // Manejar el caso en el que no se proporcionaron los datos del estudiante.
            return new Estudiante(); // Puedes ajustar esto según tu lógica de creación de objetos Estudiante vacíos.
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
        // Crear la cola de solicitudes de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Crear la solicitud PUT
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta exitosa del servidor
                        Toast.makeText(modificarEstudiante.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                        // Puedes agregar más acciones según tu necesidad
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        Toast.makeText(modificarEstudiante.this, "Error al actualizar datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        // Puedes agregar más acciones según tu necesidad
                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(request);
    }
}

