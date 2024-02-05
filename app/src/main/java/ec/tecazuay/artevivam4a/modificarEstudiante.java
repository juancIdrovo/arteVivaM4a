package ec.tecazuay.artevivam4a;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

import ec.tecazuay.artevivam4a.modelo.Estudiante;

public class modificarEstudiante extends AppCompatActivity {
    EditText txtNombres, txtApellidos, txtCorreo, txtDireccion, txtTelefono, txtcedula, txtFecha;
    Button btnGuarda, btnCancelar;
    Button btnSeleccionarFoto;
    private static final int REQUEST_IMAGE_PICK = 1;
    private String userName, apellidos, direccion, contrasena, telefono, fecha_nac;
    private String userEmail;
    private Uri imageUri;
    private String cedula;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_datos_estudiante);

        userName = getIntent().getStringExtra("user_name");
        cedula = getIntent().getStringExtra("cedula");
        apellidos = getIntent().getStringExtra("apellidos");
        direccion = getIntent().getStringExtra("direccion");
        telefono = getIntent().getStringExtra("telefono");
        userEmail = getIntent().getStringExtra("user_email");
        imageUri = Uri.parse(getIntent().getStringExtra("image_uri"));
        contrasena  = getIntent().getStringExtra("contrasena");
        fecha_nac  = getIntent().getStringExtra("fecha_nac");


        txtNombres = findViewById(R.id.txtnombres);
        txtApellidos = findViewById(R.id.txtapellidos);
        txtCorreo = findViewById(R.id.txtcorreo);
        txtDireccion = findViewById(R.id.txtdireccion);
        txtTelefono = findViewById(R.id.txttelf);
        txtcedula = findViewById(R.id.txtcedula);
        txtFecha = findViewById(R.id.txtFechaNac);
        btnGuarda = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnSeleccionarFoto = findViewById(R.id.btnfotito);
        updateUI();
        Intent intent = getIntent();

        Estudiante estudiante = obtenerDatosEstudianteActual();
        btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener datos modificados
                String nuevosNombres = txtNombres.getText().toString();
                String nuevosApellidos = txtApellidos.getText().toString();
                String nuevoCorreo = txtCorreo.getText().toString();
                String nuevaDireccion = txtDireccion.getText().toString();
                String nuevoTelefono = txtTelefono.getText().toString();


                // Mostrar los datos que se están enviando antes de la solicitud PUT
                Log.d("Depuración", "Datos a enviar al servidor: " +
                        "Nombres: " + nuevosNombres +
                        ", Apellidos: " + nuevosApellidos +
                        ", Correo: " + nuevoCorreo +
                        ", Dirección: " + nuevaDireccion +
                        ", Teléfono: " + nuevoTelefono);

                // Crear objeto JSON con los datos modificados
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("nombres", nuevosNombres);
                    jsonBody.put("apellidos", nuevosApellidos);
                    jsonBody.put("correo", nuevoCorreo);
                    jsonBody.put("direccion", nuevaDireccion);
                    jsonBody.put("telefono", nuevoTelefono);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Enviar solicitud PUT al servidor

                String url = "http://192.168.137.141:8080/api/estudiantes/" + estudiante.getCedula();
                 enviarSolicitudPut(url, jsonBody);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agregar el código para ir a la actividad "perfil_usuario"
                Intent intent = new Intent(modificarEstudiante.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void enviarSolicitudPut(String url, JSONObject jsonBody) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(modificarEstudiante.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(modificarEstudiante.this, "Error al actualizar datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            String selectedDate = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, day);
            ((TextInputEditText) getActivity().findViewById(R.id.txtFechaNac)).setText(selectedDate);
        }
    }
    private void updateUI() {
        TextView tvName = findViewById(R.id.txtnombres);
        TextView tvMail = findViewById(R.id.txtcorreo);
        TextView tvapellidos = findViewById(R.id.txtapellidos);
        TextView tvcontrasenia = findViewById(R.id.txtcontrasena);
        TextView tvdireccion = findViewById(R.id.txtdireccion);
        TextView tvcedula = findViewById(R.id.txtcedula);
        TextView tvTelefono = findViewById(R.id.txttelf);
        TextView tvFecha = findViewById(R.id.txtFechaNac);

        ImageView ivUserImage = findViewById(R.id.fotoPerfil);

        tvName.setText(userName);
        tvMail.setText(userEmail);
        tvapellidos.setText(apellidos);
        tvdireccion.setText(direccion);
        tvcedula.setText(cedula);
        tvTelefono.setText(telefono);
        tvcontrasenia.setText(contrasena);
        tvFecha.setText(fecha_nac);


        // Verifica que la URL de la imagen no sea nula
        if (imageUri != null) {
            // Intenta cargar la imagen con Glide
            Glide.with(this)
                    .load(imageUri)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.logosinfondo)  // Imagen de marcador de posición mientras carga
                            .error(R.drawable.tati)        // Imagen de marcador de posición en caso de error
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
    private Estudiante obtenerDatosEstudianteActual() {
        Intent intent = getIntent();
        try {
            if (intent != null && intent.hasExtra("estudiante")) {
                return (Estudiante) intent.getSerializableExtra("estudiante");
            } else {
                // Manejar el caso en el que no se proporcionaron los datos del estudiante.
                return new Estudiante(); // Puedes ajustar esto según tu lógica de creación de objetos Estudiante vacíos.
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Estudiante();
        }
    }
}


