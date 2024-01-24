package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroEstudiante extends AppCompatActivity {
    Button GuardarBtn;
    EditText Nombre, Apellido, Cedula, Correo, Direccion, Telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_estudiant);
        GuardarBtn = findViewById(R.id.btnGuardar);
        Nombre = findViewById(R.id.txtnombres);
        Apellido = findViewById(R.id.txtapellidos);
        Cedula = findViewById(R.id.txtcedula);
        Correo = findViewById(R.id.txtcorreo);
        Direccion = findViewById(R.id.txtdireccion);
        Telefono = findViewById(R.id.txttelf);

        GuardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setCedula(Cedula.getText().toString());
                registerRequest.setNombres(Nombre.getText().toString());
                registerRequest.setApellidos(Apellido.getText().toString());
                registerRequest.setCorreo(Correo.getText().toString());
                registerRequest.setDireccion(Direccion.getText().toString());
                registerRequest.setContrasenia(Telefono.getText().toString());
                registerRequest.setCed_estudiante_fk(Cedula.getText().toString());
                registeruser(registerRequest);
            }
        });
    }

    public void registeruser(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "Exito";
                    Toast.makeText(RegistroEstudiante.this,message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegistroEstudiante.this,LoginActivity.class));
                }else {
                    String message = "Ah ocurrido un error";
                    Toast.makeText(RegistroEstudiante.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegistroEstudiante.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}
