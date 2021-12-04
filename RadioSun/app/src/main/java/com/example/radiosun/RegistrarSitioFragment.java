package com.example.radiosun;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.radiosun.clases.Mensajes;
import com.example.radiosun.clases.dao.SitioDAO;
import com.example.radiosun.modelos.Sitio;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarSitioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarSitioFragment extends DialogFragment {

    FirebaseDatabase database;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private double mParam1;
    private double mParam2;

    public RegistrarSitioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarSitioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarSitioFragment newInstance(Double param1, Double param2) {
        RegistrarSitioFragment fragment = new RegistrarSitioFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_PARAM1, param1);
        args.putDouble(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getDouble(ARG_PARAM1);
            mParam2 = getArguments().getDouble(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_registrar_sitio, container, false);

        //Código copiado del Activity

        EditText txtNombre = (EditText) vista.findViewById(R.id.registro_nombresitio);
        TextView txtLatitud = (TextView) vista.findViewById(R.id.registrositio_coordenadas_latitud);
        TextView txtLongitud = (TextView) vista.findViewById(R.id.registrositio_coordenadas_longitud);
        EditText txtRadiacion = (EditText) vista.findViewById(R.id.registrositio_radiacion);
        //RadioGroup rgConsumo = (RadioGroup) findViewById(id.registro_sitio_radioGroup);
        //RadioButton rbInterconectado = (RadioButton) findViewById(id.registro_sitio_radioButtonInterconectado);
        //RadioButton rbAutonomo = (RadioButton) findViewById(id.registro_sitio_radioButtonAutonomo);
        EditText txtMes1 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes1);
        EditText txtMes2 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes2);
        EditText txtMes3 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes3);
        EditText txtMes4 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes4);
        EditText txtMes5 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes5);
        EditText txtMes6 = (EditText) vista.findViewById(R.id.registro_sitio_txvMes6);
        Button btnInsertar = (Button) vista.findViewById(R.id.registrositio_btnconsumo);
        TextView txtPaneles = (TextView) vista.findViewById(R.id.registrositio_Respuesta);
        EditText desplegable = (EditText) vista.findViewById(R.id.registro_sitioDespegable);

        Button btnCancelar = (Button) vista.findViewById(R.id.registrositio_btncancelar);

        if(getArguments()!=null)
        {
         txtLatitud.setText(String.valueOf(mParam1));
         txtLongitud.setText(String.valueOf(mParam2));
        }


        btnInsertar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(camposVacios(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6)){
                   new Mensajes(view.getContext()).toast("Digite los datos en blanco");
               }else{
                   long id = insertar2(txtNombre, txtLatitud, txtLongitud, txtRadiacion,txtMes1,txtMes2,txtMes3,
                           txtMes4,txtMes5,txtMes6,desplegable,vista);
                   new Mensajes(view.getContext()).toast("Se ha agregado el registro correctamente " + String.valueOf(id));
                   //onBackPressed();
                   txtPaneles.setText(paneles(txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6,
                           desplegable));
                   Intent gotoSitios = new Intent(getContext(),Sitios.class);
                   startActivity(gotoSitios);
               }
           }
       }

);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        return vista;
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
                          EditText mes4, EditText mes5, EditText mes6, EditText sp_panel){

        double r = Double.parseDouble(radiacion.getText().toString());
        double m1 = Double.parseDouble(mes1.getText().toString());
        double m2 = Double.parseDouble(mes2.getText().toString());
        double m3 = Double.parseDouble(mes3.getText().toString());
        double m4 = Double.parseDouble(mes4.getText().toString());
        double m5 = Double.parseDouble(mes5.getText().toString());
        double m6 = Double.parseDouble(mes6.getText().toString());
        double p = Double.parseDouble(sp_panel.getText().toString());

        double[] consumo = {m1,m2,m3,m4,m5,m6};
        double maximo = consumo[0];

        for(int i=0;i<6;i++){
            if(maximo<consumo[i]){
                maximo = consumo[i];
            }
        }
        maximo = (((maximo/30.0)/r)*1000.0)/p;
        maximo = Math.ceil(maximo);

        return String.valueOf(maximo);

    }

    private long insertar2(EditText nombres, TextView latitud, TextView longitud, EditText radiacion,
                          EditText mes1, EditText mes2, EditText mes3, EditText mes4, EditText mes5,
                          EditText mes6, EditText sp_panel, View vista){
        long id = 0;

        Sitio sit = new Sitio();
        sit.setNombre(nombres.getText().toString());
        sit.setLatitud(Double.parseDouble(latitud.getText().toString()));
        sit.setLongitud(Double.parseDouble(longitud.getText().toString()));
        sit.setRadiacion(Double.parseDouble(radiacion.getText().toString()));
        sit.setMes_1(Double.parseDouble(mes1.getText().toString()));
        sit.setMes_2(Double.parseDouble(mes2.getText().toString()));
        sit.setMes_3(Double.parseDouble(mes3.getText().toString()));
        sit.setMes_4(Double.parseDouble(mes4.getText().toString()));
        sit.setMes_5(Double.parseDouble(mes5.getText().toString()));
        sit.setMes_6(Double.parseDouble(mes6.getText().toString()));
        sit.setP_panel(Double.parseDouble(sp_panel.getText().toString()));
        sit.setN_panel(Double.parseDouble(paneles(radiacion, mes1, mes2, mes3, mes4, mes5,
                mes6, sp_panel)));

        //Insertar en SQLite
        SitioDAO stdao;
        stdao = new SitioDAO(vista.getContext());

        id = stdao.Insertar(sit);

        sit.setId((int)id);

        ArrayList<Sitio> sitios= stdao.listar(null);

        //Insertar en Firebase Real-Time

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        database.getReference().child("Sitio").child(UUID.randomUUID().toString()).setValue(sit);

        //Autenticación



        return id;
    }
}