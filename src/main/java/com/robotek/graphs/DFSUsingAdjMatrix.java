/**
 * This class demonstrates Depth-First Search (DFS) using an adjacency matrix representation of a graph.
 */
package com.robotek.graphs;

import java.util.Stack;

public class DFSUsingAdjMatrix {
    // Number of vertices in the graph
    private static final int vertices = 5;
    // Adjacency matrix to store the graph representation
    private static final int[][] adjMatrix = new int[vertices][vertices];

    /**
     * Adds an edge between two vertices in the graph by updating the adjacency matrix.
     *
     * @param u One vertex of the edge.
     * @param v Another vertex of the edge.
     */
    private static void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1; // Assuming an undirected graph
    }

    /**
     * Performs Depth-First Search (DFS) traversal starting from a specified vertex.
     *
     * @param startVertex The starting vertex for DFS traversal.
     */
    public static void dfs(int startVertex) {
        // Create a stack to store vertices to be visited
        Stack<Integer> stack = new Stack<>();
        // Create an array to track visited vertices
        boolean[] visited = new boolean[vertices];

        // Add the starting vertex to the stack
        stack.add(startVertex);
        // Mark the starting vertex as visited
        visited[startVertex] = true;

        // Continue traversal until the stack is empty
        while (!stack.isEmpty()) {
            // Retrieve and remove the current vertex from the stack
            int currentVertex = stack.pop();
            // Print a message indicating the vertex being visited
            System.out.println("Visiting vertex: " + currentVertex);

            // Traverse the neighbors of the current vertex using the adjacency matrix
            for (int i = 0; i < vertices; i++) {
                // Check if there is an edge between the current vertex and its neighbor, and if the neighbor has not been visited
                if (adjMatrix[currentVertex][i] != 0 && !visited[i]) {
                    // Mark the neighbor as visited
                    visited[i] = true;
                    // Add the neighbor to the stack for further exploration
                    stack.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Adding edges to the graph
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 4);

        // Starting DFS traversal from vertex 0
        System.out.println("DFS Traversal:");
        dfs(0);
    }
}
