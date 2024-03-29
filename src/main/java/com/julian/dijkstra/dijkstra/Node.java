package com.julian.dijkstra.dijkstra;

import java.util.Comparator;

// Class to represent a node in the graph
public class Node implements Comparator<Node> {

    public int node;
    public int weight;

    public Node()
    {
    }

    public Node(int node, int weight)
    {
        this.node = node;
        this.weight = weight;
    }

    public int compare(Node node1, Node node2)
    {
        return Integer.compare(node1.weight, node2.weight);
    }
}
