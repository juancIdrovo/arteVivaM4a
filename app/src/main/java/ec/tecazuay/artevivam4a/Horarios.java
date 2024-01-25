package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Horarios extends AppCompatActivity {

    Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);

        btnCancelar = findViewById(R.id.btnAceptar);
        btnLunes = findViewById(R.id.btnLunes);
        btnMartes = findViewById(R.id.btnMartes);
        btnMiercoles = findViewById(R.id.btnMiercoles);
        btnJueves = findViewById(R.id.btnJueves);
        btnViernes = findViewById(R.id.btmViernes);



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Horarios.this, PerfilUsuarioActivity.class));

            }
        });
    }

}
