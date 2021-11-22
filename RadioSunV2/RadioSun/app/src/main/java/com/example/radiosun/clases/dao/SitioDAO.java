package com.example.radiosun.clases.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.radiosun.clases.SQLiteConex;
import com.example.radiosun.modelos.Sitio;

public class SitioDAO extends SQLiteConex {

    private Context context;

    public SitioDAO(@Nullable Context c){

        super(c);
        this.context = c;
    }

    public long Insertar(Sitio sit){

        long id = 0;

        try{
            SQLiteConex dbc = new SQLiteConex(this.context);
            SQLiteDatabase db = dbc.getWritableDatabase();

            ContentValues valores= new ContentValues();
            valores.put("nombre",sit.getNombre());
            valores.put("radiacion",sit.getRadiacion());
            valores.put("consumo",sit.isConsumo());
            valores.put("mes_1",sit.getMes_1());
            valores.put("mes_2",sit.getMes_2());
            valores.put("mes_3",sit.getMes_3());
            valores.put("mes_4",sit.getMes_4());
            valores.put("mes_5",sit.getMes_5());
            valores.put("mes_6",sit.getMes_6());

            id = db.insert("sitios", "null", valores);
        }catch(Exception ex){

        }

        return id;

    }

}
