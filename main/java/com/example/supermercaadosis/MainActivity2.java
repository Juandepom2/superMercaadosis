package com.example.supermercaadosis;

import static com.example.supermercaadosis.R.id.buttonlo2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.supermercaadosis.databinding.ActivityMain2Binding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    ///variables
    EditText correote, pas;
    Button buttonlo2,button;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//conteniro
        getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.purple_500));
        setContentView(R.layout.activity_main2);
        // valor
        correote= findViewById(R.id.correote);
        pas= findViewById(R.id.correote2);
        button= findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
         buttonlo2 = findViewById(R.id.buttonlo2);
         //funcion
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
            //obtencion
               String correo =  correote.getText().toString();
               String pasw = pas.getText().toString();

               /////copia de el registro if ( validacion )
                 if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                     correote.setError("correo no valido");
                     correote.setFocusable(true);
                 } else if (pasw.length() < 6) {
                     pas.setError("La contraseña debe de ser mayora 6 caracteres");
                     pas.setFocusable(false);
                 } else {
                     LogeoUsuario(correo, pasw);
                 }
             }
         });
         buttonlo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));

            }
        });
    }
///metodo para el logueo de los suarios
    private void LogeoUsuario(String correo, String pasw) {
auth.signInWithEmailAndPassword(correo, pasw)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                 user = auth.getCurrentUser();
                Toast.makeText(MainActivity2.this, "Inicio de session", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));

             }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this, "CORREO O CONTRASEÑA INCORECTA"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}