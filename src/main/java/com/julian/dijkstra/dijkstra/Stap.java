package com.julian.dijkstra.dijkstra;

import java.io.Serializable;

public class Stap implements Serializable {
    public int van;
    public int naar;
    public int lengte;

    public Stap(int van, int naar, int lengte) {
        this.van = van;
        this.naar = naar;
        this.lengte = lengte;
    }
}