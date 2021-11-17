package com.example.radiosun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(home_toolbar);

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
            Toast.makeText(this,R.string.main_menu_opcion1,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opc2){
            Toast.makeText(this,R.string.main_menu_opcion2,Toast.LENGTH_LONG).show();
        }else if(id==R.id.opc3){
            Toast.makeText(this,R.string.main_menu_opcion3,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,R.string.main_menu_opcion4,Toast.LENGTH_LONG).show();
        }

        return true;

    }



}