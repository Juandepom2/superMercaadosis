package com.example.supermercaadosis.adaptador;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supermercaadosis.Index;
import com.example.supermercaadosis.MainActivity;
import com.example.supermercaadosis.MainActivity2;
import com.example.supermercaadosis.MainActivity3;
import com.example.supermercaadosis.R;
import com.example.supermercaadosis.categoria2;
import com.example.supermercaadosis.molde.datos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//alt-enter crear contructor, clases, metodos y por ultimo el make
    public class dataAdaptador extends RecyclerView.Adapter<dataAdaptador.Myholder>   {

String dirc;
    ///costructor
      Context context;
      ArrayList<datos> lista;
        private View.OnClickListener carritp;
      public dataAdaptador(Context context, ArrayList<datos> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.diseno,parent,false);



         return new Myholder(v);
    }


    ///  1 _ primero creamos una funcion

    /////////////////////// usuario

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
     datos datos=lista.get(position);
     holder.nobre.setText(datos.getNobre());
     holder.precio.setText(datos.getPrecio());
        Glide.with(context).load(datos.getImagen())
                .into(holder.imagen);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,MainActivity3.class);
                i.putExtra("carrito",datos.toString());
                 context.startActivity(i);
                String Nombre = holder.nobre.getText().toString();
                String Precio = holder.precio.getText().toString();

                RegistroDeCarrito(Nombre,Precio);

            }

            private void RegistroDeCarrito(String nombre, String precio) {

                String Nombre = holder.nobre.getText().toString();
                String Precio = holder.precio.getText().toString();
                HashMap<Object, Object> CarritooProduc =new HashMap<>();
                CarritooProduc.put("Nombre",Nombre);
                CarritooProduc.put("precio",Precio);
                FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("MI data base Usuarios/32zRXgqDbITLvX1QI1fZCWtnrbM2"); //nombre de data de base
                reference.child("Carrito").setValue(CarritooProduc);
                Toast.makeText(context, "producto agregado ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount()
    {
         return lista.size();
    }

    public void setOnClickListener(View.OnClickListener carritp) {
        this.carritp=carritp;

    }


    public static class Myholder extends RecyclerView.ViewHolder{
TextView nobre,precio;
        ImageView imagen;
        Button buy;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.imagen);
            nobre=itemView.findViewById(R.id.nobre);
            precio=itemView.findViewById(R.id.precio);
            buy=itemView.findViewById(R.id.mas);

        }
    }


    }
