package ec.tecazuay.artevivam4a

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegisterActivityKTL : AppCompatActivity() {
    var txtNombre:EditText?=null
    var txtApellido:EditText?=null
    var txtCedula:EditText?=null
    var txtCorreo:EditText?=null
    var txtDireccion:EditText?=null
    var txtTelefono:EditText?=null
    var txtContrasena:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_estudiant)
        txtNombre=findViewById(R.id.txtnombres)
        txtApellido=findViewById(R.id.txtapellidos)
        txtCedula=findViewById(R.id.txtcedula)
        txtCorreo=findViewById(R.id.txtcorreo)
        txtDireccion=findViewById(R.id.txtdireccion)
        txtTelefono=findViewById(R.id.txttelf)
        txtContrasena=findViewById(R.id.txtContrasena)

    }
    fun clickbtnGuardar(view: View){
        val url="http://192.168.1.10/Android_PHP/insertar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Estudiante Registrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@RegisterActivityKTL, LoginActivity::class.java))

            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al registrar Estudiante", Toast.LENGTH_LONG).show()

            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String,String>()
                parametros.put("cedula",txtCedula?.text.toString())
                parametros.put("cedula_estudiante_fk",txtCedula?.text.toString())
                parametros.put("nombres",txtNombre?.text.toString())
                parametros.put("apellidos",txtApellido?.text.toString())
                parametros.put("correo",txtCorreo?.text.toString())
                parametros.put("contrasena",txtContrasena?.text.toString())
                parametros.put("direccion",txtDireccion?.text.toString())
                parametros.put("telf",txtTelefono?.text.toString())
                return parametros

            }
        }
        queue.add(resultadoPost)

    }

}