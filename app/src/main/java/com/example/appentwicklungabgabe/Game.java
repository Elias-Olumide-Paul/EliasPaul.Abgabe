package com.example.appentwicklungabgabe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/*import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;*/


import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    private static final String KEY_Fakt = "Fakt";
    private static final String KEY_Frage = "Frage";
    private static final String KEY_ANTWORT = "Antwort";
    private static final String KEY_Nummer = "Nummer";
    //private static FirebaseFirestore db= FirebaseFirestore.getInstance();
    // private static DocumentReference faktRef = db.collection("Fakten").document();
    int lvlcount = 0;
    String Money;
    Button back;
    Button A, B, C, D;
    Button Start;
    TextView Frage;
    TextView Geld;
    Button halb, telefon;
    Button currentCorrect;
    ArrayList<Fakt> fakten   = new ArrayList<Fakt>();
    Fakt current;
    Button EingabeAntwort ;
    ArrayList<Integer> usedButton= new ArrayList<Integer>();
    ArrayList<Integer> usedAntwort= new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        back = findViewById(R.id.back);
        A = findViewById(R.id.AntwortA);
        B = findViewById(R.id.AntwortB);
        C = findViewById(R.id.AntwortC);
        D = findViewById(R.id.AntwortD);
        halb = findViewById(R.id.halbhalb);
        telefon = findViewById(R.id.Telefon);
        Frage = findViewById(R.id.Frage);
        Geld = findViewById(R.id.Geldanzeige);
        Start = findViewById(R.id.begin);

        Fakt eins= new Fakt("Olaf scholz ist Kanzler","Wer ist Kanzler","Olaf scholz",1);
        Fakt zwei= new Fakt("Monstaub riecht wie Schießpulver","Wonach riecht Mondstaub","Schießpulver",2);
        Fakt drei= new Fakt("Pamela Anderson nennt ihre Brüste Ernie und Bert","Wie nennt Pamela Anderson ihre Brüste ?","Ernie und Bert",3);
        Fakt vier= new Fakt("Nelson Mandela besitzt 34 Ehrendoktor-Titel"," Wie viele Ehrendoktor-Titel hat Nelson Mandela","34",4);
        Fakt fünf= new Fakt("In Tokio gibt es ein Bowling-Center mit 504 Bahnen","Wo gibt es ein Bowlingcenter mit 504 Bahnen?","Tokio",5);
        Fakt sechs= new Fakt("Der Plural von Oktopus heißt Oktopoden","Wie heißt die Mehrzahl von Oktopus","Oktopoden",6);
        Fakt sieben= new Fakt("Koalas schlafen täglich 20 Stunden","Wie lang schlafen Koalas pro Tag ","20 Stunden ",7);
        Fakt acht = new Fakt("Opak ist das Gegenteil von transparent","Was ist das gegenteil von transparent?","Opak",8);
        fakten.add(eins);
        fakten.add(zwei);
        fakten.add(drei);
        fakten.add(vier);
        fakten.add(fünf);
        fakten.add(sechs);
        fakten.add(sieben);
        fakten.add(acht);





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent zurück = new Intent(Game.this,MainActivity.class);
                //  startActivity(zurück);
            }
        });

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAwnser(currentCorrect,A);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAwnser(currentCorrect,B);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAwnser(currentCorrect,C);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EingabeAntwort = D;
            }
        });
        Geld.setText(setMoney(lvlcount));

        game();
    }

    public void game() {
        for(int i = 0; i <=15;i++) {


            Random rand = new Random();
            int max = fakten.size();
            int min = 1;
            int RandoNumb = rand.nextInt(max - min) + min;
            usedAntwort.add(RandoNumb);
            current = fakten.get(RandoNumb);
            Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setincorrectButton();
                    setincorrectButton();
                    setincorrectButton();
                    Frage.setText(current.getFrage());
                    System.out.println("klappt");
                    currentCorrect=setcorrectButton(current.getAntwort());
                    Geld.setText(setMoney(lvlcount));

                }
            });

            //Frage.setText(current.getFrage());



        }
    }

    private Button setcorrectButton(String antwort) {

        int min = 1 ;
        int max = 5;
        Random rand = new Random();
        int rander = rand.nextInt(max-min)+min;
        usedButton.add(rander);
        switch (rander){
            case 1 : A.setText(antwort);
                return A;
            case 2 : B.setText(antwort);
                return B;
            case 3 : C.setText(antwort);
                return C;
            case 4 : D.setText(antwort);
                return D;

        }

        return null;
    }

    private Button setincorrectButton() {

        int maxf = fakten.size();
        int minf = 1;
        Random zufall = new Random();
        int nextFakt = zufall.nextInt(maxf - minf) + minf;
        usedAntwort.add(nextFakt);
        Fakt wrong = fakten.get(nextFakt);



        int min = 1 ;
        int max = 5;
        Random rand = new Random();
        int rander = rand.nextInt(max-min)+min;
        while(usedButton.contains(rander)){
            rander=rand.nextInt();
            usedButton.add(rander);
        }
        System.out.println(usedButton);
        switch (rander){
            case 1 : A.setText(wrong.getAntwort());
                return A;
            case 2 : B.setText(wrong.getAntwort());
                return B;
            case 3 : C.setText(wrong.getAntwort());
                return C;
            case 4 : D.setText(wrong.getAntwort());
                return D;

        }

        return null;
    }

    private void checkAwnser(Button current, Button pressed) {
        if(current==pressed){
            lvlcount+=1;
            Geld.setText(setMoney(lvlcount));
            Start.setText("Nächste Frage");

        }
        else {
            lvlcount = 16;
            A.setClickable(false);
            B.setClickable(false);
            C.setClickable(false);
            D.setClickable(false);
            Geld.setText(setMoney(lvlcount));
            Intent gameOver = new Intent(Game.this,gameOver.class);
            startActivity(gameOver);
        }



    }

    private String setMoney(int lvl){
        switch (lvlcount){
            case 0 : Money ="0$";
                return Money;


            case 1 : Money ="50$";
                return Money;


            case 2 : Money ="100$";
                return Money;


            case 3 : Money ="200$";
                return Money;


            case 4 : Money ="300$";
                return Money;


            case 5: Money ="500$";
                return Money;


            case 6 : Money ="1.000$";
                return Money;


            case 7 : Money ="2.000$";
                return Money;


            case 8 : Money ="4.000$";
                return Money;


            case 9 : Money ="8.000$";
                return Money;


            case 10 : Money ="16.000$";
                return Money;


            case 11 : Money ="32.000$";
                return Money;


            case 12 : Money ="64.000$";
                return Money;


            case 13 : Money ="125.000$";
                return Money;


            case 14 : Money ="500.000$";
                return Money;


            case 15 : Money ="1.000.000$";
                return Money;


        }
        Money = "How dafq are you even able ?";
        return Money;
    }
    /*public  void loadFact(View v){
        Frage.setText(current.getFakt());
        faktRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            //String Fakt = documentSnapshot.getString(KEY_Fakt);
                            //String Frag= documentSnapshot.getString(KEY_Frage);
                            //String Antwort = documentSnapshot.getString(KEY_ANTWORT);
                            //int Nummer = Integer.parseInt(documentSnapshot.getString(KEY_Nummer));
                            Map<String,Object> Fakt = documentSnapshot.getData();
                            Frage.setText(KEY_Frage);
                            System.out.println(Frage);
                        }
                    }
                });



    }*/

}