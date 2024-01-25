package ec.tecazuay.artevivam4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilUsuarioActivity  extends AppCompatActivity {

    Button btnNotas, btnHorario, btnDocente, btnCurso;
    ImageView opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuairo);

        btnNotas = findViewById(R.id.btnNotes);
        btnHorario = findViewById(R.id.btnSchedule1);
        btnDocente = findViewById(R.id.btnDocentes);
        btnCurso = findViewById(R.id.btnCourses);
        opt = findViewById(R.id.btnOptions);

        btnNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PerfilUsuarioActivity.this, Notas.class));

            }
        });

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PerfilUsuarioActivity.this, Horarios.class));

            }
        });

        btnDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PerfilUsuarioActivity.this, Docentes.class));

            }
        });

        btnCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PerfilUsuarioActivity.this, PerfilUsuarioActivity.class));

            }
        });

    }


}