package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarCarro extends AppCompatActivity {
    TextView us;
    EditText cod3, mar3,col3,mod3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_carro);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog4);
        us.setText(ul12);
        cod3=(EditText) findViewById(R.id.codigo3);
        mar3= (EditText) findViewById(R.id.marca3);
        col3= (EditText) findViewById(R.id.color3);
        mod3= (EditText) findViewById(R.id.modelo3);
    }
    public void verificar1 (View view){
        BaseDeDatos veri1 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= veri1.getWritableDatabase();
        String codig=cod3.getText().toString();
        Cursor conmar1 = carsmotors.rawQuery("select nombre from marcas where idmarcas='" + codig+"'", null);
        Cursor concol1 = carsmotors.rawQuery("select descripcion from colores where idcolores='" + codig+"'", null);
        Cursor conmod1 = carsmotors.rawQuery("select modelo from automovil where idautomovil='" + codig+"'", null);
        if (conmar1.moveToFirst()&&concol1.moveToFirst()&&conmod1.moveToFirst()) {
            mar3.setText(conmar1.getString(0));
            col3.setText(concol1.getString(0));
            mod3.setText(conmod1.getString(0));
        } else
            Toast.makeText(this, "No existe el vehiculo solicitado con dicho código",
                    Toast.LENGTH_SHORT).show();
        carsmotors.close();
    }
    public void eliminar (View view){
        BaseDeDatos elim = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= elim.getWritableDatabase();
        String codig=cod3.getText().toString();
        int marelim = carsmotors.delete("marcas", "idmarcas='" + codig+"'", null);
        int colelim = carsmotors.delete("colores", "idcolores='" + codig+"'", null);
        int tiplim = carsmotors.delete("tipo_automovil", "idtipoautomovil='" + codig+"'", null);
        int autelim = carsmotors.delete("automovil", "idautomovil='" + codig+"'", null);
        carsmotors.close();
        cod3.setText("");
        mar3.setText("");
        col3.setText("");
        mod3.setText("");
        if (marelim == 1&&colelim == 1&&tiplim == 1&&autelim == 1) {
            Toast.makeText(this, "Vehiculo Eliminado Satisfactoriamente",
                    Toast.LENGTH_SHORT).show();
        }       else
            Toast.makeText(this, "No existe Vehiculo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }
    public void volv (View view){
        Intent vol=new Intent(this, MainActivity.class);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        vol.putExtra("uslog", ul12);
        startActivity(vol);
    }
    }
