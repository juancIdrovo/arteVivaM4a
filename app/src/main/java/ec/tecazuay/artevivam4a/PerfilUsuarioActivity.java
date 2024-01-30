package ec.tecazuay.artevivam4a;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;

import ec.tecazuay.artevivam4a.modelo.Estudiante;


public class PerfilUsuarioActivity  extends AppCompatActivity {
    private String userName;
    private String userEmail;
    private String userImageUrl;
    Button btnNotas, btnHorario, btnDocente, btnCurso;
    ImageView opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuairo);

        userName = getIntent().getStringExtra("user_name");
        userEmail = getIntent().getStringExtra("user_email");
        userImageUrl = getIntent().getStringExtra("foto");

        btnNotas = findViewById(R.id.btnNotes);
        btnHorario = findViewById(R.id.btnSchedule1);
        btnDocente = findViewById(R.id.btnDocentes);
        btnCurso = findViewById(R.id.btnCourses);
        opt = findViewById(R.id.btnOptions);
        updateUI();
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

                startActivity(new Intent(PerfilUsuarioActivity.this, Docente.class));

            }
        });

        btnCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PerfilUsuarioActivity.this, Cursos.class));

            }
        });

    }

    private void updateUI() {
        TextView tvName = findViewById(R.id.tvName);
        TextView tvMail = findViewById(R.id.tvMail);
        ImageView ivUserImage = findViewById(R.id.imageViewfoto);

        tvName.setText(userName);
        tvMail.setText(userEmail);

        // Verifica que la URL de la imagen no sea nula
        if (userImageUrl != null && !userImageUrl.isEmpty()) {
            // Intenta cargar la imagen con Picasso
            try {
                // Convertir el path local a una URL completa
                Uri imageUri = Uri.fromFile(new File(userImageUrl));
                String imageUrl = imageUri.toString();

                // Utilizar Picasso para cargar la imagen en el ImageView
                Picasso.get().load(imageUrl).into(ivUserImage);
            } catch (Exception e) {
                // Manejar cualquier excepción que pueda ocurrir al intentar cargar la imagen
                e.printStackTrace();
                // Usa una imagen de marcador de posición si hay un problema con la URL
                ivUserImage.setImageResource(R.drawable.profesora64);
            }
        } else {
            // URL de la imagen nula o vacía, usa una imagen de marcador de posición
            ivUserImage.setImageResource(R.drawable.profesora64);
        }
    }

}


