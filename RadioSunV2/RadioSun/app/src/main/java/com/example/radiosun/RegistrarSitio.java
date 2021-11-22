package com.example.radiosun;

import static com.example.radiosun.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegistrarSitio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_registrarsitio);

        EditText txtNombre = (EditText) findViewById(R.id.registro_nombresitio);
        TextView txtCoordenadas = (TextView) findViewById(R.id.registrositio_coordenadas);
        EditText txtRadiacion = (EditText) findViewById(R.id.registrositio_radiacion);
        RadioGroup rgConsumo = (RadioGroup) findViewById(id.registro_sitio_radioGroup);
        RadioButton rbInterconectado = (RadioButton) findViewById(id.registro_sitio_radioButtonInterconectado);
        RadioButton rbAutonomo = (RadioButton) findViewById(id.registro_sitio_radioButtonAutonomo);
        EditText txtMes1 = (EditText) findViewById(R.id.registro_sitio_txvMes1);
        EditText txtMes2 = (EditText) findViewById(R.id.registro_sitio_txvMes2);
        EditText txtMes3 = (EditText) findViewById(R.id.registro_sitio_txvMes3);
        EditText txtMes4 = (EditText) findViewById(R.id.registro_sitio_txvMes4);
        EditText txtMes5 = (EditText) findViewById(R.id.registro_sitio_txvMes5);
        EditText txtMes6 = (EditText) findViewById(R.id.registro_sitio_txvMes6);
        Button btnInsertar = (Button) findViewById(id.registrositio_btnconsumo);
        TextView txtPaneles = (TextView) findViewById(id.registrositio_paneles);
    }

    public boolean camposVacios(EditText nombres, EditText radiacion, EditText mes1, EditText mes2, EditText mes3, EditText mes4, EditText mes5, EditText mes6){

        boolean vacios = false;

        if (nombres.getText().toString().isEmpty() ||
                radiacion.getText().toString().isEmpty() ||
                mes1.getText().toString().isEmpty() ||
                mes2.getText().toString().isEmpty() ||
                mes3.getText().toString().isEmpty() ||
                mes4.getText().toString().isEmpty() ||
                mes5.getText().toString().isEmpty() ||
                mes6.getText().toString().isEmpty()){
                    vacios = true; }
        return vacios;
    }
}