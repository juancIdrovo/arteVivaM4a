package ec.tecazuay.artevivam4a

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class PerfilUsuarioActivityKTL : AppCompatActivity() {

    var txtNombre: EditText?=null
    var txtMail: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_usuairo)
        txtNombre=findViewById(R.id.tvName)
        txtMail=findViewById(R.id.tvMail)
        val cedula=intent.getStringExtra("cedula").toString()
        val queue=Volley.newRequestQueue(this)

        val url="http://192.168.137.1/Android_PHP/registro.php?cedula=$cedula"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
               txtNombre?.setText(response.getString("nombres"))
                txtMail?.setText(response.getString("correo"))
            }, { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsonObjectRequest)

    }
}