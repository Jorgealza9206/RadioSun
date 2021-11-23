package com.example.radiosun;

import static com.example.radiosun.R.id;
import static com.example.radiosun.R.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.radiosun.clases.Mensajes;

public class RegistrarSitio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_registrarsitio);

        EditText txtNombre = (EditText) findViewById(R.id.registro_nombresitio);
        //TextView txtCoordenadas = (TextView) findViewById(R.id.registrositio_coordenadas);
        EditText txtRadiacion = (EditText) findViewById(R.id.registrositio_radiacion);
        //RadioGroup rgConsumo = (RadioGroup) findViewById(id.registro_sitio_radioGroup);
        //RadioButton rbInterconectado = (RadioButton) findViewById(id.registro_sitio_radioButtonInterconectado);
        //RadioButton rbAutonomo = (RadioButton) findViewById(id.registro_sitio_radioButtonAutonomo);
        EditText txtMes1 = (EditText) findViewById(R.id.registro_sitio_txvMes1);
        EditText txtMes2 = (EditText) findViewById(R.id.registro_sitio_txvMes2);
        EditText txtMes3 = (EditText) findViewById(R.id.registro_sitio_txvMes3);
        EditText txtMes4 = (EditText) findViewById(R.id.registro_sitio_txvMes4);
        EditText txtMes5 = (EditText) findViewById(R.id.registro_sitio_txvMes5);
        EditText txtMes6 = (EditText) findViewById(R.id.registro_sitio_txvMes6);
        Button btnInsertar = (Button) findViewById(id.registrositio_btnconsumo);
        TextView txtPaneles = (TextView) findViewById(id.registrositio_Respuesta);
        Spinner desplegable = (Spinner) findViewById(id.registro_sitioDespegable);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.potencia_paneles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desplegable.setAdapter(adapter);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camposVacios(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6)){
                    new Mensajes(view.getContext()).toast("Digite los datos en blanco");
                }else{
                    //long id = insertar(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6);
                    new Mensajes(view.getContext()).toast("Se ha agregado el registro correctamente"/*+String.valueOf(id)*/);
                    txtPaneles.setText(paneles(txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6));
                }
            }
        }

        );
    }

    public boolean camposVacios(EditText nombres, EditText radiacion, EditText mes1, EditText mes2,
                                EditText mes3, EditText mes4, EditText mes5, EditText mes6){

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

    public String paneles(EditText radiacion,EditText mes1, EditText mes2, EditText mes3,
                            EditText mes4, EditText mes5, EditText mes6){

        double r = Double.parseDouble(radiacion.getText().toString());
        double m1 = Double.parseDouble(mes1.getText().toString());
        double m2 = Double.parseDouble(mes2.getText().toString());
        double m3 = Double.parseDouble(mes3.getText().toString());
        double m4 = Double.parseDouble(mes4.getText().toString());
        double m5 = Double.parseDouble(mes5.getText().toString());
        double m6 = Double.parseDouble(mes6.getText().toString());
        //double p = Double.parseDouble(panel.toString());

        double[] consumo = {m1,m2,m3,m4,m5,m6};
        double maximo = consumo[0];

        for(int i=0;i<6;i++){
            if(maximo<consumo[i]){
                maximo = consumo[i];
            }
        }
        maximo = (((maximo/30.0)/r)*1000.0)/445.0;
        maximo = Math.ceil(maximo);

        return String.valueOf(maximo);

    }

    /*private long insertar(@NonNull EditText nombres, @NonNull EditText radiacion, @NonNull EditText mes1, @NonNull EditText mes2,
                          @NonNull EditText mes3, @NonNull EditText mes4, @NonNull EditText mes5, @NonNull EditText mes6){
        long id = 0;

        Sitio sit = new Sitio();
        sit.setNombre(nombres.getText().toString());
        sit.setRadiacion(Double.parseDouble(radiacion.getText().toString()));
        sit.setMes_1(Double.parseDouble(mes1.getText().toString()));
        sit.setMes_2(Double.parseDouble(mes2.getText().toString()));
        sit.setMes_3(Double.parseDouble(mes3.getText().toString()));
        sit.setMes_4(Double.parseDouble(mes4.getText().toString()));
        sit.setMes_5(Double.parseDouble(mes5.getText().toString()));
        sit.setMes_6(Double.parseDouble(mes6.getText().toString()));

        SitioDAO stdao = new SitioDAO(this);

        id = stdao.Insertar(sit);

        return id;
    }*/

}
