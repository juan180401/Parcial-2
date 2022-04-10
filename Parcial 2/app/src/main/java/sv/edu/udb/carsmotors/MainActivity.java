package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView) findViewById(R.id.Usulog1);
        us.setText(ul12);
    }
    public void Agregar (View view){
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        Intent agre=new Intent(this, AgregarCarro.class);
        agre.putExtra("uslog", ul12);
        startActivity(agre);
    }
    public void AgregarUsuario (View view){
        Intent agreus=new Intent(this, Registro.class);
        startActivity(agreus);
    }
}