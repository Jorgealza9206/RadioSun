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
            valores.put("latitud",sit.getLatitud());
            valores.put("longitud",sit.getLongitud());
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

    //Actualizando listar para que pueda consultar en el activity sitios
    public ArrayList<Sitio> listar(@Nullable String criterioBusqueda){

        SQLiteConex dbc = new SQLiteConex(this.context);
        SQLiteDatabase db = dbc.getWritableDatabase();

        String consultaSql = "select id,nombre, latitud, longitud, radiacion, consumo, mes_1, mes_2," +
                "mes_3,mes_4,mes_5,mes_6,potencia_del_panel,numero_de_paneles from sitios";



        ArrayList<Sitio> sitios = new ArrayList<>();

        if(criterioBusqueda!=null){
            consultaSql += " WHERE nombre like '%" + criterioBusqueda + "%' or radiacion like '%" + criterioBusqueda + "%'";
        }

        Cursor cregistros = db.rawQuery(consultaSql,null);

        if(cregistros.moveToFirst()){
            do{
                Sitio sit = new Sitio();
                sit.setId(cregistros.getInt(0));
                sit.setNombre(cregistros.getString(1));
                sit.setLatitud(cregistros.getDouble(2));
                sit.setLongitud(cregistros.getDouble(3));
                sit.setRadiacion(cregistros.getDouble(4));
                //Aquí iría consumo
                sit.setMes_1(cregistros.getDouble(6));
                sit.setMes_2(cregistros.getDouble(7));
                sit.setMes_3(cregistros.getDouble(8));
                sit.setMes_4(cregistros.getDouble(9));
                sit.setMes_5(cregistros.getDouble(10));
                sit.setMes_6(cregistros.getDouble(11));
                sit.setP_panel(cregistros.getDouble(12));
                sit.setN_panel(cregistros.getDouble(13));

                sitios.add(sit);

            }while(cregistros.moveToNext());


        }
        cregistros.close();

        return sitios;
    }

    public boolean eliminar(long id)
    {
        boolean eliminado = false;

        SQLiteConex conexion = new SQLiteConex(this.context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM sitios " +
                    "WHERE id = '" + String.valueOf(id) + "'");
            eliminado = true;
        }
        catch (Exception ex){

        }

        return eliminado;
    }

}
