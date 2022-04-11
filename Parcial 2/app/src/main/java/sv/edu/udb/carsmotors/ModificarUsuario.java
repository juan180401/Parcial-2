package sv.edu.udb.carsmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

public class ModificarUsuario extends AppCompatActivity {
    String elementos2[]={"Cliente","Administrador"};
    Spinner tipousua2;
    EditText codus2,nom2,apell2,corre2,usuar2,passw2;
    TextView us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);
        Bundle bundle = getIntent().getExtras();
        String ul12 = bundle.getString("uslog");
        us=(TextView)  findViewById(R.id.usulog7);
        us.setText(ul12);
        codus2=(EditText) findViewById(R.id.cod2);
        nom2=(EditText) findViewById(R.id.nombre2);
        apell2=(EditText) findViewById(R.id.apellido2);
        corre2=(EditText) findViewById(R.id.email2);
        usuar2=(EditText) findViewById(R.id.usuario2);
        passw2=(EditText) findViewById(R.id.contra2);
        tipousua2=(Spinner) findViewById(R.id.tipousuarreg2);
        ArrayAdapter<String> spin2= new ArrayAdapter<String>(this,R.layout.spinner_item_colores,elementos2);
        tipousua2.setAdapter(spin2);
    }
    public void veriusua (View view){
        BaseDeDatos veriusu = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= veriusu.getWritableDatabase();
        String codig=codus2.getText().toString();
        Cursor conusu1 = carsmotors.rawQuery("select nombres,apellidos,email,user,password,tipo from usuario where idusuario='" + codig+"'", null);
        if (conusu1.moveToFirst()) {
            nom2.setText(conusu1.getString(0));
            apell2.setText(conusu1.getString(1));
            corre2.setText(conusu1.getString(2));
            usuar2.setText(conusu1.getString(3));
            passw2.setText(conusu1.getString(4));

            // EVITAMOS QUE APAREZCAN 3 OPCIONES EN SPIN DE TIPO DE CLIENTE
            if(conusu1.getString(5).equals("Administrador")) {
                String tius[] = {conusu1.getString(5), "Cliente"};
                ArrayAdapter<String> spin3 = new ArrayAdapter<String>(this, R.layout.spinner_item_colores, tius);
                tipousua2.setAdapter(spin3);
            }else if(conusu1.getString(5).equals("Cliente")){
                String tius[]={conusu1.getString(5),"Administrador"};
                ArrayAdapter<String> spin3= new ArrayAdapter<String>(this,R.layout.spinner_item_colores, tius);
                tipousua2.setAdapter(spin3);
            }
        } else
            Toast.makeText(this, "No existe ningun Usuario con dicho código", Toast.LENGTH_SHORT).show();
        carsmotors.close();
    }

    public void registrarusuario (View view){
        BaseDeDatos modusu1 = new BaseDeDatos(this,"cars", null, 1);
        SQLiteDatabase carsmotors= modusu1.getWritableDatabase();
        String cod12 = codus2.getText().toString();
        String nam12 = nom2.getText().toString();
        String ape12 = apell2.getText().toString();
        String em12 = corre2.getText().toString();
        String use12 = usuar2.getText().toString();
        String cont12 = passw2.getText().toString();
        String tius12 = tipousua2.getSelectedItem().toString();
        ContentValues usunew = new ContentValues();
        usunew.put("idusuario", cod12);
        usunew.put("nombres", nam12);
        usunew.put("apellidos", ape12);
        usunew.put("email", em12);
        usunew.put("user", use12);
        usunew.put("password", cont12);
        usunew.put("tipo", tius12);
        int usn12 = carsmotors.update("usuario",usunew,"idusuario='"+cod12+"'",null);
        carsmotors.close();
        if(usn12==1){
            Toast.makeText(this, "Modificacion Satifactoria", Toast.LENGTH_SHORT).show();
            codus2.setText("");
            nom2.setText("");
            apell2.setText("");
            corre2.setText("");
            usuar2.setText("");
            passw2.setText("");
        }
    }
    public void volver (View view){
        finish();
    }
}