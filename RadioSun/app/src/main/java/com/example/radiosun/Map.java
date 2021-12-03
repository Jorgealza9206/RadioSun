package com.example.radiosun;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Map extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        obtenerPermiso();

        //Inicializando el Toolbar del activity map
        Toolbar map_toolbar = (Toolbar) findViewById(R.id.map_toolbar_2);
        setSupportActionBar(map_toolbar);

        FloatingActionButton add_user = (FloatingActionButton) findViewById(R.id.map_add_site);

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarSitioFragment.newInstance(latitud,longitud).show(getSupportFragmentManager(), null);

            }
        });

    }

    // Comportamiento del action Bar del menu map
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId(); // Creando la variable entera que corresponde a las acciones del Menu

        //Configurando las opciones del menu y las acciones que harán al hacerse click
        switch (id){

            case R.id.opcion1:
                Intent goHomefromMap = new Intent(Map.this,Home.class);
                startActivity(goHomefromMap);
                break;
            case R.id.opcion3:
                Intent goTusSitiosfromMap = new Intent(Map.this,Sitios.class);
                startActivity(goTusSitiosfromMap);
                break;
            case R.id.opcion4:
                Intent goAcercadefromMap = new Intent(Map.this,Acerca_de.class);
                startActivity(goAcercadefromMap);
                break;
            case R.id.opcion5:
                Intent goConfiguracionfromMap = new Intent(Map.this,Configuracion.class);
                startActivity(goConfiguracionfromMap);
                break;
            case R.id.opcion6:
                AlertDialog.Builder Alert_CerrarSesion = new AlertDialog.Builder(Map.this);
                Alert_CerrarSesion.setTitle("Cerrar Sesión");
                Alert_CerrarSesion.setMessage("¿Esta seguro que desea cerrar sesión?");
                Alert_CerrarSesion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth autenticacion = FirebaseAuth.getInstance();
                        autenticacion.signOut();
                        Intent goLogin = new Intent(Map.this, Login.class);
                        startActivity(goLogin);

                    }
                });
                Alert_CerrarSesion.show();
                break;
            default:
        }


        /*
        // Configurando la acción de cada uno de los items del menu_map
        if(id==R.id.opcion1){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opcion2){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opcion3){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opcion4) {
            AlertDialog.Builder miDialogo = new AlertDialog.Builder(Map.this);
            miDialogo.setTitle("Nuevo Sitio");
            miDialogo.setMessage("Ingrese consumo");
            miDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goHome = new Intent(Map.this, Home.class);
                    startActivity(goHome);
                }
            });
            miDialogo.show();
        }else{
            Toast.makeText(this,R.string.main_menu_opcion4,Toast.LENGTH_LONG).show();
        }
        */


        return true;

    }

public void actualizarCoordenadas(double lat, double lon)
{
    this.latitud = lat;
    this.longitud = lon;
}

    public void obtenerPermiso() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            boolean locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

    }

}