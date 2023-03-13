package com.example.appentwicklungabgabe;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class upSwipe extends AppCompatActivity {
    TextView countDownDisplay;
    TextView Fakt;
    private long verbleibendeZeit ;
    private long unterbrechungZeit;
    CountDownTimer countDown;
    private Button dailyFact ;
    private boolean laueft;
    int Stunden,min,sekunden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_swipe);
        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        countDownDisplay = findViewById(R.id.countdown);
        dailyFact = findViewById(R.id.dF);
        Fakt= findViewById(R.id.dailyFact);
        dailyFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(laueft==false){
                    startTimer();
                }

            }
        });
    }
    // countdown shit
    public void startTimer(){
        unterbrechungZeit = System.currentTimeMillis() + verbleibendeZeit;
        countDown = new CountDownTimer(verbleibendeZeit,1000) {
            @Override
            public void onTick(long l) {
                verbleibendeZeit=l;
                viewAnpassung();// important still to do
            }

            @Override
            public void onFinish() {
                dailyFact.setVisibility(View.VISIBLE);
                verbleibendeZeit =  86400000;
                viewAnpassung();
                laueft = false;


            }
        }.start();
        laueft = true;
        dailyFact.setVisibility(View.INVISIBLE);
    }
    public void viewAnpassung(){
        Stunden = (int) (verbleibendeZeit/1000)/3600;
        min = (int) ((verbleibendeZeit/1000)/60) - Stunden*60;
        sekunden = (int) verbleibendeZeit/1000%60;
        //int sekunden = 5;
        String anzeige = String.format(Locale.getDefault(),"%02d:%02d:%02d",Stunden,min,sekunden);

        countDownDisplay.setText(anzeige);
    }




    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("Verbleibende Zeit",verbleibendeZeit);
        editor.putBoolean("Countdown am laufen",laueft);
        editor.putLong("Endzeit",unterbrechungZeit);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        System.out.println(System.currentTimeMillis());
        verbleibendeZeit = pref.getLong("Verbleibende Zeit",86400000);
        laueft = pref.getBoolean("Countdown am laufen",false);
        unterbrechungZeit = pref.getLong("Endzeit",0);



        if (laueft==true){
            unterbrechungZeit= pref.getLong("Endzeit",0);
            verbleibendeZeit = unterbrechungZeit-System.currentTimeMillis();
            startTimer();
            Fakt.setText("Fakt des Tages");// hier fehlt noch import aus datenbank

            if(verbleibendeZeit<0){
                verbleibendeZeit = 86400000;
                laueft = false;
                dailyFact.setVisibility(View.VISIBLE);
                Fakt.setText("no nonon ");
                viewAnpassung();

            }
        }


    }
}