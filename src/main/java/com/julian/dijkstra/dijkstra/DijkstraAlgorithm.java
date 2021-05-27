package com.julian.dijkstra.dijkstra;

import java.util.*;
public class DijkstraAlgorithm {

    public int dist[];
    public Set<Integer> bevestigdeNodes;
    public PriorityQueue<Node> priorityQueue;
    public int V; // Aantal vertices
    public List<List<Integer>> padHistorie = new ArrayList<>();
    public List<List<Stap>> stapHistorie = new ArrayList<>();
    List<List<Node>> adjendacies;

    public DijkstraAlgorithm(int V, int startpunt) {
        this.V = V;
        dist = new int[V];
        bevestigdeNodes = new HashSet<>();
        priorityQueue = new PriorityQueue<>(V, new Node());

        // Maak een lege historie aan voor elke node
        for (int i = 0; i < V; i++) {
            List<Integer> history = new ArrayList<>();
            padHistorie.add(history);

            List<Stap> history2 = new ArrayList<>();
            stapHistorie.add(history2);
        }

        //Voeg 0 toe aan de eerste lijst
        padHistorie.get(startpunt).add(startpunt);
    }

    // Functie voor Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adjendacies, int startpunt) {
        this.adjendacies = adjendacies;

        //Infinite value voor nodes die nog niet zijn uitgerekend
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Voeg startpunt toe aan de priority queue
        priorityQueue.add(new Node(startpunt, 0));

        // Afstand tot startpunt is 0
        dist[startpunt] = 0;

        //Zolang we niet klaar zijn
        while (bevestigdeNodes.size() != V) {

            // verwijder de dichtstbijzijnde node, van de queue
            int u = priorityQueue.remove().node;

            // voeg de node toe aan de settled lijst
            bevestigdeNodes.add(u);

            berekenBuren(u);
        }
    }

    // Functie om alle afstanden te berekenen voor de aanliggende nodes
    private void berekenBuren(int u) {
        int randAfstand = -1;
        int nieuweAfstand = -1;

        // Alle buren van U
        for (int i = 0; i < adjendacies.get(u).size(); i++) {
            Node otherNode = adjendacies.get(u).get(i);

            // Als de geselecteerde node nog niet is vastgesteld
            if (!bevestigdeNodes.contains(otherNode.node)) {

                randAfstand = otherNode.weight;
                nieuweAfstand = dist[u] + randAfstand;

                // If new distance is cheaper in cost
                if (nieuweAfstand < dist[otherNode.node]){
                    dist[otherNode.node] = nieuweAfstand;

                    //Stap toevoegen
                    stapHistorie.get(otherNode.node).clear(); //Clears history
                    stapHistorie.get(otherNode.node).addAll(stapHistorie.get(u)); //Copies the previous node's path
                    Stap stap = new Stap(u, otherNode.node, otherNode.weight);
                    stapHistorie.get(otherNode.node).add(stap); //Adds itself to the list

                    //Pad maken
                    padHistorie.get(otherNode.node).clear(); //Clears history
                    padHistorie.get(otherNode.node).addAll(padHistorie.get(u)); //Copies the previous node's path
                    padHistorie.get(otherNode.node).add(otherNode.node); //Adds itself to the list
                }

                // Add the current node to the queue
                priorityQueue.add(new Node(otherNode.node, dist[otherNode.node]));
            }
        }
    }
}