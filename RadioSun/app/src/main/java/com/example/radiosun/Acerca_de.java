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

public class Acerca_de extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);


        //Inicializando el Toolbar del activity map
        Toolbar acercade_toolbar = (Toolbar) findViewById(R.id.acercade_toolbar);
        setSupportActionBar(acercade_toolbar);

    }

    // Comportamiento del action Bar del menu map
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acerca_de,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId(); // Creando la variable entera que corresponde a las acciones del Menu

        //Configurando las opciones del menu y las acciones que harán al hacerse click
        switch (id){

            case R.id.opcion1:
                Intent goHomefromAcerca_de= new Intent(Acerca_de.this,Home.class);
                startActivity(goHomefromAcerca_de);
                break;
            case R.id.opcion2:
                Intent goMapfromAcerca_de = new Intent(Acerca_de.this,Map.class);
                startActivity(goMapfromAcerca_de);
                break;
            case R.id.opcion3:
                Intent goTusSitiosfromAcerca_de = new Intent(Acerca_de.this,Sitios.class);
                startActivity(goTusSitiosfromAcerca_de);
                break;
            case R.id.opcion4:
                Intent goAcercadefromAcerca_de = new Intent(Acerca_de.this,Acerca_de.class);
                startActivity(goAcercadefromAcerca_de);
                break;
            case R.id.opcion5:
                Intent goConfiguracionfromAcerca_de= new Intent(Acerca_de.this,Configuracion.class);
                startActivity(goConfiguracionfromAcerca_de);
                break;
            case R.id.opcion6:
                AlertDialog.Builder Alert_CerrarSesion = new AlertDialog.Builder(Acerca_de.this);
                Alert_CerrarSesion.setTitle("Cerrar Sesión");
                Alert_CerrarSesion.setMessage("¿Esta seguro que desea cerrar sesión?");
                Alert_CerrarSesion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth autenticacion = FirebaseAuth.getInstance();
                        autenticacion.signOut();
                        Intent goLogin = new Intent(Acerca_de.this, Login.class);
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