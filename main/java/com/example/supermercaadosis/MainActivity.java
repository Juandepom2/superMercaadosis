package com.example.supermercaadosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // declaramos las variables
    EditText correote, correote2;
    Button button,button2 ;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /////baner y color de el navegador
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_500));
        //////////////////////////////////
        setContentView(R.layout.activity_main);
        correote = findViewById(R.id.correote);
        correote2 = findViewById(R.id.correote2);
        button = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        button2= findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = correote.getText().toString();
                String password = correote2.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    correote.setError("correo no valido");
                    correote.setFocusable(true);
                } else if (password.length() < 6) {
                    correote2.setError("La contraseña debe de ser mayora 6 caracteres");
                    correote2.setFocusable(false);
                } else {
                    RegistrarUsuario(Email, password);
                }
            }
        });
    }

    //metodo para  registrar un usuario
    private void RegistrarUsuario(String Email, String password) {
        auth.createUserWithEmailAndPassword(Email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//si el refistro es exitoso
                        if (task.isSuccessful()){

                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;
                            String uidString = user.getUid();
                            //string
                            String correoString = correote.getText().toString();
                            String passworString = correote2.getText().toString();
                            String direc= "MI data base Usuarios/"+uidString+"/carrito";
                            String carrito= "";
                            HashMap<Object, Object> DatosUsuario =new HashMap<>();
                            DatosUsuario.put("Uid",uidString);
                            DatosUsuario.put("Email",correoString);
                            DatosUsuario.put("Password",passworString);
                            DatosUsuario.put("direcciCarrito",direc);
                            DatosUsuario.put("carrito",carrito);


                            FirebaseDatabase database= FirebaseDatabase.getInstance();

                            DatabaseReference reference = database.getReference("MI data base Usuarios"); //nombre de data de base
                            reference.child(uidString).setValue(DatosUsuario);
                            startActivity(new Intent(MainActivity.this,MainActivity2.class));
                            Toast.makeText(MainActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();

                        }
                    }
//Si falla el registro
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
