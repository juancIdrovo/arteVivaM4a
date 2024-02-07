package ec.tecazuay.artevivam4a;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Horarioss;
import ec.tecazuay.artevivam4a.modelo.Matricula;
import ec.tecazuay.artevivam4a.modelo.Profesor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Horarios extends AppCompatActivity {
    private static final String TAG = "Horarios";
    Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes, btnCancelar;
    private String cedula;
    List<String> datos = new ArrayList<String>();
    String url;
    private RecyclerView recyclerViewHorarios;
    private HorariosAdapter horariosAdapter;
    RequestQueue qeqeq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        cedula = getIntent().getStringExtra("cedula");

        btnCancelar = findViewById(R.id.btnAceptar);


        recyclerViewHorarios=findViewById(R.id.recyclerViewHorarios);
        horariosAdapter=new HorariosAdapter(new ArrayList<>());
        recyclerViewHorarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHorarios.setAdapter(horariosAdapter);



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
        new ObtenerHorariosTask().execute(cedula);
    }

    private class ObtenerHorariosTask extends AsyncTask<String, Void, List<Horarioss>> {

        @Override
        protected List<Horarioss> doInBackground(String... params) {
            String cedula = params[0]; // Obtener la cédula del estudiante

            try {
                // Configurar Retrofit para tu API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.18.17:8080/") // Coloca la URL base de tu API
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Crear un servicio de Retrofit
                ApiService service = retrofit.create(ApiService.class);

                // Realizar la llamada síncrona a la API
                Call<List<Horarioss>> call = service.getHorarios(cedula);

                // Imprimir la URL completa después de realizar la llamada
                Log.d(TAG, "URL de la API: " + call.request().url());

                Response<List<Horarioss>> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    // Manejar el error de la API
                    String errorBody = response.errorBody().string();
                    Log.e(TAG, "Error en la llamada a la API: " + errorBody);
                }
            } catch (IOException e) {
                // Manejar excepciones, por ejemplo, falta de conexión a Internet
                Log.e(TAG, "Excepción al realizar la llamada a la API", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Horarioss> horarios) {
            if (horarios != null) {
                // Actualizar el adaptador con los horarios obtenidos
                actualizarHorariosEnAdapter(horarios);
            } else {
                // Mostrar un mensaje de error en la interfaz de usuario si es necesario
                Log.w(TAG, "No se pudieron obtener los horarios");
            }
        }
    }



    private void actualizarHorariosEnAdapter(List<Horarioss> horarios) {
        horariosAdapter.setHorarios(horarios);
        horariosAdapter.notifyDataSetChanged();
    }
}

