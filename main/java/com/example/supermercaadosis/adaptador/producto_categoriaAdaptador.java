package com.example.supermercaadosis.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supermercaadosis.R;
import com.example.supermercaadosis.molde.ProductoCategoria;

import java.util.ArrayList;

public class producto_categoriaAdaptador extends RecyclerView.Adapter<producto_categoriaAdaptador.Myholder> {
Context context;
ArrayList<ProductoCategoria> productosCa;

    public producto_categoriaAdaptador(Context context, ArrayList<ProductoCategoria> productosCa) {
        this.context = context;
        this.productosCa = productosCa;
    }

    @NonNull
    @Override
    public producto_categoriaAdaptador.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.categoria_productos,parent,false);
        return new Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull producto_categoriaAdaptador.Myholder holder, int position) {
        ProductoCategoria productoCategoria= productosCa.get(position);
        holder.nombre.setText(productoCategoria.getNombre());
        holder.precio.setText(productoCategoria.getPrecio());
        Glide.with(context).load(productoCategoria.getImagen())
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return  productosCa.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView precio;
        TextView nombre;
        ImageView imagen;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nobre2);
            precio=itemView.findViewById(R.id.precio2);
            imagen=itemView.findViewById(R.id.imagen2);

        }
    }
}
