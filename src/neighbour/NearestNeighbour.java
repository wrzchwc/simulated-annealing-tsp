package neighbour;

import matrix.AdjacencyMatrix;

import java.util.*;

public class NearestNeighbour {
    public static int get(AdjacencyMatrix matrix) {
        int size = matrix.getSize();
        int parameter = 0;
        List<Integer> unvisited = getIndexes(size);
        int u = unvisited.get(0);
        unvisited.remove(0);
        int v = 0;
        while (!unvisited.isEmpty()) {
            ValuePair<Integer, Integer> indexOfNearest = getIndexOfNearest(matrix, unvisited, v);
            v = indexOfNearest.getKey();
            parameter += indexOfNearest.getValue();
            unvisited.remove((Integer) v);
        }
        parameter += matrix.getData(v, 0);

        return parameter;
    }

    private static List<Integer> getIndexes(int size) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tmp.add(i);
        }
        return tmp;
    }

    private static ValuePair<Integer, Integer> getIndexOfNearest(AdjacencyMatrix matrix, List<Integer> unvisited, int row) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (Integer u : unvisited) {
            int value = matrix.getData(row, u);
            if (value < min) {
                min = value;
                index = u;
            }
        }
        return new ValuePair<>(index, min);
    }
}
