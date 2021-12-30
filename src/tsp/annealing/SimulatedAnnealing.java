package tsp.annealing;

import graph.AdjacencyMatrix;
import tsp.neighbour.NearestNeighbour;
import tsp.neighbour.ValuePair;

import java.util.List;


public class SimulatedAnnealing {
    public static int tsp(AdjacencyMatrix matrix) {
        ValuePair<List<Integer>, Integer> initialPair = NearestNeighbour.get(matrix);
        List<Integer> solution = initialPair.getKey();
        double temperature = 2 * initialPair.getValue();
        int size = matrix.getSize();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j < size; j++) {
                if (i == j) {
                    continue;
                }
                List<Integer> permutation = Neighbourhood.swap(solution, i, j);
                double difference = getCost(matrix, permutation) - getCost(matrix, solution);
                double a = Math.random();
                double b = Math.pow(Math.E, -difference / temperature);
                if (difference < 0) {
                    solution = permutation;
                } else if (a < b) {
                    solution = permutation;
                }
            }
            temperature = CoolingSchedule.geometric(temperature, 0.99, i);
        }
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
