package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroEstudiante extends AppCompatActivity {

    private String urlRegistro = "http://192.168.18.17:8080/api/estudiantes";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_estudiant);

        // Inicializa la RequestQueue
        requestQueue = Volley.newRequestQueue(this);
    }

    public void clickbtnGuardar(View view) {
        // Obtén los valores de los campos
        String cedula = ((TextInputEditText) findViewById(R.id.txtcedula)).getText().toString().trim();
        String nombres = ((TextInputEditText) findViewById(R.id.txtnombres)).getText().toString().trim();
        String apellidos = ((TextInputEditText) findViewById(R.id.txtapellidos)).getText().toString().trim();
        String correo = ((TextInputEditText) findViewById(R.id.txtcorreo)).getText().toString().trim();
        String direccion = ((TextInputEditText) findViewById(R.id.txtdireccion)).getText().toString().trim();
        String telf = ((TextInputEditText) findViewById(R.id.txttelf)).getText().toString().trim();
        String contrasena = ((TextInputEditText) findViewById(R.id.txtContrasena)).getText().toString().trim();
        // También puedes obtener la fecha de nacimiento de manera similar

        // Crea un objeto JSON con los datos
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("cedula", cedula);
            jsonBody.put("nombres", nombres);
            jsonBody.put("apellidos", apellidos);
            jsonBody.put("correo", correo);
            jsonBody.put("direccion", direccion);
            jsonBody.put("telf", telf);
            jsonBody.put("contrasena", contrasena);
            jsonBody.put("cedula_estudiante_fk", cedula);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Crea una solicitud POST para el registro
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlRegistro, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Maneja la respuesta del servidor después de un registro exitoso
                        // Puedes implementar lógica adicional según tus necesidades
                        Log.d("RegistroEstudiante", "Respuesta del servidor: " + response.toString());
                        // Por ejemplo, puedes navegar a la pantalla de inicio de sesión después del registro exitoso
                        startActivity(new Intent(RegistroEstudiante.this, LoginActivity.class));
                        finish(); // Cierra la actividad actual
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Maneja el error en la solicitud
                Log.e("RegistroEstudiante", "Error en la solicitud: " + error.getMessage());
                Toast.makeText(RegistroEstudiante.this, "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Agrega la solicitud a la cola de solicitudes
        requestQueue.add(request);
    }
}

