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

public class ModificarCarro extends AppCompatActivity {
    TextView us;
    EditText cod2, mar2,col2,tipaut2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_carro);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog2);
        us.setText(ul12);
        cod2=(EditText) findViewById(R.id.codigo2);
        mar2= (EditText) findViewById(R.id.marca2);
        col2= (EditText) findViewById(R.id.color2);
        tipaut2= (EditText) findViewById(R.id.tipoauto2);

    }
    public void verificar (View view){
        BaseDeDatos veri1 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= veri1.getWritableDatabase();
        String codig=cod2.getText().toString();
        Cursor conmar = carsmotors.rawQuery("select nombre from marcas where idmarcas='" + codig+"'", null);
        Cursor concol = carsmotors.rawQuery("select descripcion from colores where idcolores='" + codig+"'", null);
        Cursor contip = carsmotors.rawQuery("select descripcion from tipo_automovil where idtipoautomovil='" + codig+"'", null);
        if (conmar.moveToFirst()&&concol.moveToFirst()&&contip.moveToFirst()) {
            mar2.setText(conmar.getString(0));
            col2.setText(concol.getString(0));
            tipaut2.setText(contip.getString(0));
        } else
            Toast.makeText(this, "No existe el vehiculo solicitado con dicho código",
                    Toast.LENGTH_SHORT).show();
        carsmotors.close();
    }
    public void modi1 (View view){
        BaseDeDatos mod1 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= mod1.getWritableDatabase();
        String codig=cod2.getText().toString();
        String marca2=mar2.getText().toString();
        String color2=col2.getText().toString();
        String tipcar2=tipaut2.getText().toString();

// cambio para cada tabla
        ContentValues autmar = new ContentValues();
        ContentValues autcol = new ContentValues();
        ContentValues auttia = new ContentValues();
        autmar.put("nombre", marca2);
        autcol.put("descripcion", color2);
        auttia.put("descripcion", tipcar2);
        int mo1 = carsmotors.update("marcas", autmar, "idmarcas='" + codig+"'", null);
        int mo2 = carsmotors.update("colores", autcol, "idcolores='" + codig+"'", null);
        int mo3 = carsmotors.update("tipo_automovil", auttia, "idtipoautomovil='" + codig+"'", null);
        carsmotors.close();
        if (mo1 == 1&&mo2 == 1&&mo3 == 1) {
            Toast.makeText(this, "Modificacion Satifactoria", Toast.LENGTH_SHORT).show();
            Intent car2=new Intent(this, ModificarCarro2.class);
            Bundle bundle = getIntent().getExtras();
            String ul12 = bundle.getString("uslog");
            car2.putExtra("idref2",codig);
            car2.putExtra("uslog", ul12);
            startActivity(car2);
            cod2.setText("");
            mar2.setText("");
            col2.setText("");
            tipaut2.setText("");
            finish();
        } else
            Toast.makeText(this, "no existe el vehiculo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
}