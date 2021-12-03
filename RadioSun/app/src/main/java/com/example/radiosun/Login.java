package com.example.radiosun;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class Login extends AppCompatActivity {

    private FirebaseAuth autenticacionFirebase;
    private static  final int RC_SIGN_IN = 9001;
    private String email;
    private String clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autenticacionFirebase = FirebaseAuth.getInstance();

        FirebaseUser usuario = autenticacionFirebase.getCurrentUser();
        if(usuario != null){
            irMenu(usuario.getEmail());
        }

        setContentView(R.layout.activity_login);

        Button btn_ingresar_login = (Button) findViewById(R.id.login_btningresar);
        EditText txtEmail = (EditText) findViewById(R.id.editTextTextEmail);
        EditText txtContrasena = (EditText) findViewById(R.id.editTextTextClave);
        Button btnRegistro = (Button) findViewById(R.id.btn_registrarse_registrarUsuario);
        TextView restablecer = (TextView) findViewById(R.id.link_restablecerClave);
        ImageButton btnGoogle = (ImageButton) findViewById(R.id.login_google);
        /*restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restablecerClave.newInstance("mParam1","mParam2").show(getSupportFragmentManager(),null);
            }
        });*/

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.llave_cliente_google))
                        .requestEmail()
                        .build();
                Intent signIntent = GoogleSignIn.getClient(v.getContext(),gso).getSignInIntent();
                startActivityForResult(signIntent, RC_SIGN_IN);



            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (validarCampos(txtEmail, txtContrasena)) {
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



                }else{
                    verMensaje("Diligencie todos los campos");
                }*/
                Intent goRegister = new Intent(Login.this, RegistrarUsuario.class);
                startActivity(goRegister);
                }

        });



        btn_ingresar_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(validarCampos(txtEmail,txtContrasena)){
                    autenticacionFirebase.signInWithEmailAndPassword(email,clave).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                irMenu(email);
                            }else verMensaje("Usuario no registrado");
                        }
                    });
                }else verMensaje("Ingrese sus datos");
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
                /*Intent goHome = new Intent(Login.this, Home.class);
                startActivity(goHome);*/


            }
        });

    }


    // Validar datos


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Activity ActivityActual = this;

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount cuenta = task.getResult(ApiException.class);
                AuthCredential credencial  = GoogleAuthProvider.getCredential(cuenta.getIdToken(),null);
                autenticacionFirebase.signInWithCredential(credencial).addOnCompleteListener(ActivityActual, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           FirebaseUser usuario = autenticacionFirebase.getCurrentUser();

                           irMenu(usuario.getEmail());

                       }
                    }
                });
            }
            catch (Exception ex){

            }
        }

    }

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
        Toast.makeText(this, cuerpo, Toast.LENGTH_LONG).show();;
    }

    private void irMenu(String email){
        Intent goHome = new Intent(this, Home.class);
        goHome.putExtra("email",email);
        startActivity(goHome);
    }
}