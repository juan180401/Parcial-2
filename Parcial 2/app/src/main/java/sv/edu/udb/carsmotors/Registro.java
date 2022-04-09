package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    String elementos[]={"Cliente","Administrador"};
    Spinner tipousua;
    EditText nom,apell,corre,usuar,passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nom=(EditText) findViewById(R.id.nombre);
        apell=(EditText) findViewById(R.id.apellido);
        corre=(EditText) findViewById(R.id.email);
        usuar=(EditText) findViewById(R.id.usuario);
        passw=(EditText) findViewById(R.id.contra);
        tipousua=(Spinner) findViewById(R.id.tipousuarreg);
        ArrayAdapter<String> spin= new ArrayAdapter<String>(this,R.layout.spinner_item_colores,elementos);
        tipousua.setAdapter(spin);
    }
public void registrarusuario (View view){
    BaseDeDatos regis = new BaseDeDatos(this,"cars", null, 1);
    SQLiteDatabase carsmotors= regis.getWritableDatabase();
    String nam = nom.getText().toString();
    String ape = apell.getText().toString();
    String em = corre.getText().toString();
    String use = usuar.getText().toString();
    String cont = passw.getText().toString();
    String tius = tipousua.getSelectedItem().toString();
    ContentValues regis1 = new ContentValues();
    regis1.put("nombres", nam);
    regis1.put("apellidos", ape);
    regis1.put("email", em);
    regis1.put("user", use);
    regis1.put("password", cont);
    regis1.put("tipo", tius);
    carsmotors.insert("usuario", null, regis1);
    carsmotors.close();
    nom.setText("");
    apell.setText("");
    corre.setText("");
    usuar.setText("");
    passw.setText("");
    Toast.makeText(this, "Se cargaron los datos del nuevo usuario",Toast.LENGTH_SHORT).show();
    Intent log=new Intent(this, Login.class);
    startActivity(log);
}
    public void volver (View view)
    {
        finish();
    }
}