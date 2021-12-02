package com.example.radiosun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.radiosun.clases.Mensajes;
import com.example.radiosun.clases.dao.SitioDAO;
import com.example.radiosun.modelos.Sitio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

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
            LatLng bogota = new LatLng(4.64069407887268, -74.08643975574985);
            googleMap.addMarker(new MarkerOptions().position(bogota).title("Marcador en Bogota"));
            googleMap.setMinZoomPreference(5);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(bogota));
            ((Map) getActivity()).actualizarCoordenadas(bogota.latitude,bogota.longitude);
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    googleMap.clear();
                    LatLng coordenada = new LatLng(latLng.latitude, latLng.longitude);
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
        return inflater.inflate(R.layout.fragment_mapa, container, false);
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
}