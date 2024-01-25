package ec.tecazuay.artevivam4a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    Button btnAceptar, btnRegistarse;
    TextView txtRecuperar;
    EditText txtEmail, txtPass;
    String correo,pass;
    String url = "http://192.168.1.10/Android_PHP/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnAceptar = findViewById(R.id.btnLogIn);
        btnRegistarse = findViewById(R.id.btnRegistro);
        txtEmail = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);

        btnRegistarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroEstudiante.class));
            }
        });

    }

    public void Login(View view){
        if (txtEmail.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese la cedula", Toast.LENGTH_LONG).show();
        }else if(txtPass.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese la contraseña", Toast.LENGTH_LONG).show();

        }else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Espere un momento...");
            progressDialog.show();

            progressDialog.show();
            correo = txtEmail.getText().toString().trim();
            pass = txtPass.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if (response.equalsIgnoreCase("ingresaste correctamente")) {
                        txtEmail.setText("");
                        txtPass.setText("");
                        startActivity(new Intent(getApplicationContext(), PerfilUsuarioActivity.class));
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("correo",correo);
                    params.put("contrasena",pass);
                    return params;
                }
            } ;
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);
        }
    }
}
