package com.example.radiosun.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

public class Mensajes {
    private Context contexto;

    public Mensajes(Context contexto) {
        this.contexto = contexto;
    }

    public void alerta(String titulo, String cuerpo)
    {
        AlertDialog.Builder msj = new AlertDialog.Builder(this.contexto);
        msj.setTitle(titulo);
        msj.setMessage(cuerpo);
        msj.create();
        msj.show();
    }

    public void confirmacion(String titulo, String cuerpo)
    {
        AlertDialog.Builder msj = new AlertDialog.Builder(this.contexto);
        msj.setTitle(titulo);
        msj.setMessage(cuerpo);
        msj.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new Mensajes(contexto).alerta("Eleccion", "Pulsaste el boton Si");
            }
        });
        msj.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new Mensajes(contexto).alerta("Eleccion", "Pulsaste el boton No");
            }
        });
        msj.create();
        msj.show();
    }

    public void toast(String cuerpo)
    {
        Toast.makeText(this.contexto, cuerpo, Toast.LENGTH_LONG).show();
    }

    public void snackbar(View vista, String cuerpo)
    {
        Snackbar.make(vista, cuerpo, Snackbar.LENGTH_LONG).show();
    }
}
