package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnIniciaSesion, btnRegistrar, btnGuardarRegistro;
    EditText usuario, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //////////Login
        btnIniciaSesion = findViewById(R.id.btnLogIn);
        btnRegistrar = findViewById(R.id.btnRegistro);
        usuario = findViewById(R.id.inputEmail);
        clave = findViewById(R.id.inputPassword);

        /////registro
        //btnGuardarRegistro = findViewById(R.id.btnGuardar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroEstudiante.class));
            }
        });

        btnIniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(usuario.getText().toString()) || TextUtils.isEmpty(clave.getText().toString())) {
                    String message = "Llene todos los campos";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    // Llamar al método para realizar la autenticación
                    loginUser(usuario.getText().toString(), clave.getText().toString());
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo(email);
        loginRequest.setContrasenia(password);

        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getContrasenia().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, PerfilUsuarioActivity.class);
                        intent.putExtra("data", loginResponse);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Correcto", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login Falló", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginActivity", "Error en loginUser: " + t.getMessage(), t);
                Toast.makeText(LoginActivity.this, "ERROR: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


