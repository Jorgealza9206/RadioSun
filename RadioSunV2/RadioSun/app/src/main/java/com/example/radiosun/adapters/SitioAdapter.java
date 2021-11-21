package com.example.radiosun.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiosun.R;

import java.util.ArrayList;

public class SitioAdapter extends RecyclerView.Adapter<SitioAdapter.ViewHolderRegistro> {

    ArrayList<Sitio> registros;

    public SitioAdapter(ArrayList<Sitio> r){
        this.registros = r;
    }

    @NonNull
    @Override
    public ViewHolderRegistro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitio_item,null,false);
        return new ViewHolderRegistro(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRegistro holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderRegistro extends RecyclerView.ViewHolder {

        public ViewHolderRegistro(@NonNull View itemView) {
            int id;
            TextView txtNombreCompleto;
            TextView txtCoordenadas;
            TextView txtRadiacion;

            super(itemView);
        }
    }
}
