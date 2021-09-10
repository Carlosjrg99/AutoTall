package com.example.autotall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import androidx.annotation.Nullable;

public class AyudaBD extends SQLiteOpenHelper {

    public static final String nombreDB = "usuario.db";
    public static final String nombreTabla = "usuario";
    public static final String columna_1 = "Usuario";
    public static final String columna_2 = "Password";
    public static final String columna_3 = "Tipo";

    public AyudaBD(@Nullable Context context) {
        super(context, nombreDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + nombreTabla + "(" + columna_1 + " TEXT PRIMARY KEY,"
                + columna_2 + " TEXT, " + columna_3 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + nombreTabla);
        onCreate(db);
    }

    public boolean insertar(String usuario, String password, String tipo) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put(columna_1, usuario);
        contentValues.put(columna_2, password);
        contentValues.put(columna_3, tipo);
        if (db.insert(nombreTabla, null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public boolean revisarAdmin() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + nombreTabla + " WHERE " + columna_3
                + " = 'Administrador'", null);
        if (datos.getCount() == 0) {
            insertar("admin", "admin", "Administrador");
            return false;
        }
        return true;
    }

    public Cursor ingresar(String usuario, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + nombreTabla + " WHERE " + columna_1
                + " = '" + usuario + "' AND " + columna_2 + " = '" + password + "';", null);
        return datos;
    }

    public Integer eliminar(String usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(nombreTabla, columna_1 + " = ?", new String[]{usuario});
    }

    public boolean editar(String usuarioA, Editable usuarioN, Editable password, String tipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean actualizar = false;
        if(usuarioN.length() > 0) {
            contentValues.put(columna_1,usuarioN.toString());
            actualizar = true;
        }
        if(password.length() > 0) {
            contentValues.put(columna_2,password.toString());
            actualizar = true;
        }
        if(tipo != null) {
            contentValues.put(columna_3,tipo);
            actualizar = true;
        }
        db.update(nombreTabla,contentValues,columna_1+" = ?",new String[] {usuarioA});
        return actualizar;
    }
}
