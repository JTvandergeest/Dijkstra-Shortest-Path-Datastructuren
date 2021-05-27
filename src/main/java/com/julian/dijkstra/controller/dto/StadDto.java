package com.julian.dijkstra.controller.dto;

public class StadDto {
    public int positie;
    public String naam;

    public StadDto() {
    }

    public StadDto(int positie, String naam) {
        this.positie = positie;
        this.naam = naam;
    }

    public int getPositie() {
        return positie;
    }

    public void setPositie(int positie) {
        this.positie = positie;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
