package com.example.radiosun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Inicializando el Toolbar del activity map
        Toolbar configuracion_toolbar = (Toolbar) findViewById(R.id.configuracion_toolbar);
        setSupportActionBar(configuracion_toolbar);
    }



    // Comportamiento del action Bar del menu map
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_configuracion,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId(); // Creando la variable entera que corresponde a las acciones del Menu

        //Configurando las opciones del menu y las acciones que harán al hacerse click
        switch (id){

            case R.id.opcion1:
                Intent goHomefromConfiguracion = new Intent(Configuracion.this,Home.class);
                startActivity(goHomefromConfiguracion);
                break;
            case R.id.opcion2:
                Intent goMapfromConfiguracion = new Intent(Configuracion.this,Map.class);
                startActivity(goMapfromConfiguracion);
                break;
            case R.id.opcion3:
                Intent goTusSitiosfromConfiguracion = new Intent(Configuracion.this,Sitios.class);
                startActivity(goTusSitiosfromConfiguracion);
                break;
            case R.id.opcion4:
                Intent goAcercadefromConfiguracion = new Intent(Configuracion.this,Acerca_de.class);
                startActivity(goAcercadefromConfiguracion);
                break;
            case R.id.opcion5:
                Intent goConfiguracionfromConfiguracion= new Intent(Configuracion.this,Configuracion.class);
                startActivity(goConfiguracionfromConfiguracion);
                break;
            case R.id.opcion6:
                AlertDialog.Builder Alert_CerrarSesion = new AlertDialog.Builder(Configuracion.this);
                Alert_CerrarSesion.setTitle("Cerrar Sesión");
                Alert_CerrarSesion.setMessage("¿Esta seguro que desea cerrar sesión?");
                Alert_CerrarSesion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth autenticacion = FirebaseAuth.getInstance();
                        autenticacion.signOut();
                        Intent goLogin = new Intent(Configuracion.this, Login.class);
                        startActivity(goLogin);
                    }
                });
                Alert_CerrarSesion.show();
                break;
            default:
        }
        return true;

    }


}