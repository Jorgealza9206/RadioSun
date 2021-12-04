package com.example.radiosun;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link restablecerClave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class restablecerClave extends DialogFragment {

    public restablecerClave() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static restablecerClave newInstance() {
        restablecerClave fragment = new restablecerClave();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_restablecer_clave, container, false);

        EditText email = (EditText) vista.findViewById(R.id.editTextRestablecer);
        TextView confirmar = (TextView) vista.findViewById(R.id.btn_restablecerAceptar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString());
                dismiss();
            }
        });

        return vista;
    }
}