package tsp.annealing;

import graph.AdjacencyMatrix;
import tsp.neighbour.NearestNeighbour;

import java.util.List;


public class SimulatedAnnealing {
    public static int tsp(AdjacencyMatrix matrix, boolean isSwap, boolean isGeometric) {
        int size = matrix.getSize();
        long timeLimit = getTimeLimit(size);

        List<Integer> solution = NearestNeighbour.get(matrix);
        double initialTemperature = 2 * getCost(matrix, solution);
        double currentTemperature = initialTemperature;

        long initialTime = System.currentTimeMillis();
        for (int i = 0; System.currentTimeMillis() - initialTime < timeLimit; i++) {
            for (int j = 0; j < size / 2; j++) {
                List<Integer> permutation = Neighbourhood.get(solution, isSwap);
                double difference = getCost(matrix, permutation) - getCost(matrix, solution);
                if (difference < 0) {
                    System.out.println(getCost(matrix, permutation));
                    solution = permutation;
                } else if (Math.random() < Math.pow(Math.E, -difference / currentTemperature)) {
                    solution = permutation;
                }
            }
            currentTemperature = CoolingSchedule.get(initialTemperature, i, isGeometric);
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

    private static long getTimeLimit(int instanceSize) {
        return instanceSize * 880L + 71689;
    }
}
