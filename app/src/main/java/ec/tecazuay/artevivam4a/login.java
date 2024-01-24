package ec.tecazuay.artevivam4a;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.tecazuay.artevivam4a.solicitud.LoginRespuesta;
import ec.tecazuay.artevivam4a.solicitud.LoginSolicitud;
import ec.tecazuay.artevivam4a.solicitud.servicioUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {
    // Variables del login activity
    Button btnAceptar;
    TextView txtRecuperar;
    EditText txtEmail, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnAceptar = findViewById(R.id.btnLogIn);
        txtRecuperar = findViewById(R.id.txtRecuperar);
        txtEmail = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);

        // Evento del botón iniciar sesión
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtEmail.getText().toString()) || TextUtils.isEmpty(txtPass.getText().toString())) {
                    Toast.makeText(login.this, "Aun existen campos vacíos. Por favor, ingrese su correo electrónico y su contraseña", Toast.LENGTH_LONG).show();
                } else {
                    // Iniciar sesión
                    login();
                }
            }
        });
    }

    public void login() {
        LoginSolicitud lore = new LoginSolicitud();
        lore.setUsuario(txtEmail.getText().toString());
        lore.setPassword(txtPass.getText().toString());

        // Obtén el servicio de usuario con la nueva URL
        servicioUsuario userService = ClienteApi.getUserService();

        // Realiza la llamada a la API con la nueva URL
        Call<LoginRespuesta> loginResponseCall = userService.userLogin(lore);

        // Maneja la respuesta de manera asíncrona
        loginResponseCall.enqueue(new Callback<LoginRespuesta>() {
            @Override
            public void onResponse(Call<LoginRespuesta> call, Response<LoginRespuesta> response) {
                if (response.isSuccessful()) {
                    // Obtiene el objeto LoginRespuesta de la respuesta
                    LoginRespuesta loginRespuesta = response.body();

                    // Realiza cualquier acción adicional con la respuesta (por ejemplo, mostrar información del usuario)
                    // Ejemplo: Mostrar el nombre del usuario
                    String nombreUsuario = loginRespuesta.getPerPrimerNom();
                    Toast.makeText(login.this, "Login Correcto. ¡Bienvenido, " + nombreUsuario + "!", Toast.LENGTH_LONG).show();
                } else {
                    // Maneja la respuesta de error
                    Toast.makeText(login.this, "Login Falló. Código de error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespuesta> call, Throwable t) {
                // Maneja el fallo de la solicitud
                Toast.makeText(login.this, "ERROR: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}