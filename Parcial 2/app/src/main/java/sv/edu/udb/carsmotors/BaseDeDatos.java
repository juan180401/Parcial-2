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
        carsmotors.execSQL("create table usuario(idusuario integer primary key autoincrement,nombres text,apellidos text, email text, user text, password text, tipo text)");
        carsmotors.execSQL("create table marcas(idmarcas integer primary key autoincrement,nombre text)");
        carsmotors.execSQL("create table colores(idcolores integer primary key autoincrement,descripcion text)");
        carsmotors.execSQL("create table tipo_automovil(idtipoautomovil integer primary key autoincrement,descripcion text)");
        carsmotors.execSQL("create table automovil(idautomovil integer primary key autoincrement,modelo text,numero_vin text,numero_chasis text,numero_motor text,numero_asientos integer, anio year,capacidad_asientos int, precio decimal, URI_IMG text, descripcion text, idmarcas references marcas(idmarcas), idtipoautomovil references tipo_automovil(idtipoautomovil),idcolores references colores(idcolores))");
        carsmotors.execSQL("create table favoritos_automovil(idfavoritosautomovil integer primary key autoincrement,fecha_agregado timestamp,idusuario references usuario(idusuario),idmarcas references automovil(idautomovil) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase carsmotors, int i, int i1) {

    }
}