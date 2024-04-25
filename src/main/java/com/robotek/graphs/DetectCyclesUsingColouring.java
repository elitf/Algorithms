package com.robotek.graphs;

import java.util.*;

enum Colors {
    WHITE,
    GRAY,
    BLACK
}

public class DetectCyclesUsingColouring {
    private static final int vertices = 5;
    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] adjList = new LinkedList[vertices];

    static {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public static void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    private static boolean DFSUtil(int vertex, Colors[] color) {
        // GRAY : This vertex is being processed (DFS
        // for this vertex has started, but not
        // ended (or this vertex is in function
        // call stack)
        color[vertex] = Colors.GRAY;

        for (int neighbor: adjList[vertex]) {
            if (color[neighbor] == Colors.GRAY) {
                return true;
            }

            // If v is not processed and there is a back
            // edge in subtree rooted with v
            if (color[neighbor] == Colors.WHITE && DFSUtil(neighbor, color)) {
                return true;
            }
        }

        // Mark this vertex as processed
        color[vertex] = Colors.BLACK;
        return false;
    }

    public static boolean isCyclic() {
        // Initialize color of all vertices as WHITE
        Colors[] color = new Colors[vertices];
        Arrays.fill(color, Colors.WHITE);
        // Do a DFS traversal beginning with all
        // vertices
        for (int i = 0; i < vertices; i++) {
            if (color[i] == Colors.WHITE) {
                if (DFSUtil(i, color)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(3, 3);

        if (isCyclic()) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }
}
