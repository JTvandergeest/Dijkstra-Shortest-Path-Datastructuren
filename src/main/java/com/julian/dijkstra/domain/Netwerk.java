package com.julian.dijkstra.domain;

import java.util.ArrayList;
import java.util.List;

public class Netwerk {
    public ArrayList<Stad> steden;

    public Netwerk() {
        this.steden = new ArrayList<>();
    }

    public void voegStadToe(Stad stad) {
        steden.add(stad);
    }

    public Stad geefStad(int i) {
        return steden.get(i);
    }

    public Integer geefPositie(String stadsNaam) {
        return steden.indexOf(new Stad(stadsNaam));
    }
}
