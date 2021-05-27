package com.julian.dijkstra.domain;

import java.util.*;

public class Stad {
    public String naam;

    public HashMap<String, List<Integer>> verbondenMet = new HashMap<>();

    public Stad(String naam){
        this.naam = naam;
    }

    public void voegVerbindingToe(String naam, int aantalKm, int aantalMinuten){
        //0 km
        //1 minuten
        ArrayList<Integer> soortVerbinding = new ArrayList<>();
        soortVerbinding.add(aantalKm);
        soortVerbinding.add(aantalMinuten);
        this.verbondenMet.put(naam, soortVerbinding);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stad stad = (Stad) o;
        return Objects.equals(naam, stad.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }
}
