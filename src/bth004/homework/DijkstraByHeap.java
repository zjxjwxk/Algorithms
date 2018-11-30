package bth004.homework;

import java.util.*;

/**
 * Dijkstra implemented by heap
 * @author zjxjwxk
 */
public class DijkstraByHeap {

    /**
     * Shortest weights from starting node to each node
      */
    private static Map<Integer, Integer> d;

    /**
     * Nodes has been found the shortest path
      */
    private static List<Integer> s;

    /**
     * Nodes hasn't been found the shortest path
     */
    private static Set<Integer> q;

    /**
     * Find shortest path with Dijkstra and Heap
     * @param graph Directed graph represented by adjacency list
     * @param nodes Nodes in graph
     */
    public static void findShortestPath(int[][] graph, int[] nodes) {

        int nodeCount = nodes.length;
        int edgeCount = graph.length - 1;

        // Initial path, d, s and q
        d = new HashMap<>(nodeCount);
        s = new ArrayList<>(nodeCount);
        q = new HashSet<>(nodeCount);

        for (int node : nodes) {
            d.put(node, Integer.MAX_VALUE);
        }

        for (int node : nodes) {
            q.add(node);
        }

        // Shortest path from starting node to itself is 0
        d.put(nodes[0], 0);
        q.remove(nodes[0]);
        s.add(nodes[0]);

        Integer node = nodes[0];
        while (q.size() != 0) {
            // Improve d
            for (int i = 1; i <= edgeCount; i++) {
                if (graph[i][0] == node) {
                    if (d.get(graph[i][1]) > (d.get(node) + graph[i][2])) {
                        d.put(graph[i][1], d.get(node) + graph[i][2]);
                    }
                }
            }
            // Find next shortest path
            node = extractMin(q);
            s.add(node);
        }
    }

    /**
     * Find the node in q that has the shortest weights from starting node and remove it from q
     * @param q Nodes hasn't been found the shortest path
     * @return The node that has the shortest weight from starting node in q
     */
    private static Integer extractMin(Set<Integer> q) {
        Comparator<Map.Entry<Integer, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(comparator);
        for (Integer node :
                q) {
            int distance = d.get(node);
            if (distance != Integer.MAX_VALUE) {
                Map.Entry<Integer, Integer> entry = new Map.Entry<Integer, Integer>(){
                    @Override
                    public Integer getKey() {
                        return node;
                    }

                    @Override
                    public Integer getValue() {
                        return distance;
                    }

                    @Override
                    public Integer setValue(Integer value) {
                        return null;
                    }
                };
                priorityQueue.offer(entry);
            }
        }
        Map.Entry entry = priorityQueue.poll();
        q.remove(entry.getKey());
        return (Integer) entry.getKey();
    }

    /**
     * Print the shortest path
     * @param s Nodes has been found the shortest path
     */
    public static void printPath(List<Integer> s) {
        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
            if (i != s.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {6, 9},
                {1, 2, 1},
                {1, 3, 12},
                {2, 3, 9},
                {2, 4, 3},
                {4, 3, 4},
                {3, 5, 5},
                {4, 5, 13},
                {4, 6, 15},
                {5, 6, 4}
        };
        int[] nodes = {1, 2, 3, 4, 5, 6};
        findShortestPath(graph, nodes);
        printPath(s);
    }
}
