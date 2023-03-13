package com.example.appentwicklungabgabe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class popupAddFact extends AppCompatActivity {
    private static final String TAG = "popupAddFact";

    private static final String KEY_Fakt = "Fakt";
    private static final String KEY_Frage = "Frage";
    private static final String KEY_ANTWORT = "Antwort";
    private static final String KEY_Nummer = "Nummer";
    private static FirebaseFirestore db= FirebaseFirestore.getInstance();
    private static DocumentReference faktRef = db.collection("Fakten").document();

    private EditText Edit_Fakt;
    private EditText Edit_Frage;
    private EditText Edit_Antwort;
    private int Nummer;
    private Button Add ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_add_fact);
        Edit_Fakt = findViewById(R.id.Fakt);
        Edit_Frage= findViewById(R.id.FrageZuFakt);
        Edit_Antwort =findViewById(R.id.AntwortAufFrage);
    }
    public void saveFakt(View v){
        Nummer +=1 ;
        String Fakt = Edit_Fakt.getText().toString();
        String Frage= Edit_Frage.getText().toString();
        String Antwort = Edit_Antwort.getText().toString();

        Map<String, Object> fakt = new HashMap<>();
        fakt.put(KEY_Fakt, Fakt);
        fakt.put(KEY_Frage,Frage);
        fakt.put(KEY_ANTWORT, Antwort);
        fakt.put(KEY_Nummer, Nummer);

        db.collection("Fakten").document().set(fakt)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(popupAddFact.this,"eingespeist", Toast.LENGTH_SHORT).show();
                        Intent hop = new Intent(popupAddFact.this, rightSwipe.class);
                        startActivity(hop);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(popupAddFact.this,"Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());

                    }
                });

    }

}