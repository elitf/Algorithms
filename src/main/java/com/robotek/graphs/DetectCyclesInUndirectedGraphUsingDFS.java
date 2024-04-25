package com.robotek.graphs;

import java.util.LinkedList;

/**
 * DetectCyclesInUndirectedGraphUsingDFS class provides methods to detect cycles in an undirected graph using Depth-First Search (DFS).
 */
public class DetectCyclesInUndirectedGraphUsingDFS {
    // Number of vertices in the graph
    private static final int vertices = 5;

    // Adjacency list representation of the graph
    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] adjList = new LinkedList[vertices];

    static {
        // Initialize adjacency lists for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param u The first vertex.
     * @param v The second vertex.
     */
    public static void addEdge(int u, int v) {
        // Add edge u-v and v-u as the graph is undirected
        adjList[u].add(v);
        adjList[v].add(u);
    }

    /**
     * Recursive utility function to detect cycles in the graph.
     *
     * @param vertex The current vertex being visited.
     * @param visited An array to track visited vertices.
     * @param parent The parent of the current vertex in the DFS traversal.
     * @return True if a cycle is detected, false otherwise.
     */
    private static boolean isCyclicUtil(int vertex, boolean[] visited, int parent) {
        visited[vertex] = true; // Mark the current vertex as visited

        // Iterate through all adjacent vertices of the current vertex
        for (int v : adjList[vertex]) {
            // If the adjacent vertex v is not visited, recursively check for cycles
            if (!visited[v]) {
                if (isCyclicUtil(v, visited, vertex)) {
                    return true; // Cycle detected
                }
            } else if (v != parent) {
                return true; // Cycle detected if v is visited and not the parent of the current vertex
            }
        }
        return false; // No cycle detected
    }

    /**
     * Checks if the graph contains any cycles using DFS traversal.
     *
     * @return True if the graph contains a cycle, false otherwise.
     */
    public static boolean isCyclic() {
        boolean[] visited = new boolean[vertices]; // Mark all vertices as not visited

        // Check for cycles in each disconnected component of the graph
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, -1)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle detected in the graph
    }

    /**
     * Main method to demonstrate cycle detection in a graph.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Add edges to create a graph
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 3); // Self-loop to create a cycle

        // Check if the graph contains a cycle and print the result
        if (isCyclic()) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }
}
