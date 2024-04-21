package com.robotek.graphs;

import java.util.*;

/**
 * A class to find and print all cycles in an undirected graph using coloring technique.
 */
public class PrintCyclesInGraphUsingColouring {

    private static final int NUM_VERTICES = 13; // Total number of vertices in the graph
    @SuppressWarnings("unchecked")
    private static final List<Integer>[] adjList = new ArrayList[NUM_VERTICES]; // Adjacency list representation
    private static final List<List<Integer>> cycles = new ArrayList<>(); // List to store all cycles found

    static {
        for (int i = 0; i < NUM_VERTICES; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    /**
     * Adds an edge between vertices u and v in the graph.
     *
     * @param u Vertex u
     * @param v Vertex v
     */
    public static void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u); // Undirected graph, so add edge in both directions
    }

    /**
     * Performs depth-first search (DFS) to detect cycles in the graph starting from vertex u.
     * It marks vertices with different colors (0 - unvisited, 1 - partially visited, 2 - completely visited)
     * and uses parent information to backtrack and find cycles.
     *
     * @param u       Current vertex being visited
     * @param parent  Parent of the current vertex in the DFS traversal
     * @param color   Array to mark visited vertices
     *                (0 - unvisited, 1 - partially visited, 2 - completely visited)
     * @param parents Array to store parent of each vertex in the DFS traversal
     */
    private static void dfsCycles(int u, int parent, int[] color, int[] parents) {
        // If the vertex is completely visited, no need to explore further
        if (color[u] == 2) {
            return;
        }

        // If the vertex is partially visited, a cycle is detected
        if (color[u] == 1) {
            // Backtrack based on parents to find the complete cycle
            List<Integer> cycleVertices = new ArrayList<>();
            int currentVertex = parent;
            cycleVertices.add(currentVertex);
            while (currentVertex != u) {
                currentVertex = parents[currentVertex];
                cycleVertices.add(currentVertex);
            }
            cycles.add(cycleVertices); // Add the cycle to the list of cycles
            return;
        }

        // Mark the current vertex as partially visited and set its parent
        parents[u] = parent;
        color[u] = 1;

        // Explore neighbors in DFS, skipping the edge to the parent in the DFS tree
        for (int neighbor : adjList[u]) {
            if (neighbor == parents[u]) {
                continue;
            }
            dfsCycles(neighbor, u, color, parents);
        }

        // Mark the current vertex as completely visited
        color[u] = 2;
    }

    /**
     * Prints all cycles found in the graph.
     */
    private static void printCycles() {
        for (List<Integer> cycle : cycles) {
            cycle.forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Build the graph by adding edges
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 5);
        addEdge(3, 6);
        addEdge(4, 5);
        addEdge(2, 4);
        addEdge(6, 7);
        addEdge(5, 9);
        addEdge(4, 8);
        addEdge(9, 8);
        addEdge(9, 10);
        addEdge(10, 11);
        addEdge(10, 12);
        addEdge(11, 12);

        // Arrays required for DFS and cycle detection
        int[] color = new int[NUM_VERTICES];
        int[] parents = new int[NUM_VERTICES];

        // Perform DFS to detect cycles
        dfsCycles(0, -1, color, parents);

        // Print all cycles found
        printCycles();
    }
}
