package com.example.radiosun.interfaces;

import com.example.radiosun.modelos.Peticion;
import com.example.radiosun.modelos.Propiedades;
import com.example.radiosun.modelos.Sitio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface radiacionAPI {

    @GET("api/temporal/climatology/point")
    Call<Propiedades> peticion(@Field("properties") Propiedades radiacion);

}
