package com.example.radiosun.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiosun.R;
import com.example.radiosun.clases.dao.SitioDAO;
import com.example.radiosun.modelos.Sitio;

import java.util.ArrayList;

public class AdapterSitios extends RecyclerView.Adapter<AdapterSitios.ViewHolderDatos>{


    //Crear una lista que va a recibir el recycler
    ArrayList<String> ListaDatos;

    //Crear lista de objetos de tipo sitios

    ArrayList<Sitio> listaSitios;

    //Generando constructor de la lista de Datos


   /* public AdapterSitios(ArrayList<String> listaDatos) {
        ListaDatos = listaDatos;
    } */

    public AdapterSitios(ArrayList<Sitio> listaSitios) {
        this.listaSitios = listaSitios;
    }


    @NonNull
    @Override
    public AdapterSitios.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.sitio_item,null,false);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSitios.ViewHolderDatos holder, int position) {
        //holder.asignarDatos(ListaDatos.get(position));
        holder.txv_nombre.setText(listaSitios.get(position).getNombre());
        holder.txv_radiación.setText("Radiación: " + String.valueOf(listaSitios.get(position).getRadiacion()) + " horas");


    }

    @Override
    public int getItemCount() {
        //retorna el tamaño de la lista que generemos
        return listaSitios.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        //Referencia a lo que quiero mostrar en el recycler

        //TextView dato;
        TextView txv_nombre, txv_radiación;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //Asigno la referencia
            //dato = itemView.findViewById(R.id.idDato);
            txv_nombre = (TextView) itemView.findViewById(R.id.sitio_idnombre);
            txv_radiación = (TextView) itemView.findViewById(R.id.sitio_radiacion);

            ImageButton btneditar = (ImageButton) itemView.findViewById(R.id.sitio_btneditar);
            ImageButton btneliminar = (ImageButton) itemView.findViewById(R.id.sitio_btneliminar);
            ImageView imagen_sitio = (ImageView) itemView.findViewById(R.id.sitio_image);

        }

        public void asignarDatos(String datos) {
            //dato.setText(datos);


        }
    }
}
