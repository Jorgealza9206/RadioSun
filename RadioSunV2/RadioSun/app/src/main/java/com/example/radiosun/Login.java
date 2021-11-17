package com.example.radiosun;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_ingresar_login = (Button) findViewById(R.id.login_btningresar);
        EditText txtEmail = (EditText) findViewById(R.id.login_txvEmail);
        EditText txtContrasena = (EditText) findViewById(R.id.login_txvContrasena);
        Button btnRegistro = (Button) findViewById(R.id.btn_registrarse_login);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegister = new Intent(Login.this, RegistrarSitio.class);
                startActivity(goRegister);
            }
        });



        btn_ingresar_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder message = new AlertDialog.Builder(view.getContext()); // Crear la clase alertdialog
                String email = txtEmail.getText().toString();
                String contrasena = txtContrasena.getText().toString();
               /* if (email.isEmpty() || contrasena.isEmpty() ){

                    message.setTitle(R.string.alerta_error); // asignamos el titulo del alertdialog
                    message.setMessage(R.string.btn_registrarse_login_message); // asignamos el mensaje por medio del string
                    message.create(); // creamos el mensaje
                    message.show(); // mostramos el mensaje

                }else{

                    message.setTitle(R.string.alerta_exito); // asignamos el titulo del alertdialog
                    message.setMessage(R.string.btn_registrarse_login_message_exito); // asignamos el mensaje por medio del string
                    message.create(); // creamos el mensaje
                    message.show(); // mostramos el mensaje
                }*/

                Intent goHome = new Intent(Login.this,MainActivity.class);
                startActivity(goHome);


            }
        });

    }
}