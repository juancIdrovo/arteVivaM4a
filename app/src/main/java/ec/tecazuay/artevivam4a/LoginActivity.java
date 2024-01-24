package ec.tecazuay.artevivam4a;

import android.os.Bundle;
import android.text.TextUtils;
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

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtEmail.getText().toString())||TextUtils.isEmpty(txtPass.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Aun existen campos vacios, por favor ingrese su correo electrónico y su contraseña",Toast.LENGTH_LONG);

                }else{
                    //logearse
                    login();
                }
            }
        });
    }
    public void login (){
        LoginRequest lore = new LoginRequest();
        lore.setCorreo(txtEmail.getText().toString());
        lore.setContrasenia(txtPass.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(lore);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Correcto", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Fallo", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"ERROR: "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}