package com.example.appentwicklungabgabe;

public class Fakt{
    private String Fakt;
    private String Frage;
    private String Antwort;
    private int Nummer ;

    public Fakt(String fakt, String frage, String antwort, int nummer) {
        Fakt = fakt;
        Frage = frage;
        Antwort = antwort;
        Nummer = nummer;
    }

    public Fakt(){

    }


    public String getFakt() {
        return Fakt;
    }

    public void setFakt(String fakt) {
        Fakt = fakt;
    }

    public String getFrage() {
        return Frage;
    }

    public void setFrage(String frage) {
        Frage = frage;
    }

    public String getAntwort() {
        return Antwort;
    }

    public void setAntwort(String antwort) {
        Antwort = antwort;
    }

    public int getNummer() {
        return Nummer;
    }

    public void setNummer(int nummer) {
        Nummer = nummer;
    }




}
