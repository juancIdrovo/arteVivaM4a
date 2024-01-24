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
import ec.tecazuay.artevivam4a.solicitud.clienteApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {
    //variables del login activity
    Button btnAceptar;
    TextView txtRecuperar;
    EditText txtEmail, txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button myButton = findViewById(R.id.btnLogIn);

        btnAceptar = findViewById(R.id.btnLogIn);
        txtRecuperar = findViewById(R.id.txtRecuperar);
        txtEmail = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);

        //evento de boton iniciar
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtEmail.getText().toString())||TextUtils.isEmpty(txtPass.getText().toString())){
                    Toast.makeText(login.this,"Aun existen campos vacios, por favor ingrese su correo electrónico y su contraseña",Toast.LENGTH_LONG);

                }else{
                    //logearse
                    login();
                }
            }
        });
    }
    public void login (){
        LoginSolicitud lore = new LoginSolicitud();
        lore.setUsuario(txtEmail.getText().toString());
        lore.setPassword(txtPass.getText().toString());
        Call<LoginRespuesta> loginResponseCall = clienteApi.getUserService().userLogin(lore);
        loginResponseCall.enqueue(new Callback<LoginRespuesta>() {
            @Override
            public void onResponse(Call<LoginRespuesta> call, Response<LoginRespuesta> response) {
                if (response.isSuccessful()){
                    Toast.makeText(login.this, "Login Correcto", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(login.this, "Login Fallo", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginRespuesta> call, Throwable t) {
                Toast.makeText(login.this,"ERROR: "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}