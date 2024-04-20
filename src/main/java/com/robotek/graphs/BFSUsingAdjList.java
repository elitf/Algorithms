/**
 * This class demonstrates Breadth-First Search (BFS) using an adjacency list representation of a graph.
 * It includes methods to add edges between vertices and perform BFS traversal starting from a specified vertex.
 */
package com.robotek.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSUsingAdjList {
    // Number of vertices in the graph
    private static final int vertices = 5;

    // Adjacency list to store the graph representation
    @SuppressWarnings("unchecked")
    private static final List<Integer>[] adjList = new LinkedList[vertices];

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
    private static void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u); // Assuming an undirected graph
    }

    /**
     * Performs Breadth-First Search (BFS) traversal starting from a specified vertex.
     *
     * @param startVertex The starting vertex for BFS traversal.
     */
    private static void bfs(int startVertex) {
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

            // Traverse the neighbors of the current vertex
            for (int neighbor : adjList[currentVertex]) {
                // Check if the neighbor has not been visited
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited
                    visited[neighbor] = true;
                    // Add the neighbor to the queue for further exploration
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Performs Breadth-First Search (BFS) traversal for a disconnected graph starting from a specified vertex.
     *
     * @param startVertex The starting vertex for BFS traversal.
     * @param visited     An array to track visited vertices.
     */
    private static void bsfForDisconnectedGraph(int startVertex, boolean[] visited) {
        // Create a queue to store vertices to be visited
        Queue<Integer> queue = new LinkedList<>();

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

            // Traverse the neighbors of the current vertex
            for (int neighbor : adjList[currentVertex]) {
                // Check if the neighbor has not been visited
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited
                    visited[neighbor] = true;
                    // Add the neighbor to the queue for further exploration
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Helper method to perform BFS traversal on a disconnected graph by calling bsfForDisconnectedGraph for each unvisited vertex.
     */
    private static void bfsHelper() {
        // Create an array to track visited vertices
        boolean[] visited = new boolean[vertices];

        // Iterate through all vertices
        for (int i = 0; i < vertices; i++) {
            // Check if the vertex has not been visited
            if (!visited[i]) {
                // Perform BFS traversal for the current unvisited vertex
                bsfForDisconnectedGraph(i, visited);
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

        // Starting BFS traversal for disconnected graph
        System.out.println("BFS Traversal:");
        bfsHelper();
    }
}

