package ec.tecazuay.artevivam4a;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.tecazuay.artevivam4a.R;
import ec.tecazuay.artevivam4a.modelo.Profesor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaProfesoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProfesorAdapter profesorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ec.tecazuay.artevivam4a.R.layout.activity_lista_profesores);

        // Obtén una instancia del RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProfesores);

        // Configura el RecyclerView y asigna un LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Inicializa el adaptador
        profesorAdapter = new ProfesorAdapter();

        // Asigna el adaptador al RecyclerView
        recyclerView.setAdapter(profesorAdapter);

        // Obtiene la lista de profesores desde el servidor
        obtenerListaProfesores();
    }

    private void obtenerListaProfesores() {
        // URL del servidor Spring Boot
        String baseUrl = "http://192.168.18.26:8080/";
        String endpoint = "api/profesores";

        // Crea una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una interfaz para las solicitudes HTTP
        ApiService apiService = retrofit.create(ApiService.class);

        // Realiza la solicitud GET para obtener la lista de profesores
        Call<List<Profesor>> call = apiService.getProfesores();

        // Maneja la respuesta de la solicitud
        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if (response.isSuccessful()) {
                    // Si la solicitud fue exitosa, actualiza el adaptador con la lista de profesores
                    List<Profesor> listaProfesores = response.body();
                    profesorAdapter.setListaProfesores(listaProfesores);
                    profesorAdapter.notifyDataSetChanged();
                } else {
                    // Si la solicitud no fue exitosa, muestra un mensaje de error
                    Toast.makeText(ListaProfesoresActivity.this, "Error al obtener la lista de profesores", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                // Maneja el fallo de la solicitud
                Toast.makeText(ListaProfesoresActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
