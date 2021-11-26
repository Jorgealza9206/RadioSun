package com.example.radiosun.clases.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.radiosun.clases.SQLiteConex;
import com.example.radiosun.modelos.Sitio;

import java.util.ArrayList;

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
            valores.put("potencia_del_panel ",sit.getP_panel());
            valores.put("numero_de_paneles",sit.getN_panel());

            id = db.insert("sitios", "null", valores);
        }catch(Exception ex){

        }

        return id;

    }

    public ArrayList<Sitio> listar(){
        SQLiteConex dbc = new SQLiteConex(this.context);
        SQLiteDatabase db = dbc.getWritableDatabase();

        ArrayList<Sitio> sitios = new ArrayList<>();

        Cursor cregistros = db.rawQuery("select nombre, radiacion, consumo, mes_1, mes_2" +
                "mes_3,mes_4,mes_5,mes_6,potencial_del_panel,numero_de_paneles" +
                " from sitios",null);

        if(cregistros.moveToFirst()){
            do{
                Sitio sit = new Sitio();
                sit.setNombre(cregistros.getString(0));
                sit.setRadiacion(cregistros.getDouble(1));
                sit.setMes_1(cregistros.getDouble(3));
                sit.setMes_2(cregistros.getDouble(4));
                sit.setMes_3(cregistros.getDouble(5));
                sit.setMes_4(cregistros.getDouble(6));
                sit.setMes_5(cregistros.getDouble(7));
                sit.setMes_6(cregistros.getDouble(8));
                sit.setP_panel(cregistros.getDouble(9));
                sit.setN_panel(cregistros.getDouble(10));

                sitios.add(sit);

            }while(cregistros.moveToNext());


        }
        cregistros.close();

        return sitios;
    }

}
