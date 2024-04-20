/**
 * This class demonstrates Breadth-First Search (BFS) using an adjacency matrix representation of a graph.
 */
package com.robotek.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSUsingAdjMatrix {
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
     * Performs Breadth-First Search (BFS) traversal starting from a specified vertex.
     *
     * @param startVertex The starting vertex for BFS traversal.
     */
    public static void bfs(int startVertex) {
        // Create a queue to store vertices to be visited
        Queue<Integer> queue = new LinkedList<>();
        // Create an array to track visited vertices
        boolean[] visited = new boolean[vertices];

        // Add the starting vertex to the queue
        queue.add(startVertex);
        // Mark the starting vertex as visited
        visited[startVertex] = true;

        // Continue traversal until the queue is empty
        while (!queue.isEmpty()) {
            // Retrieve and remove the current vertex from the queue
            int currentVertex = queue.poll();
            // Print a message indicating the vertex being visited
            System.out.println("Visiting vertex: " + currentVertex);

            // Traverse the neighbors of the current vertex using the adjacency matrix
            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[currentVertex][i] != 0 && !visited[i]) {
                    // Mark the neighbor as visited
                    visited[i] = true;
                    // Add the neighbor to the queue for further exploration
                    queue.add(i);
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

        // Starting BFS traversal from vertex 0
        System.out.println("BFS Traversal:");
        bfs(0);
    }
}
