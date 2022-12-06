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
import com.example.supermercaadosis.molde.carrito;

import java.util.ArrayList;

public class carritoAdaptador  extends RecyclerView.Adapter<carritoAdaptador.Myholder>{
    Context context;
    ArrayList<carrito> carrito1;

    public carritoAdaptador(Context context, ArrayList<carrito> carrito) {
        this.context = context;
        this.carrito1 = carrito;
    }

    @NonNull
    @Override
    public  Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.carrito2,parent,false);
        return new Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  Myholder holder, int position) {
        carrito carrito=carrito1.get(position);
        holder.precio.setText(carrito.getPrecio());
        holder.nobre.setText(carrito.getNobre());
        Glide.with(context).load(carrito.getImagen())
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return carrito1.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView nobre,precio;
        ImageView imagen;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.imagen1);
            nobre=itemView.findViewById(R.id.nobre1);
            precio=itemView.findViewById(R.id.precio1);

        }
    }
}
