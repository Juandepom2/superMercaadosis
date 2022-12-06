package com.example.supermercaadosis.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.example.supermercaadosis.R;
import com.example.supermercaadosis.molde.carusel;

import java.util.ArrayList;

public class caruselAdaptador extends RecyclerView.Adapter<caruselAdaptador.Myholder> {
    Context context;
    ArrayList<carusel> listaimagenes;

    public caruselAdaptador(Context context, ArrayList<carusel> listaimagenes) {
        this.context = context;
        this.listaimagenes = listaimagenes;
    }

    @NonNull
    @Override
    public caruselAdaptador.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v3 = LayoutInflater.from(context).inflate(R.layout.carrusel,parent,false);
        return new Myholder(v3);
    }

    @Override
    public void onBindViewHolder(@NonNull caruselAdaptador.Myholder holder, int position) {
     carusel carusel = listaimagenes.get(position);

        Glide.with(context).load(carusel.getImagen())
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return listaimagenes.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        ImageView imagen;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.imageView);
        }
    }
}
