package com.example.supermercaadosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supermercaadosis.adaptador.caruselAdaptador;
import com.example.supermercaadosis.adaptador.categoriaAdaptador;
import com.example.supermercaadosis.adaptador.dataAdaptador;
import com.example.supermercaadosis.molde.carusel;
import com.example.supermercaadosis.molde.categoria;
import com.example.supermercaadosis.molde.datos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {
    // variables
FirebaseUser user;
FirebaseAuth auth;
FirebaseDatabase firebaseDatabase;
DatabaseReference Perfil;
//carusel
    RecyclerView cr;
    caruselAdaptador  car;
    DatabaseReference cabas;
    ArrayList<carusel> listaimagenes;
// categoria inicio
    RecyclerView ds;
    categoriaAdaptador cdd;
    DatabaseReference pro;
ArrayList<categoria>  catego;
    //productos inicio
    RecyclerView mr;
    dataAdaptador add;
    DatabaseReference databas;
     ArrayList<datos> list;
//navegacion botones
    Button inicioBoton,categoriaBoton,bolsa;
    // carrito
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
        }
        setContentView(R.layout.activity_index);
        //baner---------------------------------------------------------------------------------------------------------------------

        //navegacion -----------------------------------------------------------------------------------------------------------------
        bolsa= findViewById(R.id.button6);
        inicioBoton= findViewById(R.id.inicioind);
        categoriaBoton= findViewById(R.id.categoribu22);
        bolsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Index.this,MainActivity3.class));

            }
        });
        categoriaBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Index.this,categoria2.class));
            }
        });
        inicioBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Index.this,Index.class));

            }
        });
        //--------------------------------------------carusel--------------------------------------------------------------------------
        cr=findViewById(R.id.promociones);
        cabas=FirebaseDatabase.getInstance().getReference("carusel");
        cr.setHasFixedSize(true);
        cr.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        listaimagenes= new ArrayList<>();
        car=new caruselAdaptador(this,listaimagenes);
        cr.setAdapter(car);
    cabas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    carusel carusel2 = dataSnapshot.getValue(carusel.class);
                    listaimagenes.add(carusel2);

                }
                car.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //----------------------------------------------  Categoria  --------------------------------------------------------------------
                ds=findViewById(R.id.categoria);
                pro=FirebaseDatabase.getInstance().getReference("Categoria");
                ds.setHasFixedSize(true);
                ds.setLayoutManager(new GridLayoutManager(this,3));
        catego=new ArrayList<>();
                cdd=new categoriaAdaptador(this,catego);
                ds.setAdapter(cdd);
            cdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Index.this,categoria2.class);
                    i.putExtra("dato",catego.get(ds.getChildAdapterPosition(view)).getId().toString());
                    startActivity(i);


                }
            });
        pro.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                     categoria categoria2= dataSnapshot.getValue( categoria.class);
                     catego.add(categoria2);
                }
                cdd.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      // --------------------------------Mostrar datos desde firestore productos de inicio -------------------------------------------------


         mr = findViewById(R.id.textBuscar22);
         databas=FirebaseDatabase.getInstance().getReference("BasePro");
          mr.setHasFixedSize(true);
          //cobiar el layaout por el gridlayaout y colocarle las 2 columnas
        mr.setLayoutManager(new LinearLayoutManager( this,LinearLayoutManager.HORIZONTAL,false));
         list= new ArrayList<>();
        add= new dataAdaptador(this,list);
        mr.setAdapter(add);
       databas.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                   datos datos1=dataSnapshot.getValue(datos.class);
                   list.add(datos1);
               }
               add.notifyDataSetChanged();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
     //   -----------------------------Verificacion de logueo----------------------------------------
        // valor de las variables
        auth = FirebaseAuth.getInstance();
        user= auth.getCurrentUser();

    }
    @Override
    //// 2_ tenemos que crear un onStart para poder llamar a nuestra funcion logueoUsuario
    protected void onStart() {
        logueoUsuarios();
        super.onStart();

    }
 ///  1 _ primero creamos una funcion
    private  void  logueoUsuarios(){
        /// si el usuario no es nulo osea que si si esta puede entrar directo
        if (user !=null ){
             Toast.makeText(this, "Bienvenido :)", Toast.LENGTH_SHORT).show();
        } else {
            /// en caso de que no este lo manda a la pantalla de registro
            startActivity(new Intent(Index.this,MainActivity.class));
            finish();
         }
    }
    /////////////////////// usuario

}