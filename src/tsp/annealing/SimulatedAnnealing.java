package tsp.annealing;

import graph.AdjacencyMatrix;
import tsp.neighbour.NearestNeighbour;

import java.util.List;


public class SimulatedAnnealing {
    public static int tsp(AdjacencyMatrix matrix) {
        List<Integer> solution = NearestNeighbour.get(matrix);
        double temperature = 2 * getCost(matrix, solution);

        int size = matrix.getSize();
        for (int i = 0; temperature > 0.001; i++) {
            for (int j = 0; j < size; j++) {
                List<Integer> permutation = Neighbourhood.swap(solution);
                double difference = getCost(matrix, permutation) - getCost(matrix, solution);
                if (difference < 0) {
                    solution = permutation;
                } else if (Math.random() < Math.pow(Math.E, -difference / temperature)) {
                    solution = permutation;
                }
            }
            temperature = CoolingSchedule.geometric(temperature, 0.999, i);
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
