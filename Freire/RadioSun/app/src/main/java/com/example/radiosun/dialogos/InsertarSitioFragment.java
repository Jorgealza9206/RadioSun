package com.example.radiosun.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radiosun.R;
import com.example.radiosun.Sitios;
import com.example.radiosun.clases.Mensajes;
import com.example.radiosun.clases.dao.SitioDAO;
import com.example.radiosun.modelos.Sitio;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarSitioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarSitioFragment extends DialogFragment {

    Activity actividad;
    Button btnAgregar, btnCancelar;
    LinearLayout barraSuperior;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private double mParam1;
    private double mParam2;


    public InsertarSitioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertarSitioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertarSitioFragment newInstance(Double param1, Double param2) {
        InsertarSitioFragment fragment = new InsertarSitioFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_PARAM1, param1);
        args.putDouble(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam1 = getArguments().getDouble(ARG_PARAM1);
            mParam2 = getArguments().getDouble(ARG_PARAM2);
        }
    }

  /*  @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoInsertarSitio();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        View v = inflater.inflate(R.layout.fragment_insertar_sitio,container, false);
        //builder.setView(v);

        TextView txtLatitud = (TextView) v.findViewById(R.id.insertarsitio_coordenada_latitude);
        TextView txtLongitud = (TextView) v.findViewById(R.id.insertarsitio_coordenada_longitud);

        EditText txtNombre = (EditText) v.findViewById(R.id.registro_nombresitio);
        //TextView txtCoordenadas = (TextView) findViewById(R.id.registrositio_coordenadas);
        EditText txtRadiacion = (EditText) v.findViewById(R.id.registrositio_radiacion);
        EditText txtMes1 = (EditText) v.findViewById(R.id.registro_sitio_txvMes1);
        EditText txtMes2 = (EditText) v.findViewById(R.id.registro_sitio_txvMes2);
        EditText txtMes3 = (EditText) v.findViewById(R.id.registro_sitio_txvMes3);
        EditText txtMes4 = (EditText) v.findViewById(R.id.registro_sitio_txvMes4);
        EditText txtMes5 = (EditText) v.findViewById(R.id.registro_sitio_txvMes5);
        EditText txtMes6 = (EditText) v.findViewById(R.id.registro_sitio_txvMes6);
        TextView txtPaneles = (TextView) v.findViewById(R.id.registrositio_Respuesta);

        if(getArguments()!=null)
        {
            txtLatitud.setText(String.valueOf(mParam1));
            txtLongitud.setText(String.valueOf(mParam2));

        }


        btnAgregar = (Button) v.findViewById(R.id.insertarsitio_btnagregar);
        btnCancelar = (Button) v.findViewById(R.id.insertarsitio_btncancelar);

        barraSuperior = (LinearLayout) v.findViewById(R.id.insertarsitio_barrasuperior);

        eventoBotones();

        Spinner desplegable = (Spinner) v.findViewById(R.id.registro_sitioDespegable);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.potencia_paneles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desplegable.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camposVacios(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6)){
                    new Mensajes(view.getContext()).toast("Digite los datos en blanco");
                }else{
                    long id = insertar(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6,
                            desplegable, txtLatitud,txtLongitud,v);
                    new Mensajes(view.getContext()).toast("Se ha agregado el registro correctamente " + String.valueOf(id));
                    txtPaneles.setText(paneles(txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6,
                            desplegable));
                    Intent goTusSitiosfromMap = new Intent(getContext(), Sitios.class);
                    startActivity(goTusSitiosfromMap);
                }
            }
        });



        return v;

    }

    /*
    private AlertDialog crearDialogoInsertarSitio() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_insertar_sitio, null);
        builder.setView(v);

        TextView txtLatitud = (TextView) v.findViewById(R.id.insertarsitio_coordenada_latitude);
        TextView txtLongitud = (TextView) v.findViewById(R.id.insertarsitio_coordenada_longitud);

        EditText txtNombre = (EditText) v.findViewById(R.id.registro_nombresitio);
        //TextView txtCoordenadas = (TextView) findViewById(R.id.registrositio_coordenadas);
        EditText txtRadiacion = (EditText) v.findViewById(R.id.registrositio_radiacion);
        EditText txtMes1 = (EditText) v.findViewById(R.id.registro_sitio_txvMes1);
        EditText txtMes2 = (EditText) v.findViewById(R.id.registro_sitio_txvMes2);
        EditText txtMes3 = (EditText) v.findViewById(R.id.registro_sitio_txvMes3);
        EditText txtMes4 = (EditText) v.findViewById(R.id.registro_sitio_txvMes4);
        EditText txtMes5 = (EditText) v.findViewById(R.id.registro_sitio_txvMes5);
        EditText txtMes6 = (EditText) v.findViewById(R.id.registro_sitio_txvMes6);
        TextView txtPaneles = (TextView) v.findViewById(R.id.registrositio_Respuesta);




        btnAgregar = (Button) v.findViewById(R.id.insertarsitio_btnagregar);
        btnCancelar = (Button) v.findViewById(R.id.insertarsitio_btncancelar);

        barraSuperior = (LinearLayout) v.findViewById(R.id.insertarsitio_barrasuperior);

        eventoBotones();

        Spinner desplegable = (Spinner) v.findViewById(R.id.registro_sitioDespegable);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.potencia_paneles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desplegable.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camposVacios(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6)){
                    new Mensajes(view.getContext()).toast("Digite los datos en blanco");
                }else{
                    long id = insertar(txtNombre,txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6,
                            desplegable, txtLatitud,txtLongitud);
                    new Mensajes(view.getContext()).toast("Se ha agregado el registro correctamente " + String.valueOf(id));
                    txtPaneles.setText(paneles(txtRadiacion,txtMes1,txtMes2,txtMes3,txtMes4,txtMes5,txtMes6,
                            desplegable));
                    Intent goTusSitiosfromMap = new Intent(getContext(), Sitios.class);
                    startActivity(goTusSitiosfromMap);
                }
            }
        });


        return builder.create();
    }
*/


    private void eventoBotones() {



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Boton Cancelar",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
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
                          EditText mes4, EditText mes5, EditText mes6, Spinner sp_panel){

        double r = Double.parseDouble(radiacion.getText().toString());
        double m1 = Double.parseDouble(mes1.getText().toString());
        double m2 = Double.parseDouble(mes2.getText().toString());
        double m3 = Double.parseDouble(mes3.getText().toString());
        double m4 = Double.parseDouble(mes4.getText().toString());
        double m5 = Double.parseDouble(mes5.getText().toString());
        double m6 = Double.parseDouble(mes6.getText().toString());
        double p = Double.parseDouble((String) sp_panel.getSelectedItem());

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


    private long insertar(EditText nombres, EditText radiacion, EditText mes1, EditText mes2,
                          EditText mes3, EditText mes4, EditText mes5, EditText mes6,
                          Spinner sp_panel,TextView latitud, TextView longitud, View vista){
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
        sit.setP_panel(Double.parseDouble((String) sp_panel.getSelectedItem()));
        sit.setN_panel(Double.parseDouble(paneles(radiacion, mes1, mes2, mes3, mes4, mes5,
                mes6, sp_panel)));

        sit.setLatitud(Double.parseDouble(latitud.getText().toString()));
        sit.setLongitud(Double.parseDouble(longitud.getText().toString()));

        SitioDAO stdao = new SitioDAO(vista.getContext());

        id = stdao.insertar(sit);

        ArrayList<Sitio> sitios = stdao.listar(null);

        return id;
    }

    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad = (Activity) context;
        }else{
            throw new RuntimeException(context.toString()
            +" must implement OnFragmentInteractionListener");
        }
    }*/


}