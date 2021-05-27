package com.julian.dijkstra.service;

import com.julian.dijkstra.controller.dto.RouteDto;
import com.julian.dijkstra.dijkstra.DijkstraAlgorithm;
import com.julian.dijkstra.dijkstra.Node;
import com.julian.dijkstra.dijkstra.Stap;
import com.julian.dijkstra.domain.Netwerk;
import com.julian.dijkstra.domain.Stad;

import java.util.ArrayList;
import java.util.List;

public class NetwerkService {

    public Netwerk geefNetwerk(){
        Netwerk netwerk = new Netwerk();

        Stad amsterdam = new Stad("Amsterdam");
        Stad utrecht = new Stad("Utrecht");
        Stad zwolle = new Stad("Zwolle");
        Stad groningen = new Stad("Groningen");
        Stad alkmaar = new Stad("Alkmaar");
        Stad lelystad = new Stad("Lelystad");
        Stad leeuwarden = new Stad("Leeuwarden");
        Stad rotterdam = new Stad("Rotterdam");
        Stad denhaag = new Stad("Den Haag");

        amsterdam.voegVerbindingToe("Zwolle", 63, 30);
        amsterdam.voegVerbindingToe("Groningen", 101, 130);
        amsterdam.voegVerbindingToe("Lelystad", 74, 40);

        lelystad.voegVerbindingToe("Leeuwarden", 112, 60);

        utrecht.voegVerbindingToe("Amsterdam", 34, 25);
        utrecht.voegVerbindingToe("Groningen", 82, 120);
        utrecht.voegVerbindingToe("Rotterdam", 96, 86);
        utrecht.voegVerbindingToe("Den Haag", 102, 65);

        denhaag.voegVerbindingToe("Rotterdam", 20, 24);
        denhaag.voegVerbindingToe("Alkmaar", 101, -1);

        groningen.voegVerbindingToe("Leeuwarden", 91, 30);
        alkmaar.voegVerbindingToe("Amsterdam", 75, 40);

        alkmaar.voegVerbindingToe("Utrecht", 55, -1);

        netwerk.voegStadToe(amsterdam);
        netwerk.voegStadToe(utrecht);
        netwerk.voegStadToe(zwolle);
        netwerk.voegStadToe(groningen);
        netwerk.voegStadToe(alkmaar);
        netwerk.voegStadToe(lelystad);
        netwerk.voegStadToe(leeuwarden);
        netwerk.voegStadToe(rotterdam);
        netwerk.voegStadToe(denhaag);

        return netwerk;
    }

    public List<List<Node>> getAdjacencies(Netwerk netwerk, Integer soortGewicht){
        List<List<Node>> adjacencies = new ArrayList<>();

        // Initialize list for every node
        for (int i = 0; i < netwerk.steden.size(); i++) {
            List<Node> item = new ArrayList<>();
            adjacencies.add(item);
        }

        //Geef voor elke stad de verbonden steden op en bouw netwerk
        for (int i = 0; i < netwerk.steden.size(); i++) {
            Stad stad = netwerk.geefStad(i);

            int finalI = i;
            stad.verbondenMet.forEach((andereStad, gewicht) -> {
                int gewichtAantal = gewicht.get(soortGewicht);
                if (gewichtAantal > 0) {
                    adjacencies.get(finalI).add(new Node(netwerk.geefPositie(andereStad), gewichtAantal));

                    //2 way coupling
                    adjacencies.get(netwerk.geefPositie(andereStad)).add(new Node(finalI, gewichtAantal));
                }
            });
        }

        return adjacencies;
    }

    public RouteDto kortste_route(String startpunt, String eindpunt, Integer soortGewicht){
        Netwerk netwerk = geefNetwerk();
        int s1 = netwerk.geefPositie(startpunt);
        int e1 = netwerk.geefPositie(eindpunt);
        return kortste_route(getAdjacencies(netwerk, soortGewicht), s1, e1);
    }

    public RouteDto kortste_route(List<List<Node>> adjacencies, int startpunt, int eindpunt){
        int V = geefNetwerk().steden.size();

        // Calculate the single source shortest path
        DijkstraAlgorithm da = new DijkstraAlgorithm(V, startpunt);
        da.dijkstra(adjacencies, startpunt);

        // from the source node to end node
        System.out.println(startpunt + " to " + eindpunt + " is "
                + da.dist[eindpunt] + " route: " + da.padHistorie.get(eindpunt).toString());

        //Return list
        List<Integer> historie = da.padHistorie.get(eindpunt);
        List<Stap> stappen = da.stapHistorie.get(eindpunt);
        return new RouteDto(historie, stappen);
    }

    public ArrayList<Stad> geefSteden() {
        return geefNetwerk().steden;
    }
}
