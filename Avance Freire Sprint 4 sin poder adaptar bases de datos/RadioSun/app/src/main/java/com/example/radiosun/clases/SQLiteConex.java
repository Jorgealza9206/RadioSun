package com.example.radiosun.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConex extends SQLiteOpenHelper
{

    private Context context;

    public static final  String nombredb = "Radiosun.db";

    public  SQLiteConex(@Nullable Context c){
        super(c, nombredb,null,2);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sitios "+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "latitud REAL NOT NULL," +
                "longitud REAL NOT NULL," +
                "radiacion REAL NOT NULL," +
                "consumo BLOB," +
                "mes_1 REAL NOT NULL," +
                "mes_2 REAL NOT NULL," +
                "mes_3 REAL NOT NULL," +
                "mes_4 REAL NOT NULL," +
                "mes_5 REAL NOT NULL," +
                "mes_6 REAL NOT NULL," +
                "potencia_del_panel REAL NOT NULL," +
                "numero_de_paneles REAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE sitios");
        onCreate(db);
    }
}
