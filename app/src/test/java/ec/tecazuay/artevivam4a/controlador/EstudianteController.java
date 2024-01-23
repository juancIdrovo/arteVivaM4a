package ec.tecazuay.artevivam4a.controlador;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ec.tecazuay.artevivam4a.modelo.Estudiante;

public class EstudianteController {

    private Estudiante estudianteModel;
    private RequestQueue requestQueue;
    private Context context;

    public EstudianteController(Context context) {
        this.estudianteModel = new Estudiante();
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public Estudiante getEstudianteModel() {
        return estudianteModel;
    }

    public void guardarEstudiante() {
        // Lógica para enviar los datos a la API
        String apiUrl = "http://192.168.18.17:8080/api/estudiantes";

        Gson gson = new Gson();
        String estudianteJson = gson.toJson(estudianteModel);

        try {
            JSONObject estudianteJsonObject = new JSONObject(estudianteJson);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    apiUrl,
                    estudianteJsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Manejar la respuesta del servidor
                            Log.d("EstudianteController", "Estudiante guardado exitosamente");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Manejar errores en la conexión
                            Log.e("EstudianteController", "Error al guardar estudiante: " + error.toString());
                        }
                    }
            );

            // Agregar la solicitud a la cola de solicitudes
            requestQueue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("EstudianteController", "Error al convertir el objeto a JSON");
        }
    }

}


