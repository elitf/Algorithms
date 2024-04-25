package com.robotek.graphs;

import java.util.LinkedList;

/**
 * A class to detect cycles in a directed graph using Depth-First Search (DFS).
 */
public class DetectCyclesUsingDFS {
    private static final int vertices = 5; // Number of vertices in the graph
    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] adjList = new LinkedList[vertices]; // Adjacency list representation of the graph

    // Static initializer to initialize the adjacency list
    static {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * Adds a directed edge from vertex u to vertex v in the graph.
     * @param u The source vertex.
     * @param v The destination vertex.
     */
    public static void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    /**
     * Checks if there is a cycle in the graph starting from the given vertex using Depth-First Search (DFS).
     * @param vertex The current vertex being visited.
     * @param visited Array to track visited vertices.
     * @param recStack Array to track vertices in the recursion stack (to detect back edges).
     * @return True if a cycle is found, false otherwise.
     */
    private static boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        if (recStack[vertex]) {
            return true; // If the current vertex is already in the recursion stack, there is a cycle
        }

        if (visited[vertex]) {
            return false; // If the current vertex is already visited, it's not part of a cycle
        }

        visited[vertex] = true; // Mark the current vertex as visited
        recStack[vertex] = true; // Mark the current vertex as part of the recursion stack

        // Recursively check all neighbors of the current vertex
        for (int neighbor : adjList[vertex]) {
            if (isCyclicUtil(neighbor, visited, recStack)) {
                return true; // If a cycle is found in any neighbor, return true
            }
        }

        recStack[vertex] = false; // Remove the current vertex from the recursion stack after DFS traversal

        return false; // No cycle found starting from the current vertex
    }

    /**
     * Checks if the graph contains any cycles using Depth-First Search (DFS).
     * @return True if the graph contains a cycle, false otherwise.
     */
    public static boolean isCyclic() {
        boolean[] visited = new boolean[vertices]; // Array to track visited vertices during DFS
        boolean[] recStack = new boolean[vertices]; // Array to track vertices in the recursion stack

        // Perform DFS traversal from each vertex to detect cycles
        for (int i = 0; i < vertices; i++) {
            if (isCyclicUtil(i, visited, recStack)) {
                return true; // If a cycle is found starting from any vertex, return true
            }
        }

        return false; // No cycle found in the graph
    }

    /**
     * Main method to test the DetectCyclesUsingDFS class.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Add directed edges to the graph
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(3, 3);

        // Check if the graph contains a cycle using DFS
        if (isCyclic()) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }
}
