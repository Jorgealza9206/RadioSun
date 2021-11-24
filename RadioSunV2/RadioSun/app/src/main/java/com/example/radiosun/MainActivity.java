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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(home_toolbar);

        ImageButton TusSitios = (ImageButton) findViewById(R.id.btn_missitios);
        ImageButton Mapas = (ImageButton) findViewById(R.id.home_button_Maps);

        TusSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSitios = new Intent(MainActivity.this, Sitios.class);
                startActivity(goSitios);
            }
        });

        Mapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMap = new Intent(MainActivity.this, Map.class);
                startActivity(goMap);
            }
        });

    }


    // Comportamiento del action Bar del menu home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.opc1){
            Intent goSitios = new Intent(MainActivity.this, Sitios.class);
            startActivity(goSitios);
        }else if(id==R.id.opc2){
            Intent goMap = new Intent(MainActivity.this, Map.class);
            startActivity(goMap);
        }else if(id==R.id.opc3){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opc5) {
            AlertDialog.Builder miDialogo = new AlertDialog.Builder(MainActivity.this);
            miDialogo.setTitle("Nuevo Sitio");
            miDialogo.setMessage("Ingrese consumo");
            miDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goHome = new Intent(MainActivity.this, Map.class);
                    startActivity(goHome);
                }
            });
        miDialogo.show();
        }else{
            Toast.makeText(this,R.string.main_menu_opcion4,Toast.LENGTH_LONG).show();
        }

        return true;

    }



}