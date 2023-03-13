package com.example.appentwicklungabgabe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rightSwipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_swipe);
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        Button add = (Button) findViewById(R.id.buttonAddFact);
        Button back = (Button) findViewById(R.id.backButtonFromRightSwipe);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backEx = new Intent(rightSwipe.this, MainActivity.class);
                startActivity(backEx);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(rightSwipe.this, popupAddFact.class);
                startActivity(add);
            }
        });


    }
}