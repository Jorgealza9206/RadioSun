package com.example.radiosun.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiosun.R;
import com.example.radiosun.Sitios;
import com.example.radiosun.clases.Mensajes;
import com.example.radiosun.clases.dao.SitioDAO;
import com.example.radiosun.modelos.Sitio;

import java.util.ArrayList;

public class AdapterSitios extends RecyclerView.Adapter<AdapterSitios.ViewHolderDatos>{



    //Crear lista de objetos de tipo sitios

    ArrayList<Sitio> listaSitios;

    //Generando constructor de la lista de Datos


    public AdapterSitios(ArrayList<Sitio> l) {
        this.listaSitios = l;
    }


    @NonNull
    @Override
    public AdapterSitios.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitio_item,null,false);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.cargarDatos(listaSitios.get(position));

    }

    @Override
    public int getItemCount() {
        //retorna el tamaño de la lista que generemos
        return this.listaSitios.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        //Referencia a lo que quiero mostrar en el recycler

        //TextView dato;
        int id;
        TextView txv_nombre, txv_radiación, txv_latitud, txv_longitud;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //Asigno la referencia

            txv_nombre = (TextView) itemView.findViewById(R.id.sitio_idnombre);
            txv_radiación = (TextView) itemView.findViewById(R.id.sitio_radiacion);

            txv_latitud = (TextView) itemView.findViewById(R.id.sitio_latitud);
            txv_longitud = (TextView) itemView.findViewById(R.id.sitio_longitud);

            ImageButton btneditar = (ImageButton) itemView.findViewById(R.id.sitio_btneditar);
            ImageButton btneliminar = (ImageButton) itemView.findViewById(R.id.sitio_btneliminar);
            ImageView imagen_sitio = (ImageView) itemView.findViewById(R.id.sitio_image);

            btneliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
                    mensaje.setTitle("Advertencia");
                    mensaje.setMessage("Está a punto de eliminar uno de sus sitios");
                    mensaje.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SitioDAO db = new SitioDAO(view.getContext());
                            if(db.eliminar(id))
                                new Mensajes(view.getContext()).alerta("Sitio Eliminado","Se ha eliminado el sitio");
                            else
                                new Mensajes(view.getContext()).alerta("Error","El sitio no ha logrado eliminarse");

                            //Recrear el activty
                            ((Sitios) view.getContext()).recreate();

                        }
                    });

                    mensaje.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new Mensajes(view.getContext()).alerta("Error","Se ha producido un error");
                        }
                    });
                    mensaje.create();
                    mensaje.show();
                }
            });

        }

        public void cargarDatos(Sitio sitio)
        {

            txv_nombre.setText(sitio.getNombre());
            txv_radiación.setText("Radiación: " + String.valueOf(sitio.getRadiacion()) + " horas");
            //Establecer Coordenadas
            if(sitio.getLatitud()>=0){
                txv_latitud.setText(String.valueOf(Math.round(sitio.getLatitud()*10000.0)/10000.0) + " N" );
            }else{
                txv_latitud.setText(String.valueOf(Math.round(sitio.getLatitud()*-10000.0)/10000.0) + " S" );
            }
            if(sitio.getLongitud()>=0){
                txv_longitud.setText(String.valueOf(Math.round(sitio.getLongitud()*10000.0)/10000.0) + " E");
            }else{
                txv_longitud.setText(String.valueOf(Math.round(sitio.getLongitud()*-10000.0)/10000.0) + " W");
            }


            id = sitio.getId();
        }
    }
}
