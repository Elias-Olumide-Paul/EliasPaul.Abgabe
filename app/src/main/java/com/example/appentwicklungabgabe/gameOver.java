package com.example.appentwicklungabgabe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gameOver extends AppCompatActivity {
    Button Restart, Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Restart = findViewById(R.id.restart);
        Home = findViewById(R.id.home);

        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart = new Intent(gameOver.this, Game.class);
                startActivity(restart);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(gameOver.this,MainActivity.class);
                startActivity(home);
            }
        });


    }
}