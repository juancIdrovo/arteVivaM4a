package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private String urlRegistro = "http://192.168.1.18:8080/api/estudiantes";
    private RequestQueue requestQueue;
    private static final int REQUEST_IMAGE_PICK = 1;
    private ImageView imageView;
    private Uri imageUri; // Declarar como miembro de la clase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_estudiant);

        // Inicializa la RequestQueue
        requestQueue = Volley.newRequestQueue(this);
        imageView = findViewById(R.id.imageView);
        Button btnSeleccionarFoto = findViewById(R.id.btnSeleccionarFoto);
        btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the image picker
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            // Get the selected image Uri
            imageUri = data.getData();

            if (imageUri != null) {
                // Set the image to the ImageView
                imageView.setImageURI(imageUri);
            } else {
                // Handle the case when no image is selected
                Toast.makeText(this, "No se ha seleccionado ninguna imagen.", Toast.LENGTH_SHORT).show();
            }
        }
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
            if (imageUri != null) {
                String rutaImagen = obtenerRutaDesdeUri(imageUri);
                jsonBody.put("foto", rutaImagen);
            } else {
                // Handle the case when imageUri is null
                Log.e("RegistroEstudiante", "imageUri es null en clickbtnGuardar");
            }

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
    private String obtenerRutaDesdeUri(Uri imageUri) {
        if (imageUri == null) {
            // Manejar el caso cuando imageUri es null
            Log.e("RegistroEstudiante", "imageUri es null en obtenerRutaDesdeUri");
            return null;  // O devuelve una cadena vacía o realiza alguna acción específica
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(imageUri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String ruta = cursor.getString(columnIndex);
        cursor.close();
        return ruta;
    }
}

