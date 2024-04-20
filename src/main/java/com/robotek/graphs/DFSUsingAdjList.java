/**
 * This class demonstrates Depth-First Search (DFS) using an adjacency list representation of a graph.
 */
package com.robotek.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFSUsingAdjList {
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
     * Adds an edge between two vertices in the graph by updating the adjacency list.
     *
     * @param u One vertex of the edge.
     * @param v Another vertex of the edge.
     */
    private static void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u); // Assuming an undirected graph
    }

    /**
     * Performs Depth-First Search (DFS) traversal starting from a specified vertex.
     *
     * @param startVertex The starting vertex for DFS traversal.
     */
    private static void dfs(int startVertex) {
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

            // Traverse the neighbors of the current vertex using the adjacency list
            for (int neighbor : adjList[currentVertex]) {
                // Check if the neighbor has not been visited
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited
                    visited[neighbor] = true;
                    // Add the neighbor to the stack for further exploration
                    stack.push(neighbor);
                }
            }
        }
    }

    /**
     * Performs Depth-First Search (DFS) traversal for a disconnected graph starting from a specified vertex.
     *
     * @param startVertex The starting vertex for DFS traversal.
     * @param visited     An array to track visited vertices.
     */
    private static void dfsForDisconnectedGraph(int startVertex, boolean[] visited) {
        // Create a stack to store vertices to be visited
        Stack<Integer> stack = new Stack<>();

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

            // Traverse the neighbors of the current vertex using the adjacency list
            for (int neighbor : adjList[currentVertex]) {
                // Check if the neighbor has not been visited
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited
                    visited[neighbor] = true;
                    // Add the neighbor to the stack for further exploration
                    stack.push(neighbor);
                }
            }
        }
    }

    /**
     * Helper method to perform DFS traversal on a disconnected graph by calling dfsForDisconnectedGraph for each unvisited vertex.
     */
    public static void dfsHelper() {
        // Create an array to track visited vertices
        boolean[] visited = new boolean[vertices];

        // Iterate through all vertices
        for (int i = 0; i < vertices; i++) {
            // Check if the vertex has not been visited
            if (!visited[i]) {
                // Perform DFS traversal for the current unvisited vertex
                dfsForDisconnectedGraph(i, visited);
            }
        }
    }

    /**
     * Recursive method to perform DFS traversal starting from a specified vertex.
     *
     * @param currentVertex The current vertex being processed.
     * @param visited       An array to track visited vertices.
     */
    private static void dfsRec(int currentVertex, boolean[] visited) {
        // Mark the current vertex as visited
        visited[currentVertex] = true;
        // Print a message indicating the vertex being visited
        System.out.println("Visiting vertex: " + currentVertex);

        // Traverse neighbors of the current vertex recursively
        for (int neighbor : adjList[currentVertex]) {
            if (!visited[neighbor]) {
                dfsRec(neighbor, visited);
            }
        }
    }

    /**
     * Performs Depth-First Search (DFS) traversal using recursion starting from a specified vertex.
     *
     * @param startVertex The starting vertex for DFS traversal.
     */
    public static void dfsUsingRecursion(int startVertex) {
        boolean[] visited = new boolean[vertices];
        dfsRec(startVertex, visited);
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

        // Starting DFS traversal for disconnected graph
        System.out.println("DFS Traversal for Disconnected Graph:");
        dfsHelper();

        // Starting DFS traversal using recursion from vertex 0
        System.out.println("DFS Traversal using Recursion:");
        dfsUsingRecursion(0);
    }
}
