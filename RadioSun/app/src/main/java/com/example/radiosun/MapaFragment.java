package com.example.radiosun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.radiosun.clases.Mensajes;
import com.example.radiosun.interfaces.radiacionAPI;
import com.example.radiosun.modelos.Peticion;
import com.example.radiosun.modelos.Sitio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapaFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            llenarMarcadores(googleMap,null);
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    googleMap.clear();
                    LatLng coordenada = new LatLng(latLng.latitude, latLng.longitude);
                    llenarMarcadores(googleMap,coordenada);
                    googleMap.addMarker(new MarkerOptions().position(coordenada).title("Nueva Posicion"));
                    googleMap.setMinZoomPreference(5);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordenada));
                    ((Map) getActivity()).actualizarCoordenadas(latLng.latitude, latLng.longitude);
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mapa, container, false);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


    }
    public void llenarMarcadores(GoogleMap googleMap,LatLng coordenada){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("Sitio").get().addOnCompleteListener(getActivity(), new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    LatLng ultimacoordenada = coordenada;
                    for(DataSnapshot registro : task.getResult().getChildren())
                    {
                        Sitio s = registro.getValue(Sitio.class);
                        LatLng marcador = new LatLng(s.getLatitud(), s.getLongitud());
                        googleMap.addMarker(new MarkerOptions().position(marcador).title(s.getNombre()));
                        if(coordenada==null){
                            ultimacoordenada = marcador;
                        }
                    }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(ultimacoordenada));

                }
            }
        });
    }

    private ArrayList<Object> radiacion(String latitud, String longitud, View v){
        ArrayList<Object> r;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://power.larc.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        radiacionAPI radAPI = retrofit.create(radiacionAPI.class);
        Call<Peticion> call = radAPI.peticion();
        call.enqueue(new Callback<Sitio>() {
            @Override
            public void onResponse(Call<Sitio> call, Response<Sitio> response) {
                try{
                    if(response.isSuccessful()){
                        Peticion p = response.body();
                        String URL = "https://power.larc.nasa.gov/api/temporal/climatology/point?parameters=ALLSKY_SFC_SW_DWN&community=RE&longitude=" + longitud + "&latitude=" + latitud + "&format=JSON";
                        r = p.properties.parameter.ALLSKY_SFC_SW_DWN.ANN;
                    }
                }catch(Exception ex){
                    new Mensajes(v.getContext()).toast("No se encontró la latitud" );
                }
            }

            @Override
            public void onFailure(Call<Sitio> call, Throwable t) {

            }
        });
    }
}