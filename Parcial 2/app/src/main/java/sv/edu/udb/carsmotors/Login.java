package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void autenticar (View view)
    {
        Intent aut=new Intent(this, MainActivity.class);
        startActivity(aut);
    }
    public void registrar (View view)
    {
        Intent reg=new Intent(this, Registro.class);
        startActivity(reg);
    }
}