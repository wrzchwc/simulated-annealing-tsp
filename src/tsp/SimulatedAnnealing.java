package tsp;

import matrix.AdjacencyMatrix;
import neighbour.NearestNeighbour;
import neighbour.ValuePair;

import java.util.ArrayList;
import java.util.List;

public class SimulatedAnnealing {
    //todo: different methods for different variants or special args
    public static int tsp(AdjacencyMatrix matrix) {
        ValuePair<List<Integer>, Integer> initialPair = NearestNeighbour.get(matrix);
        int temperature = initialPair.getValue();
        List<Integer> solution = initialPair.getKey();

        return getCost(matrix, solution);
    }

    private static int getCost(AdjacencyMatrix matrix, List<Integer> list) {
        int cost = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            cost += matrix.getData(list.get(i), list.get(i + 1));
        }
        return cost;
    }

}
