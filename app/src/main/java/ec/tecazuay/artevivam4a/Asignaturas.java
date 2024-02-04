package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

import ec.tecazuay.artevivam4a.modelo.asigna;

public class Asignaturas extends AppCompatActivity {

    Button btnGuardar, btnCancelar;
    List<String> datos = new ArrayList<String>();
    ListView lst;
    RequestQueue qeqeq;
    String url = "http://192.168.18.17:8080/api/matriculas";

    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asignaturas);
        lst=(ListView) findViewById(R.id.lstAsignaturas);
        btnCancelar = findViewById(R.id.btnAceptar);
        qeqeq= Volley.newRequestQueue(this);
        GetApiData();
        userEmail = getIntent().getStringExtra("user_email");
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Asignaturas.this, PerfilUsuarioActivity.class));

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
                            asigna cliente = new asigna();
                            cliente.setNombre(obj.get("nombre").toString());
                            cliente.setDescripcion(obj.get("descripcion").toString());
                            cliente.setCodigoCategorias(obj.getInt("codigoCategorias"));
                            cliente.setCed_profesor_fk(obj.get("ced_profesor_fk").toString());
                            datos.add(cliente.getNombre().toString());
                            datos.add(cliente.getDescripcion().toString());
                            datos.add(Integer.toString(cliente.getCodigoCategorias()));
                            datos.add(cliente.getCed_profesor_fk().toString());

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
