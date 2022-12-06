package com.example.supermercaadosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class perfil extends AppCompatActivity {
//Variables
FirebaseUser user;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference Perfil;
    TextView correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        correo.findViewById(R.id.textView10);

        user= auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        Perfil= firebaseDatabase.getReference("MI data base Usuarios");
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
            consulta();
            Toast.makeText(this, "Bienvenido :)"+user, Toast.LENGTH_SHORT).show();
        } else {
            /// en caso de que no este lo manda a la pantalla de registro
            startActivity(new Intent(perfil.this,MainActivity.class));
            finish();
        }
    }
    /////////////////////// usuario
    private void consulta(){
        Query query = Perfil.orderByChild("Uid").equalTo(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                        String correoS= ""+ds.child("Email").getValue();
                    correo.setText(correoS);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }}
