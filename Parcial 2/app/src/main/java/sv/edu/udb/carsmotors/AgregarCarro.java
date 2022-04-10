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

public class AgregarCarro extends AppCompatActivity {
EditText cod, mar1,col1,tipaut1;
    TextView us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

        cod=(EditText) findViewById(R.id.codigo);
        mar1= (EditText) findViewById(R.id.marca);
        col1= (EditText) findViewById(R.id.color);
        tipaut1= (EditText) findViewById(R.id.tipoauto);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog);
        us.setText(ul12);
    }
    public void siguiente (View view){
        BaseDeDatos regis2 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= regis2.getWritableDatabase();
        String cod1=cod.getText().toString();
        String mar2 = mar1.getText().toString();
        String col2 = col1.getText().toString();
        String tipaut2 = tipaut1.getText().toString();
        ContentValues marcadat = new ContentValues();
        ContentValues colordat = new ContentValues();
        ContentValues tipodat = new ContentValues();
        marcadat.put("nombre", mar2);
        marcadat.put("idmarcas", cod1);
        colordat.put("descripcion", col2);
        colordat.put("idcolores", cod1);
        tipodat.put("descripcion", tipaut2);
        tipodat.put("idtipoautomovil", cod1);
        carsmotors.insert("marcas", null, marcadat);
        carsmotors.insert("colores", null, colordat);
        carsmotors.insert("tipo_automovil", null, tipodat);

        //llevarnos id para llaves foraneas
        /*Cursor ma12 = carsmotors.rawQuery("SELECT idmarcas FROM marcas WHERE nombre =" + mar2, null);
        Cursor co12 = carsmotors.rawQuery("SELECT idcolores FROM colores WHERE descripcion =" + col2, null);
        Cursor autip = carsmotors.rawQuery("SELECT idtipoautomovil FROM tipo_automovil WHERE descripcion =" + tipaut2, null);
        Integer idma = Integer.parseInt(ma12.getString(0));
        Integer idcol = Integer.parseInt(co12.getString(0));
        Integer idti = Integer.parseInt(autip.getString(0));*/
        carsmotors.close();
        cod.setText("");
        mar1.setText("");
        col1.setText("");
        tipaut1.setText("");
        Intent carr1=new Intent(this, AgregarCarro2.class);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        carr1.putExtra("idref1",cod1);
        carr1.putExtra("uslog", ul12);
        startActivity(carr1);
    }
}