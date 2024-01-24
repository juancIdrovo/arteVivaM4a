package ec.tecazuay.artevivam4a;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnAceptar, btnRegistarse;
    TextView txtRecuperar;
    EditText txtEmail, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnAceptar = findViewById(R.id.btnLogIn);
        btnRegistarse = findViewById(R.id.btnRegistro);
        txtRecuperar = findViewById(R.id.txtRecuperar);
        txtEmail = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);

        btnRegistarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroEstudiante.class));
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtEmail.getText().toString()) || TextUtils.isEmpty(txtPass.getText().toString())) {
                    String message = "Llene todos los campos";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    // Llamar al método para realizar la autenticación
                    loginUser(txtEmail.getText().toString(), txtPass.getText().toString());
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
                    // La respuesta es exitosa, obtenemos el objeto LoginResponse
                    LoginResponse loginResponse = response.body();

                    // Verificamos si el objeto no es nulo y la contraseña coincide
                    if (loginResponse != null && loginResponse.getContrasenia().equals(password)) {
                        // Contraseña válida, procede con la lógica de inicio de sesión
                        Intent intent = new Intent(LoginActivity.this, PerfilUsuarioActivity.class);
                        intent.putExtra("data", loginResponse);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Correcto", Toast.LENGTH_LONG).show();
                    } else {
                        // Contraseña no coincide, muestra un mensaje de error
                        Toast.makeText(LoginActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Respuesta no exitosa, mostrar mensaje de error
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
