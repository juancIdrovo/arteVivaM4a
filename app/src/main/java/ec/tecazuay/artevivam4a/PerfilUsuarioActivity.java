package ec.tecazuay.artevivam4a;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import ec.tecazuay.artevivam4a.modelo.Estudiante;


public class PerfilUsuarioActivity  extends AppCompatActivity {
    private String userName;
    private String userEmail;
    private Uri imageUri;
    Button btnNotas, btnHorario, btnDocente, btnmodificar, btnCurso;
    ImageView opt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuairo);

        userName = getIntent().getStringExtra("user_name");
        userEmail = getIntent().getStringExtra("user_email");
        imageUri = Uri.parse(getIntent().getStringExtra("image_uri"));


        btnNotas = findViewById(R.id.btnNotes);
        btnHorario = findViewById(R.id.btnSchedule1);
        btnDocente = findViewById(R.id.btnDocentes);
        btnCurso = findViewById(R.id.btnCourses);
        opt = findViewById(R.id.btnOptions);
        updateUI();
        opt = findViewById(R.id.btnOptions);
        opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
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

    private void showPopupMenu(View view) {
        // Inflate the popup menu layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_menu_desplegaable, null);

        // Create the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);

        // Find the buttons in the popup menu
        Button modifyProfileButton = popupView.findViewById(R.id.modify_profile_button);
        Button signOutButton = popupView.findViewById(R.id.sign_out_button);

        // Set click listeners for the buttons
        modifyProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Modificar perfil" button click
                startActivity(new Intent(PerfilUsuarioActivity.this, modificarEstudiante.class));
                popupWindow.dismiss();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Cerrar sesión" button click

                popupWindow.dismiss();
            }
        });
        modifyProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(PerfilUsuarioActivity.this, modificarEstudiante.class));

            }

        });

        // Show the popup menu
        popupWindow.showAsDropDown(view);
    }

    private void updateUI() {
        TextView tvName = findViewById(R.id.tvName);
        TextView tvMail = findViewById(R.id.tvMail);
        ImageView ivUserImage = findViewById(R.id.imageViewfoto);

        tvName.setText(userName);
        tvMail.setText(userEmail);

        // Verifica que la URL de la imagen no sea nula
        if (imageUri != null) {
            // Intenta cargar la imagen con Glide
            Glide.with(this)
                    .load(imageUri)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.logosinfondo)  // Imagen de marcador de posición mientras carga
                            .error(R.drawable.luffiperfil)        // Imagen de marcador de posición en caso de error
                    )
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // Manejar fallo de carga de imagen aquí
                            Log.e("PerfilUsuarioActivity", "Error al cargar la imagen con Glide: " + e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // La imagen se cargó correctamente
                            return false;
                        }
                    })
                    .into(ivUserImage);
        } else {
            // URL de la imagen nula o vacía, usa una imagen de marcador de posición
            ivUserImage.setImageResource(R.drawable.luffiperfil);
        }
    }
}

