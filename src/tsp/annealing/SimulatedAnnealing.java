package tsp.annealing;

import graph.AdjacencyMatrix;
import tsp.neighbour.NearestNeighbour;

import java.util.*;


public class SimulatedAnnealing {
    public static int tspGreedy(AdjacencyMatrix matrix, boolean isSwap, boolean isGeometric) {
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

    public static int tspSteepest(AdjacencyMatrix matrix, boolean isSwap, boolean isGeometric) {
        int size = matrix.getSize();
        long timeLimit = getTimeLimit(size);

        List<Integer> solution = getRandomPath(size);
        double initialTemperature = 2 * getCost(matrix, solution);
        double currentTemperature = initialTemperature;

        long initialTime = System.currentTimeMillis();
        for (int i = 0; System.currentTimeMillis() - initialTime < timeLimit; i++) {
            Map<Integer, List<Integer>> solutionsInNeighbourhood = new HashMap<>();
            List<Integer> permutation;
            for (int j = 0; j < size / 2; j++) {
                permutation = Neighbourhood.get(solution, isSwap);
                solutionsInNeighbourhood.put(getCost(matrix, permutation), permutation);
            }
            permutation = solutionsInNeighbourhood.get(Collections.min(solutionsInNeighbourhood.keySet()));
            double difference = getCost(matrix, permutation) - getCost(matrix, solution);
            if (difference < 0) {
                System.out.println(getCost(matrix, solution));
                solution = permutation;
            } else if (Math.random() < Math.pow(Math.E, -difference / currentTemperature)) {
                solution = permutation;
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

    private static List<Integer> getRandomPath(int instanceSize) {
        List<Integer> availableNumbers = NearestNeighbour.getIndexes(instanceSize);
        List<Integer> randomPath = new ArrayList<>(List.of(availableNumbers.remove(0)));
        while (!availableNumbers.isEmpty()) {
            randomPath.add(availableNumbers.remove(Neighbourhood.getRandomIndex(availableNumbers.size(), 0)));
        }
        randomPath.add(0);
        return randomPath;
    }
}
