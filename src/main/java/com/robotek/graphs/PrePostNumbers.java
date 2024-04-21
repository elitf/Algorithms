package com.robotek.graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * This class calculates pre- and post-numbers for vertices using Depth-First Search (DFS).
 */
public class PrePostNumbers {
    // Number of vertices in the graph
    private static final int vertices = 7;
    // Adjacency list to store the graph representation
    @SuppressWarnings("unchecked")
    private static final List<Integer>[] adjList = new LinkedList[vertices];
    // Time variable for pre- and post-number calculation
    private static int time = 1;

    // Static block to initialize the adjacency list
    static {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param u One vertex of the edge.
     * @param v Another vertex of the edge.
     */
    public static void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    /**
     * Performs Depth-First Search (DFS) starting from a specified vertex to calculate pre- and post-numbers.
     *
     * @param currentVertex The current vertex being processed.
     * @param pre           Array to store pre numbers for vertices.
     * @param post          Array to store post numbers for vertices.
     * @param visited       Array to track visited vertices.
     */
    public static void dfs0(int currentVertex, int[] pre, int[] post, boolean[] visited) {
        // Calculate pre number for the current vertex and increment time
        pre[currentVertex] = time++;

        visited[currentVertex] = true;
        // Traverse neighbors of the current vertex recursively
        for (int neighbor : adjList[currentVertex]) {
            if (!visited[neighbor]) {
                dfs0(neighbor, pre, post, visited);
            }
        }

        // Calculate post number for the current vertex and increment time
        post[currentVertex] = time++;
    }

    /**
     * Initiates DFS for pre- and post-number calculation starting from a specified vertex.
     *
     * @param startVertex The starting vertex for DFS traversal.
     */
    public static void dfsForPreAndPostNumbers(int startVertex) {
        int[] pre = new int[vertices];
        int[] post = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // Perform DFS to calculate pre and post numbers
        dfs0(startVertex, pre, post, visited);
        // Print pre- and post-numbers for each vertex
        for (int i = 1; i < vertices; i++) {
            System.out.println("Node " + i + " Pre number " + pre[i] + " Post number " + post[i]);
        }
    }

    public static void main(String[] args) {
        // Adding edges to the graph
        addEdge(1, 2);
        addEdge(2, 1);
        addEdge(2, 4);
        addEdge(4, 2);
        addEdge(1, 3);
        addEdge(3, 1);
        addEdge(3, 4);
        addEdge(1, 2);
        addEdge(4, 3);
        addEdge(3, 5);
        addEdge(5, 3);
        addEdge(5, 6);
        addEdge(6, 5);

        // Starting DFS to calculate pre- and post-numbers from vertex 1
        dfsForPreAndPostNumbers(1);
    }
}
