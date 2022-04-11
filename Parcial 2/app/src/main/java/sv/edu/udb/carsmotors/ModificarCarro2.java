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

public class ModificarCarro2 extends AppCompatActivity {
    TextView us;
    EditText mod2,vin2,chas2,motor2,uri2,descrip2,asie2,year2,capasie2,pre2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_carro2);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        String codig2= bundle.getString("idref2");
        us=(TextView)  findViewById(R.id.usulog3);
        us.setText(ul12);
        mod2=(EditText) findViewById(R.id.modelo2);
        vin2=(EditText) findViewById(R.id.vin2);
        chas2=(EditText) findViewById(R.id.chasis2);
        motor2=(EditText) findViewById(R.id.motor2);
        asie2=(EditText) findViewById(R.id.asientos2);
        year2=(EditText) findViewById(R.id.año2);
        capasie2=(EditText) findViewById(R.id.capaasien2);
        pre2=(EditText) findViewById(R.id.precio2);
        uri2=(EditText) findViewById(R.id.urimg2);
        descrip2=(EditText) findViewById(R.id.descr2);

        //capturando datos del vehiculo a actualizar
        BaseDeDatos cop12 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= cop12.getWritableDatabase();
        Cursor conauto = carsmotors.rawQuery("select modelo,numero_vin,numero_chasis,numero_motor,numero_asientos,anio,capacidad_asientos,precio,URI_IMG,descripcion from automovil where idautomovil='" + codig2+"'", null);
        if (conauto.moveToFirst()) {
            mod2.setText(conauto.getString(0));
            vin2.setText(conauto.getString(1));
            chas2.setText(conauto.getString(2));
            motor2.setText(conauto.getString(3));
            asie2.setText(conauto.getString(4));
            year2.setText(conauto.getString(5));
            capasie2.setText(conauto.getString(6));
            pre2.setText(conauto.getString(7));
            uri2.setText(conauto.getString(8));
            descrip2.setText(conauto.getString(9));
        }    }
public void guardarauto1 (View view){
    BaseDeDatos sav2 = new BaseDeDatos(this,"cars", null, 1);
    SQLiteDatabase carsmotors= sav2.getWritableDatabase();
    Bundle bundle = getIntent().getExtras();
    String codig2= bundle.getString("idref2");
    String mod22 = mod2.getText().toString();
    String vin22 = vin2.getText().toString();
    String chas22 = chas2.getText().toString();
    String motor22 = motor2.getText().toString();
    String asie22= asie2.getText().toString();
    String year22=year2.getText().toString();
    String capasie22=capasie2.getText().toString();
    String pre22=pre2.getText().toString();
    String uri22 = uri2.getText().toString();
    String descrip22 = descrip2.getText().toString();

    ContentValues modiglo = new ContentValues();
    modiglo.put("modelo", mod22);
    modiglo.put("numero_vin", vin22);
    modiglo.put("numero_chasis", chas22);
    modiglo.put("numero_motor", motor22);
    modiglo.put("numero_asientos", asie22);
    modiglo.put("anio", year22);
    modiglo.put("capacidad_asientos", capasie22);
    modiglo.put("precio", pre22);
    modiglo.put("URI_IMG", uri22);
    modiglo.put("descripcion", descrip22);

    int moglo = carsmotors.update("automovil", modiglo, "idautomovil='" +codig2 +"'", null);
    carsmotors.close();
    if (moglo == 1) {
        Toast.makeText(this, "Modificacion Completada Satisfactoriamente", Toast.LENGTH_SHORT).show();
        Intent car3=new Intent(this, MainActivity.class);
        String ul12 = bundle.getString("uslog");
        car3.putExtra("uslog", ul12);
        startActivity(car3);
        mod2.setText("");
        vin2.setText("");
        chas2.setText("");
        motor2.setText("");
        asie2.setText("");
        year2.setText("");
        capasie2.setText("");
        pre2.setText("");
        uri2.setText("");
        descrip2.setText("");
        finish();
    } else
        Toast.makeText(this, "Error Al Modificar",
                Toast.LENGTH_SHORT).show();
}
}
