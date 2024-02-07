package ec.tecazuay.artevivam4a;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Profesor;

public class Docente extends AppCompatActivity {
    List<Profesor> datos = new ArrayList<>(); // Cambio aquí
    RequestQueue queue;
    RecyclerView recyclerView;
    DocenteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        queue = Volley.newRequestQueue(this);

        ImageView imageBuscar = findViewById(R.id.imageBuscar);
        imageBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el texto ingresado en txtbuscar
                TextInputEditText txtBuscar = findViewById(R.id.txtbuscar);
                String codigoProfesor = txtBuscar.getText().toString();

                // Verificar si el texto está vacío antes de realizar la búsqueda
                if (!codigoProfesor.isEmpty()) {
                    // Realizar la búsqueda del docente
                    MostrarDetallesProfesor(codigoProfesor);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un código de profesor válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewMatriculas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DocenteAdapter(datos);
        recyclerView.setAdapter(adapter);
    }

    private void MostrarDetallesProfesor(String codigoProfesor) {
        String urlWithCode = "http://192.168.18.17:8080/api/profesores/" + codigoProfesor;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlWithCode, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Profesor profesor = new Gson().fromJson(response.toString(), Profesor.class);

                    // Añadir el objeto Profesor a la lista datos
                    datos.add(profesor);

                    // Notificar al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al analizar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("VolleyError", error.toString());
            }
        });

        queue.add(request);
    }
}


