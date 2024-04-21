package com.robotek.graphs;

import java.util.LinkedList;

/**
 * A class to compute and print the transitive closure of a directed graph using Depth-First Search (DFS).
 */
public class TransitiveClosure {
    // Number of vertices in the graph
    private static final int vertices = 4;
    // Adjacency list to represent the graph
    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] adjList = new LinkedList[vertices];
    // Matrix to store reachability information, also called transitive closure
    private static final boolean[][] reachabilityMatrix = new boolean[vertices][vertices];

    // Initialize adjacency list for each vertex
    static {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * Add an edge to the directed graph.
     *
     * @param u Source vertex
     * @param v Destination vertex
     */
    public static void addEdge(int u, int v) {
        adjList[u].add(v); // Add v to u's adjacency list
    }

    /**
     * Perform Depth-First Search (DFS) to mark reachable vertices from the source.
     *
     * @param source Source vertex
     * @param vertex Current vertex being explored
     */
    private static void dfs(int source, int vertex) {
        reachabilityMatrix[source][vertex] = true; // Mark vertex as reachable from source

        // Recursively explore neighbors of vertex
        for (int neighbor : adjList[vertex]) {
            if (!reachabilityMatrix[source][neighbor]) {
                dfs(source, neighbor); // Explore unmarked neighbor
            }
        }
    }

    /**
     * Compute the reachability matrix for the directed graph.
     * This method marks all vertices reachable from each vertex using DFS.
     */
    public static void computeReachabilityMatrix() {
        for (int i = 0; i < vertices; i++) {
            // Start DFS from each vertex to mark reachable vertices
            dfs(i, i);
        }
    }

    /**
     * Print the reachability matrix showing which vertices are reachable from others.
     * '1' indicates reachability, '0' indicates non-reachability.
     */
    public static void printReachabilityMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                // Print '1' if reachable, '0' if non-reachable
                System.out.print(reachabilityMatrix[i][j] ? "1" : "0");
            }
            System.out.println(); // Move to the next row after printing all columns
        }
    }

    public static void main(String[] args) {
        // Add directed edges to the graph
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 1);

        computeReachabilityMatrix(); // Compute the reachability matrix
        printReachabilityMatrix(); // Print the reachability matrix
    }
}
