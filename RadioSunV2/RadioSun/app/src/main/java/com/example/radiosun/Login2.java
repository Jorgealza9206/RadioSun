package com.example.radiosun;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login2 extends AppCompatActivity {

    private FirebaseAuth autenticacionFirebase;
    private EditText txtEmail;
    private EditText txtClave;
    private Button btnIngresar = (Button) findViewById(R.id.login2_btnIngresar);
    private Button btnRegistrarse = (Button) findViewById(R.id.login2_btnRegistrarse);
    private String email = "";
    private String clave = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
/*
        FirebaseUser usuario = autenticacionFirebase.getCurrentUser();

        if(usuario!=null)
            irMenu(usuario.getEmail());

        setContentView(R.layout.activity_login);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()) {
                    //FirebaseAuth autenticacionFirebase;
                    autenticacionFirebase.createUserWithEmailAndPassword(email, clave).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                irMenu(email);
                            else
                                verMensaje("Ocurrió un error");
                        }
                    });
                }
                else
                    verMensaje("Diligencie los campos vacíos");
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos())
                    autenticacionFirebase.signInWithEmailAndPassword(email, clave).
                            addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                        irMenu(email);
                                    else
                                        verMensaje("No se encontro un usuario con el email ingresado.");
                                }
                            });
            }
        });

*/
    }
/*
    private boolean validarCampos()
    {
        boolean camposDiligenciados = false;

        EditText txtEmail = (EditText) findViewById(R.id.login2_txtEmail);
        EditText txtClave = (EditText) findViewById(R.id.login2_txtClave);

        if(!txtEmail.getText().toString().isEmpty() && !txtClave.getText().toString().isEmpty())
        {
            this.email = txtEmail.getText().toString();
            this.clave = txtClave.getText().toString();
            camposDiligenciados=true;
        }

        return camposDiligenciados;
    }


    private void verMensaje(String cuerpo)
    {
        AlertDialog.Builder msj = new AlertDialog.Builder(this);
        msj.setMessage(cuerpo);
        msj.create();
        msj.show();
    }

    private void irMenu(String emeail){
        Intent i = new Intent(this, Login2);
        i.putExtra("email", email);
        startActivity(i);
    }

*/

}