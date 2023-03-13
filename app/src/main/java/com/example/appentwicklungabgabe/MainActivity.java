package com.example.appentwicklungabgabe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
float startSwipeX, startSwipeY,endSwipeX,endSwipeY;
Button login;
TextInputEditText Benutzer, Passwort;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reg = findViewById(R.id.registerBut);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, Registrierung.class);
                startActivity(register);
            }
        });
        login = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        Benutzer = findViewById(R.id.usernameLog);
        Passwort = findViewById(R.id.passwortLog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BenutzerPass, PasswortPass;
                BenutzerPass = String.valueOf(Benutzer);
                PasswortPass = String.valueOf(Passwort);
                if (TextUtils.isEmpty(BenutzerPass) || TextUtils.isEmpty(PasswortPass)) {
                    Toast.makeText(MainActivity.this, "Unvollständige Eingabe bitte korrigiern", Toast.LENGTH_SHORT).show();
                    return;
                }
                    mAuth.signInWithEmailAndPassword(BenutzerPass, PasswortPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Anmeldung erfolgreich.",
                                                Toast.LENGTH_SHORT).show();
                                        //TODO find out why Anmeldung fehlgeschlagen
                                    } else {

                                        Toast.makeText(MainActivity.this, "Anmeldung fehlgeschlagen.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            });

        }



    public boolean onTouchEvent(MotionEvent swipe){
        switch(swipe.getAction()){
            case MotionEvent.ACTION_DOWN:
                    startSwipeX = swipe.getX();
                    startSwipeY = swipe.getY();
                    break;
            case MotionEvent.ACTION_UP:
                    endSwipeX = swipe.getX();
                    endSwipeY = swipe.getY();
                    float distanzX = Math.abs(startSwipeX-endSwipeX);
                    float distanzY = Math.abs(startSwipeY-endSwipeY);
                    if(startSwipeX > endSwipeX){
                        Intent left = new Intent(MainActivity.this,Game.class);
                        startActivity(left);
                    }
                    if(startSwipeX<endSwipeX){
                        Intent right = new Intent(MainActivity.this, rightSwipe.class);
                        startActivity(right);
                    }
                   else if(startSwipeY>endSwipeY && distanzY >distanzX){
                        Intent up = new Intent(MainActivity.this, upSwipe.class);
                        startActivity(up);
                    }

        }
        return false;


    }
}