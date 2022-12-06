package com.example.supermercaadosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.supermercaadosis.adaptador.carritoAdaptador;
import com.example.supermercaadosis.adaptador.caruselAdaptador;
import com.example.supermercaadosis.molde.carrito;
import com.example.supermercaadosis.molde.datos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
///variables
    RecyclerView car;
   carritoAdaptador carritoAdaptador;
    DatabaseReference base;
    ArrayList<carrito> carrito1;
    private TextView prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        prueba =findViewById(R.id.textView4);
         prueba.setText("Carrito de articulos");
        car=findViewById(R.id.recyclerView);
        base= FirebaseDatabase.getInstance().getReference("BasePro");
        car.setHasFixedSize(true);
        car.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        carrito1= new ArrayList<>();
        carritoAdaptador=new carritoAdaptador(this,carrito1);
        car.setAdapter(carritoAdaptador);
        base.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                carrito carrito=snapshot.getValue(carrito.class);
                carrito1.add(carrito);
            }
                carritoAdaptador.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}