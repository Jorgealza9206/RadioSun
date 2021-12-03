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
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Inicializando el Toolbar del activity Home
        Toolbar home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(home_toolbar);

        //Iniciado los botones del Activity home
        ImageButton TusSitios = (ImageButton) findViewById(R.id.btn_missitios);
        ImageButton Mapas = (ImageButton) findViewById(R.id.home_button_Maps);

        //Acción del boto Tus Sitios dentro del Activity Home
        TusSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSitios = new Intent(Home.this, Sitios.class);
                startActivity(goSitios);
            }
        });

        //Acción del boto Tus Sitios dentro del Activity Home
        Mapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMap = new Intent(Home.this, Map.class);
                startActivity(goMap);
            }
        });

    }


    // Comportamiento del action Bar del menu home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // Creando la variable entera que corresponde a las acciones del Menu

        //Configurando las opciones del menu y las acciones que harán al hacerse click
        switch (id){

            case R.id.opcion2:
                Intent goHomefromMap = new Intent(Home.this,Map.class);
                startActivity(goHomefromMap);
                break;
            case R.id.opcion3:
                Intent goTusSitiosfromMap = new Intent(Home.this,Sitios.class);
                startActivity(goTusSitiosfromMap);
                break;
            case R.id.opcion4:
                Intent goAcercadefromMap = new Intent(Home.this,Acerca_de.class);
                startActivity(goAcercadefromMap);
                break;
            case R.id.opcion5:
                Intent goConfiguracionfromMap = new Intent(Home.this,Configuracion.class);
                startActivity(goConfiguracionfromMap);
                break;
            case R.id.opcion6:
                AlertDialog.Builder Alert_CerrarSesion = new AlertDialog.Builder(Home.this);
                Alert_CerrarSesion.setTitle("Cerrar Sesión");
                Alert_CerrarSesion.setMessage("¿Esta seguro que desea cerrar sesión?");
                Alert_CerrarSesion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth autenticacion = FirebaseAuth.getInstance();
                        autenticacion.signOut();
                        Intent goLogin = new Intent(Home.this, Login.class);
                        startActivity(goLogin);
                    }
                });
                Alert_CerrarSesion.show();
                break;
            default:
        }


        /*int id = item.getItemId();

        // Configurando la acción de cada uno de los items del menu_home
        if(id==R.id.opcion2){
            Intent goSitios = new Intent(Home.this, Sitios.class);
            startActivity(goSitios);
        }else if(id==R.id.opcion3){
            Intent goMap = new Intent(Home.this, Map.class);
            startActivity(goMap);
        }else if(id==R.id.opcion4){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opcion5) {
            AlertDialog.Builder miDialogo = new AlertDialog.Builder(Home.this);
            miDialogo.setTitle("Nuevo Sitio");
            miDialogo.setMessage("Ingrese consumo");
            miDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goHome = new Intent(Home.this, Map.class);
                    startActivity(goHome);
                }
            });
        miDialogo.show();
        }else{
            Toast.makeText(this,R.string.main_menu_opcion4,Toast.LENGTH_LONG).show();
        }*/

        return true;

    }



}