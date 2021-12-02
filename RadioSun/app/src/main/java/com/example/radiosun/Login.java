package com.example.radiosun;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private FirebaseAuth autenticacionFirebase;
    private String email;
    private String clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autenticacionFirebase = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        Button btn_ingresar_login = (Button) findViewById(R.id.login_btningresar);
        EditText txtEmail = (EditText) findViewById(R.id.login_txvEmail);
        EditText txtContrasena = (EditText) findViewById(R.id.login_txvContrasena);
        Button btnRegistro = (Button) findViewById(R.id.btn_registrarse_login);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos(txtEmail,txtContrasena)) {
                    autenticacionFirebase.createUserWithEmailAndPassword(email, clave).addOnCompleteListener((Activity) v.getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                irMenu(email);
                            } else {
                                verMensaje("Ocurrió un error al registrar el usuario");
                            }
                        }
                    });


                /*Intent goRegister = new Intent(Login.this, RegistrarSitio.class);
                startActivity(goRegister);*/
                }else{
                    verMensaje("Diligencie todos los campos");
                }
            }

        });



        btn_ingresar_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Validación de Ingreso de datos para entrar en la app.
                /*
                AlertDialog.Builder message = new AlertDialog.Builder(view.getContext()); // Crear la clase alertdialog
                String email = txtEmail.getText().toString();
                String contrasena = txtContrasena.getText().toString();
               if (email.isEmpty() || contrasena.isEmpty() ){

                    message.setTitle(R.string.alerta_error); // asignamos el titulo del alertdialog
                    message.setMessage(R.string.btn_registrarse_login_message); // asignamos el mensaje por medio del string
                    message.create(); // creamos el mensaje
                    message.show(); // mostramos el mensaje

                }else{

                    message.setTitle(R.string.alerta_exito); // asignamos el titulo del alertdialog
                    message.setMessage(R.string.btn_registrarse_login_message_exito); // asignamos el mensaje por medio del string
                    message.create(); // creamos el mensaje
                    message.show(); // mostramos el mensaje
                }
                */
                Intent goHome = new Intent(Login.this, Home.class);
                startActivity(goHome);


            }
        });

    }


    // Validar datos

    private boolean validarCampos(EditText email, EditText clave){

        boolean camposDiligenciados = false;
        this.email = email.getText().toString();
        this.clave = clave.getText().toString();

        if(!this.email.isEmpty() && !this.clave.isEmpty()){
            camposDiligenciados = true;
        }

        return camposDiligenciados;
    }

    private void verMensaje(String cuerpo){
        AlertDialog.Builder msj = new AlertDialog.Builder(this);
        msj.setMessage(cuerpo);
        msj.show();
    }

    private void irMenu(String email){
        Intent goHome = new Intent(this, MainActivity.class);
        goHome.putExtra("email",email);
        startActivity(goHome);
    }
}