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

public class RegistroDeCarrosPorBusqueda extends AppCompatActivity {
    TextView us;
    EditText cod5, mar5,col5,mod5,pre5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_de_carros_por_busqueda);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog6);
        us.setText(ul12);
        cod5=(EditText) findViewById(R.id.codigo5);
        mar5= (EditText) findViewById(R.id.marca5);
        col5= (EditText) findViewById(R.id.color5);
        mod5= (EditText) findViewById(R.id.modelo5);
        pre5=(EditText)  findViewById(R.id.Precio5);
    }
    public void verificarregis (View view){
        BaseDeDatos veri5 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= veri5.getWritableDatabase();
        String codig=cod5.getText().toString();
        Cursor conmar1 = carsmotors.rawQuery("select nombre from marcas where idmarcas='" + codig+"'", null);
        Cursor concol1 = carsmotors.rawQuery("select descripcion from colores where idcolores='" + codig+"'", null);
        Cursor conmod1 = carsmotors.rawQuery("select modelo,precio from automovil where idautomovil='" + codig+"'", null);
        if (conmar1.moveToFirst()&&concol1.moveToFirst()&&conmod1.moveToFirst()) {
            mar5.setText(conmar1.getString(0));
            col5.setText(concol1.getString(0));
            mod5.setText(conmod1.getString(0));
            pre5.setText(conmod1.getString(1));
        } else
            Toast.makeText(this, "No existe el vehiculo solicitado con dicho código",
                    Toast.LENGTH_SHORT).show();
        carsmotors.close();
    }
    public void volv (View view){
        finish();
    }
}