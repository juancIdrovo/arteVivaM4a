package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Horarioss;

public class Horarios extends AppCompatActivity {

    Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes, btnCancelar;
    private String cedula;
    List<String> datos = new ArrayList<String>();
    String url;
    ListView lst;
    RequestQueue qeqeq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        cedula = getIntent().getStringExtra("cedula");

        btnCancelar = findViewById(R.id.btnAceptar);
        btnLunes = findViewById(R.id.btnLunes);
        btnMartes = findViewById(R.id.btnMartes);
        btnMiercoles = findViewById(R.id.btnMiercoles);
        btnJueves = findViewById(R.id.btnJueves);
        btnViernes = findViewById(R.id.btmViernes);
        lst=(ListView)findViewById(R.id.recyclerViewHorarios);
        qeqeq= Volley.newRequestQueue(this);
        GetApiData();
        url= "http://192.168.40.85:8080/api/matriculas/horario/"+cedula;


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Horarios.this, PerfilUsuarioActivity.class));

            }
        });

    }
    private void GetApiData(){
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length()>0){
                    for (int i=0; i< response.length(); i++){
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            Horarioss horario = new Horarioss();

                            horario.setDia(obj.get("dia").toString());
                            horario.setHora_Inicio(obj.get("hora_Inicio").toString());
                            horario.setHora_fin(obj.get("hora_fin").toString());
                            datos.add(horario.getDia().toString());
                            datos.add(horario.getHora_Inicio().toString());
                            datos.add(horario.getHora_fin().toString());

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datos);
                            lst.setAdapter(adapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int x = 0;
            }
        });
        qeqeq.add(request);
    }

}
