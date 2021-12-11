package com.example.radiosun.interfaces;

import com.example.radiosun.modelos.Peticion;
import com.example.radiosun.modelos.Sitio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface radiacionAPI {

    @GET("api/temporal/climatology/point")
    Call<Peticion> peticion(@Field("properties.parameter.ALLSKY_SFC_SW_DWN.ANN") double radiacion)

}
