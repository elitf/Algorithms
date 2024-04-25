package com.robotek.graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class to demonstrate cloning a directed acyclic graph.
 */
public class CloneDirectedAcyclicGraph {
    /**
     * Represents a node in the graph.
     */
    static class Node {
        int key; // Key to identify the node
        ArrayList<Node> adjList = new ArrayList<>(); // List of adjacent nodes

        Node(int key) {
            this.key = key;
        }
    }

    /**
     * Prints the graph starting from a given node.
     * @param startNode The starting node for traversal.
     * @param visited An array to mark visited nodes.
     */
    public static void printGraph(Node startNode, boolean[] visited) {
        if (!startNode.adjList.isEmpty()) {
            for (Node neighbor : startNode.adjList) {
                if (!visited[startNode.key]) {
                    System.out.println("edge " + startNode.key + "-" + neighbor.key);
                    if (!visited[neighbor.key]) {
                        printGraph(neighbor, visited);
                        visited[neighbor.key] = true;
                    }
                }
            }
        }
    }

    /**
     * Clones a directed acyclic graph starting from the given node.
     * @param oldSource The original source node in the graph.
     * @param newSource The new source node for the cloned graph.
     * @param visited An array to mark visited nodes.
     * @return The cloned graph starting from newSource.
     */
    public static Node cloneGraph(Node oldSource, Node newSource, boolean[] visited) {
        Node clone = null;

        if (!visited[oldSource.key] && !oldSource.adjList.isEmpty()) {
            for (Node oldNeighbor : oldSource.adjList) {
                if (clone == null || (clone != null && clone.key != oldNeighbor.key)) {
                    clone = new Node(oldNeighbor.key);
                }

                newSource.adjList.add(clone);
                cloneGraph(oldNeighbor, clone, visited);
                visited[oldNeighbor.key] = true;
            }
        }
        return newSource;
    }

    /**
     * Main method to demonstrate graph cloning.
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        // Create nodes for the original graph
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        // Build the original graph
        n0.adjList.add(n1);
        n0.adjList.add(n2);
        n1.adjList.add(n2);
        n1.adjList.add(n3);
        n1.adjList.add(n4);
        n2.adjList.add(n3);
        n3.adjList.add(n4);

        // Flag to mark visited nodes during traversal
        boolean[] visited = new boolean[5];
        System.out.println("Graph Before Cloning:-");
        printGraph(n0, visited);
        Arrays.fill(visited, false); // Reset visited array

        System.out.println("\nCloning Process Starts");
        Node clonedGraphHead = cloneGraph(n0, new Node(n0.key), visited);
        System.out.println("Cloning Process Completes.");

        Arrays.fill(visited, false); // Reset visited array
        System.out.println("\nGraph After Cloning:-");
        printGraph(clonedGraphHead, visited);
    }
}
