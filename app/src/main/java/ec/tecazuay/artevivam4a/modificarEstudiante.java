package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class modificarEstudiante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_datos_estudiante);  // Reemplaza con el nombre de tu layout XML

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agrega el código para ir a la actividad "perfil_usuario"
                Intent intent = new Intent(modificarEstudiante.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
