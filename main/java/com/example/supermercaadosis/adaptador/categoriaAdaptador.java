package com.example.supermercaadosis.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.supermercaadosis.Index;
import com.example.supermercaadosis.R;
import com.example.supermercaadosis.categoria2;
import com.example.supermercaadosis.molde.categoria;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class categoriaAdaptador extends
        RecyclerView.Adapter<categoriaAdaptador.Myholder>
        implements  View.OnClickListener {
      Context context;
    ArrayList<categoria> catego;
    private View.OnClickListener listener;

    public categoriaAdaptador(Context context,ArrayList<categoria>catego) {
        this.context = context;
        this.catego = catego;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v2 = LayoutInflater.from(context).inflate(R.layout.categoria,parent,false);
       v2.setOnClickListener(this);
        return new Myholder(v2);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        categoria categoria= catego.get(position);

        Glide.with(context).load(categoria.getCategoria())
                .into(holder.categoria);

     }

    @Override
    public int getItemCount() {

        return catego.size();
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
    if (listener != null){
        listener.onClick(view);
    }
    }

    public static class Myholder extends RecyclerView.ViewHolder{
        ImageView categoria;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            categoria=itemView.findViewById(R.id.imageButton);

        }
    }

}
