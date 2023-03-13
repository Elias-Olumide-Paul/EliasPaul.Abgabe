package com.example.appentwicklungabgabe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrierung extends AppCompatActivity {

TextInputEditText email , passwort;
Button registieren;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);
        registieren = (Button) findViewById(R.id.registerButReg);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        passwort = findViewById(R.id.password);
        registieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email, Passwort;
                Email = String.valueOf(email.getText());
                Passwort = String.valueOf(passwort.getText());

                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Passwort)){
                    Toast.makeText(Registrierung.this,"Unvollständige Eingabe bitte korrigiern", Toast.LENGTH_SHORT).show();
                return;
                }
                mAuth.createUserWithEmailAndPassword(Email, Passwort)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Registrierung.this, "Registrierung erfolgreich.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent backToLogin = new Intent(Registrierung.this,MainActivity.class);
                                    startActivity(backToLogin);
                                } else {
                                    Toast.makeText(Registrierung.this, "Registrierung fehlgeschlagen.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}