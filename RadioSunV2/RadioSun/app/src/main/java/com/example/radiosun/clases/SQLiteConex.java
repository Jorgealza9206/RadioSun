package com.example.radiosun.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConex extends SQLiteOpenHelper
{

    public static final  String nombredb = "Radiosun.db";

    public  SQLiteConex(@Nullable Context c){
        super(c, nombredb,null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sitios id INTEGER NOT NULL,nombre TEXT NOT NULL," +
                "radiación REAL NOT NULL," +
                "consumo BLOB NOT NULL," +
                "mes 1 REAL NOT NULL," +
                "mes 2 REAL NOT NULL," +
                "mes 3 REAL NOT NULL," +
                "mes 4 REAL NOT NULL," +
                "mes 5 REAL NOT NULL," +
                "mes 6 REAL NOT NULL," +
                "Potencia del panel REAL NOT NULL," +
                "Número de paneles REAL NOT NULL," +
                "PRIMARY KEY(id AUTOINCREMENT))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE sitios");
        onCreate(db);
    }
}
