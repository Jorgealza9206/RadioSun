package com.example.radiosun;

import static com.example.radiosun.R.id;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class RegistrarUsuario extends AppCompatActivity {

    private FirebaseAuth autenticacionFirebase;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);


    }
}