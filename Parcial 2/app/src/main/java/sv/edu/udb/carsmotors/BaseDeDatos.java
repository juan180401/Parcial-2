package sv.edu.udb.carsmotors;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper{
    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase carsmotors) {
        carsmotors.execSQL("create table usuario(idusuario text primary key,nombres text,apellidos text, email text, user text, password text, tipo text)");
        carsmotors.execSQL("create table marcas(idmarcas text primary key,nombre text)");
        carsmotors.execSQL("create table colores(idcolores text primary key,descripcion text)");
        carsmotors.execSQL("create table tipo_automovil(idtipoautomovil text primary key,descripcion text)");
        carsmotors.execSQL("create table automovil(idautomovil text primary key,modelo text,numero_vin text,numero_chasis text,numero_motor text,numero_asientos text, anio text,capacidad_asientos text, precio text, URI_IMG text, descripcion text, idmarcas text references marcas(idmarcas), idtipoautomovil text references tipo_automovil(idtipoautomovil),idcolores text references colores(idcolores))");
        carsmotors.execSQL("create table favoritos_automovil(idfavoritosautomovil text primary key,fecha_agregado text,idusuario text references usuario(idusuario),idmarcas text references automovil(idautomovil) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase carsmotors, int i, int i1) {

    }
}