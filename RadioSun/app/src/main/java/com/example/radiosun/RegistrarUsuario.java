package com.example.radiosun;

import static com.example.radiosun.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrarUsuario extends AppCompatActivity {

    private FirebaseAuth autenticacionFirebase;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private String clave2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);
        autenticacionFirebase = FirebaseAuth.getInstance();
        EditText txvNombre = (EditText) findViewById(id.editTextTextPersonName);
        EditText txvApellido = (EditText) findViewById(id.editTextTextPersonName2);
        EditText txvEmail = (EditText) findViewById(id.editTextTextEmail);
        EditText txvClave = (EditText) findViewById(id.editTextTextClave);
        EditText txvClave2 = (EditText) findViewById(id.editTextTextClave2);
        ProgressBar progress = (ProgressBar) findViewById(id.progressBar);
        Button btnRegistrar = (Button) findViewById(id.btn_registrarse_registrarUsuario);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos(txvNombre,txvApellido,txvEmail,txvClave,txvClave2)){
                    if(comprobarClave(txvClave,txvClave2)){
                        autenticacionFirebase.createUserWithEmailAndPassword(email,clave).addOnCompleteListener((Activity) v.getContext(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            verMensaje("Usuario creado exitosamente");
                                            irLogin(email);
                                        }else verMensaje("Ocurrió un error inesperado");
                                    }
                                }
                        );
                    }else verMensaje("Las contraseñas deben ser iguales");
                }else verMensaje("Ingrese todos los campos");
            }
        });
    }

    private boolean validarCampos(EditText nombre, EditText apellido, EditText email, EditText clave,
                                  EditText clave2){

        boolean camposDiligenciados = false;
        this.nombre = nombre.getText().toString();
        this.apellido = apellido.getText().toString();
        this.email = email.getText().toString();
        this.clave = clave.getText().toString();
        this.clave2 = clave2.getText().toString();

        if(!this.nombre.isEmpty() && !this.apellido.isEmpty() && !this.email.isEmpty() && !this.clave.isEmpty() && !this.clave2.isEmpty()){
            camposDiligenciados = true;
        }

        return camposDiligenciados;
    }

    private boolean comprobarClave( EditText clave, EditText clave2){

        boolean cert = false;
        this.clave = clave.getText().toString();
        this.clave2 = clave2.getText().toString();

        if(this.clave.contentEquals(this.clave2)){
            cert = true;
        }

        return cert;
    }

    private void irLogin(String email){
        Intent goLogin = new Intent(this, Login.class);
        goLogin.putExtra("email",email);
        startActivity(goLogin);
    }

    private void verMensaje(String cuerpo){
        Toast.makeText(this, cuerpo, Toast.LENGTH_LONG).show();
    }
}