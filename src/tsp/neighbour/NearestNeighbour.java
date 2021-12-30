package tsp.neighbour;

import graph.AdjacencyMatrix;

import java.util.*;

public class NearestNeighbour {
    public static List<Integer> get(AdjacencyMatrix matrix) {
        int size = matrix.getSize();
        List<Integer> unvisited = getIndexes(size);
        List<Integer> solution = new ArrayList<>(List.of(0));
        unvisited.remove(0);
        int v = 0;
        while (!unvisited.isEmpty()) {
            v = getIndexOfNearest(matrix, unvisited, v);
            solution.add(v);
            unvisited.remove((Integer) v);
        }
        solution.add(0);
        return solution;
    }

    private static List<Integer> getIndexes(int size) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tmp.add(i);
        }
        return tmp;
    }

    private static int getIndexOfNearest(AdjacencyMatrix matrix, List<Integer> unvisited, int row) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (Integer u : unvisited) {
            int value = matrix.getData(row, u);
            if (value < min) {
                min = value;
                index = u;
            }
        }
        return index;
    }
}
