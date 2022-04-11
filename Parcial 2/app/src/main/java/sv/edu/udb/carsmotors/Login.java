package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText usuar,passw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuar=(EditText) findViewById(R.id.logusuario);
        passw=(EditText) findViewById(R.id.logcontra);
    }
    public void autenticar (View view)
    {
        BaseDeDatos regis = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= regis.getWritableDatabase();
        String use1 = usuar.getText().toString();
        String pas1 = passw.getText().toString();
        String ti = "Administrador";
        String ti1 = "Cliente";
        Cursor fila = carsmotors.rawQuery("SELECT user,tipo FROM usuario WHERE password =" + pas1, null);
        //si es administrador
         if (fila.moveToFirst()&&use1.equals(fila.getString(0))&&ti.equals(fila.getString(1))){
             carsmotors.close();
             Intent aut=new Intent(this, MainActivity.class);
             aut.putExtra("uslog", use1);
             startActivity(aut);
             usuar.setText("");
             passw.setText("");

             //si es cliente
         } else if (fila.moveToFirst()&&use1.equals(fila.getString(0))&&ti1.equals(fila.getString(1)))
         {
             carsmotors.close();
             //para mientras
             Intent aut1=new Intent(this, Registro.class);
             startActivity(aut1);
             usuar.setText("");
             passw.setText("");
    } else
            Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
         usuar.setText("");
         passw.setText("");
        carsmotors.close();
    }
    public void registrar (View view)
    {
        Intent reg=new Intent(this, Registro.class);
        startActivity(reg);
    }
}