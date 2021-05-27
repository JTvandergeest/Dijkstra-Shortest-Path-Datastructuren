package com.julian.dijkstra.controller.dto;

import com.julian.dijkstra.dijkstra.Stap;

import java.util.List;

public class RouteDto {
    public List<Integer> route;
    public List<Stap> stappen;

    public RouteDto() {
    }

    public RouteDto(List<Integer> route, List<Stap> stappen) {
        this.route = route;
        this.stappen = stappen;
    }
}
