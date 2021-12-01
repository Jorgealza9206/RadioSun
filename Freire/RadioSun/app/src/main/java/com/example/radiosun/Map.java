package com.example.radiosun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.radiosun.dialogos.InsertarSitioFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Map extends AppCompatActivity {

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Inicializando el Toolbar del activity map
        Toolbar map_toolbar = (Toolbar) findViewById(R.id.map_toolbar_2);
        setSupportActionBar(map_toolbar);

        //Agregar boton para añadir nuevos sitios
        FloatingActionButton BtnMapaAdd = (FloatingActionButton) findViewById(R.id.map_add);
        //Acción del boto Tus Sitios dentro del Activity Home
        BtnMapaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent goMap = new Intent(Map.this,Sitios.class);
                startActivity(goMap);*/
                InsertarSitioFragment.newInstance(latitud,longitud).show(getSupportFragmentManager(), null);

                //InsertarSitioFragment dialogoInsertarSitioFragment = new InsertarSitioFragment();

                //dialogoInsertarSitioFragment.show(getSupportFragmentManager(),"DialogoInsertarSitio");

            }
        });

        //Agregar boton para añadir nuevos sitios
        FloatingActionButton BtnMapaRemove = (FloatingActionButton) findViewById(R.id.map_remove);
        //Acción del boto Tus Sitios dentro del Activity Home
        BtnMapaRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Map.this,"Boton Eliminar marcardor",Toast.LENGTH_LONG).show();
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
                        Intent goLogin = new Intent(Map.this, Login.class);
                        startActivity(goLogin);
                    }
                });
                Alert_CerrarSesion.show();
                break;
            default:
        }


        return true;

    }

    public void actualizarCoordenadas(double lat, double lon)
    {
        this.latitud = lat;
        this.longitud = lon;
    }

}