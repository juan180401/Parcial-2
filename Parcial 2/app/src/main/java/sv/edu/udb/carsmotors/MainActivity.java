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
    public void modificar (View view){
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        Intent modi=new Intent(this, ModificarCarro.class);
        modi.putExtra("uslog", ul12);
        startActivity(modi);
    }
    public void eliminarauto (View view){
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        Intent eli=new Intent(this, EliminarCarro.class);
        eli.putExtra("uslog", ul12);
        startActivity(eli);
    }
    public void modificarusua (View view){
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        Intent modi=new Intent(this, ModificarUsuario.class);
        modi.putExtra("uslog", ul12);
        startActivity(modi);

    }
    public void eliminarusua (View view){
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        Intent elius=new Intent(this, EliminarUsuario.class);
        elius.putExtra("uslog", ul12);
        startActivity(elius);
    }
    public void terminar (View view){
        finish();

    }

}