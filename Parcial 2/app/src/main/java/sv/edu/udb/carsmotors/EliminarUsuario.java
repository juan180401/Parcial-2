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

public class EliminarUsuario extends AppCompatActivity {
    TextView us;
    EditText cod3, us3,no3,ap3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog5);
        us.setText(ul12);
        cod3=(EditText) findViewById(R.id.codigo4);
        us3=(EditText) findViewById(R.id.usua4);
        no3=(EditText) findViewById(R.id.nom4);
        ap3=(EditText) findViewById(R.id.apell4);
    }
    public void verificar2 (View view){
        BaseDeDatos veri4 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= veri4.getWritableDatabase();
        String codig=cod3.getText().toString();
        Cursor conus12 = carsmotors.rawQuery("select user,nombres,apellidos from usuario where idusuario='" + codig+"'", null);
        if (conus12.moveToFirst()) {
            us3.setText(conus12.getString(0));
            no3.setText(conus12.getString(1));
            ap3.setText(conus12.getString(2));
        } else
            Toast.makeText(this, "No existe el Usuario solicitado con dicho código",
                    Toast.LENGTH_SHORT).show();
        carsmotors.close();
    }
    public void eliminarus (View view){
        BaseDeDatos elimu = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= elimu.getWritableDatabase();
        String codig=cod3.getText().toString();
        int uselim = carsmotors.delete("usuario", "idusuario='" + codig+"'", null);
        carsmotors.close();
        cod3.setText("");
        us3.setText("");
        no3.setText("");
        ap3.setText("");
        if (uselim == 1) {
            Toast.makeText(this, "Usuario Eliminado Satisfactoriamente",
                    Toast.LENGTH_SHORT).show();
        }       else
            Toast.makeText(this, "Error al eliminar Usuario",
                    Toast.LENGTH_SHORT).show();
        cod3.setText("");
        us3.setText("");
        no3.setText("");
        ap3.setText("");
    }
    public void volv (View view){
        Intent vol=new Intent(this, MainActivity.class);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        vol.putExtra("uslog", ul12);
        startActivity(vol);
        finish();
    }
}