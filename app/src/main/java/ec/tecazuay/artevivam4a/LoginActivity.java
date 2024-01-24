package ec.tecazuay.artevivam4a;

import android.content.Intent;
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
                    ;
                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setCorreo(txtEmail.getText().toString());
                    loginRequest.setContrasenia(txtPass.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });
    }
    public void loginUser(LoginRequest loginRequest){
        LoginRequest lore = new LoginRequest();
        lore.setCorreo(txtEmail.getText().toString());
        lore.setContrasenia(txtPass.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(lore);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this,RegistroEstudiante.class).putExtra("data",loginResponse));
                    finish();
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