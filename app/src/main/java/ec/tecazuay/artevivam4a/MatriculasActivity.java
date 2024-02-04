package ec.tecazuay.artevivam4a;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Matricula;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatriculasActivity extends AppCompatActivity {

    private static final String TAG = "MatriculasActivity"; // Etiqueta para registros

    private RecyclerView recyclerViewMatriculas;
    private MatriculasAdapter matriculasAdapter;
    private String cedula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriculas);
        cedula=getIntent().getStringExtra("cedula");
        // Inicializar vistas
        recyclerViewMatriculas = findViewById(R.id.recyclerViewMatriculas);
        Button btnBackToMenu = findViewById(R.id.btnBackToMenu);

        // Configurar el RecyclerView y su adaptador
        matriculasAdapter = new MatriculasAdapter(new ArrayList<>());
        recyclerViewMatriculas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMatriculas.setAdapter(matriculasAdapter);

        // Manejar el clic del botón de regreso al menú principal
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Obtener las matrículas del estudiante
        new ObtenerMatriculasTask().execute(cedula); // Reemplaza con el ID del estudiante
    }

    private class ObtenerMatriculasTask extends AsyncTask<String, Void, List<Matricula>> {

        @Override
        protected List<Matricula> doInBackground(String... params) {
            String idEstudiante = params[0];

            try {
                // Configurar Retrofit para tu API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.18.254:8080/") // Coloca la URL base de tu API
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Crear un servicio de Retrofit
                ApiService service = retrofit.create(ApiService.class);

                // Realizar la llamada síncrona a la API
                Call<List<Matricula>> call = service.getMatriculas(idEstudiante);

                // Imprimir la URL completa después de realizar la llamada
                Log.d(TAG, "URL de la API: " + call.request().url());

                Response<List<Matricula>> response = call.execute();
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
        protected void onPostExecute(List<Matricula> matriculas) {
            if (matriculas != null) {
                // Actualizar el adaptador con las matrículas obtenidas
                actualizarMatriculasEnAdapter(matriculas);
            } else {
                // Mostrar un mensaje de error en la interfaz de usuario si es necesario
                Log.w(TAG, "No se pudieron obtener las matrículas");
            }
        }
    }


    private void actualizarMatriculasEnAdapter(List<Matricula> matriculas) {
        matriculasAdapter.setMatriculas(matriculas);
        matriculasAdapter.notifyDataSetChanged();
    }
}


