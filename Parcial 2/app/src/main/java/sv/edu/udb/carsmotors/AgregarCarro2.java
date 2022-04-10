package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarCarro2 extends AppCompatActivity {
    EditText mod,vin1,chas,motor1,uri,descrip1,asie,year,capasie,pre;
    TextView us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro2);
        mod=(EditText) findViewById(R.id.modelo);
        vin1=(EditText) findViewById(R.id.vin);
        chas=(EditText) findViewById(R.id.chasis);
        motor1=(EditText) findViewById(R.id.motor);
        asie=(EditText) findViewById(R.id.asientos);
        year=(EditText) findViewById(R.id.año);
        capasie=(EditText) findViewById(R.id.capaasien);
        pre=(EditText) findViewById(R.id.precio);
        uri=(EditText) findViewById(R.id.urimg);
        descrip1=(EditText) findViewById(R.id.descr);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView) findViewById(R.id.uslog);
        us.setText(ul12);


    }
    public void guardarauto (View view){
        BaseDeDatos sav = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= sav.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String idrefe1 = bundle.getString("idref1");
        String mod1 = mod.getText().toString();
        String vin2 = vin1.getText().toString();
        String chas2 = chas.getText().toString();
        String motor2 = motor1.getText().toString();
        String asie2= asie.getText().toString();
        String year2=year.getText().toString();
        String capasie2=capasie.getText().toString();
        String pre2=pre.getText().toString();
        String uri2 = uri.getText().toString();
        String descrip2 = descrip1.getText().toString();
        ContentValues savcar = new ContentValues();
        savcar.put("modelo", mod1);
        savcar.put("numero_vin", vin2);
        savcar.put("numero_chasis", chas2);
        savcar.put("numero_motor", motor2);
        savcar.put("numero_asientos", asie2);
        savcar.put("anio", year2);
        savcar.put("capacidad_asientos", capasie2);
        savcar.put("precio", pre2);
        savcar.put("URI_IMG", uri2);
        savcar.put("descripcion", descrip2);
        savcar.put("idmarcas", idrefe1);
        savcar.put("idcolores", idrefe1);
        savcar.put("idtipoautomovil", idrefe1);
        savcar.put("idautomovil", idrefe1);
        carsmotors.insert("automovil", null, savcar);
        carsmotors.close();
        mod.setText("");
        vin1.setText("");
        chas.setText("");
        motor1.setText("");
        asie.setText("");
        year.setText("");
        capasie.setText("");
        pre.setText("");
        uri.setText("");
        descrip1.setText("");
        Toast.makeText(this, "Se guardaron los datos correctamente",Toast.LENGTH_SHORT).show();
        Intent ret=new Intent(this, MainActivity.class);
        String ul12 = bundle.getString("uslog");
        ret.putExtra("uslog", ul12);
        startActivity(ret);
    }
}