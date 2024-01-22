package ec.tecazuay.artevivam4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = (Button)findViewById(R.id.btnRegistro);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
startActivity(new Intent(MainActivity.this,RegistroEstudiante.class));
    }
});
    }
    private void setup(){

    }
}