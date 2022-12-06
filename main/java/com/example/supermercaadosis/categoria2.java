package com.example.supermercaadosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.supermercaadosis.adaptador.categoriaAdaptador;
import com.example.supermercaadosis.adaptador.producto_categoriaAdaptador;
import com.example.supermercaadosis.molde.ProductoCategoria;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class categoria2 extends AppCompatActivity {
     Button inicioBoton,categoriaBoton,perfilButon;
    // categoria inicio
    RecyclerView ds;
    categoriaAdaptador cdd;
    DatabaseReference pro;
    ArrayList<ProductoCategoria> catego;
    // productos
    RecyclerView pc;
    producto_categoriaAdaptador prod;
    DatabaseReference catePro;
    ArrayList<ProductoCategoria> productosCa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria2);
        categoriaBoton= findViewById(R.id.inicioind);

        //navegacion -----------------------------------------------------------------------------------------------------------------
        categoriaBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(categoria2.this,Index.class));
            }
        });
        //productos ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        pc=findViewById(R.id.categoria);
        String datos=getIntent().getStringExtra("dato");
        catePro=FirebaseDatabase.getInstance().getReference(""+ datos);
        pc.setHasFixedSize(true);
        pc.setLayoutManager(new GridLayoutManager(this,3));
        productosCa=new ArrayList<>();
        prod=new producto_categoriaAdaptador(this,productosCa);
        pc.setAdapter(prod);

        catePro.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductoCategoria ProductoCategoria= dataSnapshot.getValue( ProductoCategoria.class);
                    productosCa.add(ProductoCategoria);
                }
                prod.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}