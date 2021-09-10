package com.example.autotall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AyudaDBarril extends SQLiteOpenHelper {

    public static final String nombreDB = "barril.db";
    public static final String nombreTabla = "barril";
    public static final String columna_2 = "Cantidad";
    public static final String columna_3 = "Fecha";
    public static final String columna_4 = "Hora";

    public AyudaDBarril(@Nullable Context context) {
        super(context, nombreDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + nombreTabla + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + columna_2 + " TEXT, " + columna_3 + " TEXT, "+columna_4+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + nombreTabla);
        onCreate(db);
    }

    public boolean llenar() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + nombreTabla, null);
        if (datos.getCount() == 0) {
            insertar("90%", "07/07/2020", "09:30:58 AM");
            insertar("98%", "07/07/2020", "09:32:33 AM");
            insertar("80%", "07/07/2020", "09:33:25 AM");
            insertar("95%", "07/07/2020", "09:35:18 AM");
            insertar("87%", "08/07/2020", "08:36:12 AM");
            insertar("93%", "08/07/2020", "08:36:44 AM");
            return false;
        }
        return true;
    }

    public boolean insertar(String cantidad, String fecha, String hora) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put(columna_2, cantidad);
        contentValues.put(columna_3, fecha);
        contentValues.put(columna_4, hora);
        if (db.insert(nombreTabla, null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public Cursor listar() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + nombreTabla, null);
        return datos;
    }
}
