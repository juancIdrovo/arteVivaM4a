package ec.tecazuay.artevivam4a;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroEstudiante extends AppCompatActivity {
Button GuardarBtn;
EditText Nombre,Apellido,Cedula,Correo,Direccion,Telefono,Edad;
String nombre,apellido,cedula,correo,direccion,telefono,edad;
AlertDialog.Builder builder;
String reg_url ="http://192.168.1.19:8080/api/estudiantes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_estudiant);
        GuardarBtn = (Button)findViewById(R.id.btnGuardar);
        Nombre  = (EditText)findViewById(R.id.txtnombres);
        Apellido  = (EditText)findViewById(R.id.txtapellidos);
        Cedula  = (EditText)findViewById(R.id.txtcedula);
        Correo  = (EditText)findViewById(R.id.txtcorreo);
        Direccion  = (EditText)findViewById(R.id.txtdireccion);
        Telefono  = (EditText)findViewById(R.id.txttelf);
        builder = new AlertDialog.Builder(RegistroEstudiante.this);
        GuardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = Nombre.getText().toString();
                apellido = Apellido.getText().toString();
                cedula = Cedula.getText().toString();
                correo = Correo.getText().toString();
                direccion = Direccion.getText().toString();
                telefono = Telefono.getText().toString();
                if (nombre.equals("")|| apellido.equals("")||cedula.equals("")||correo.equals("")||direccion.equals("")||telefono.equals("")){
                    builder.setTitle("Algo va mal");
                    builder.setMessage("Por favor, llena todos los campos");
                    displayAlert("input_error");
                }
else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        String message = jsonObject.getString("message");
                                        builder.setTitle("Holi");
                                        builder.setMessage(message);
                                        displayAlert(code);
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                           Map<String,String> params = new HashMap<String, String>();
                           params.put("cedula",cedula);
                            params.put("nombres",nombre);
                            params.put("apellidos",apellido);
                            params.put("correo",correo);
                            params.put("direccion",direccion);
                            params.put("telf",telefono);

                            return params;
                        }
                    };
                    MySingleton.getInstance(RegistroEstudiante.this).addToRequestque(stringRequest);
                }
            }
        });

    }
    private void displayAlert(String code){
builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (code.equals("input_error")){

        } else if (code.equals("reg_success")) {
            finish();
        }
    }
});
AlertDialog alertDialog = builder.create();
alertDialog.show();
    }
}