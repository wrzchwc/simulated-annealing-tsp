package tsp.annealing;

import graph.AdjacencyMatrix;
import tsp.NearestNeighbour;

import java.util.*;


public class SimulatedAnnealing {
    private static int size;
    private static AdjacencyMatrix matrix;
    private static long timeLimit;
    private static double currentTemperature;

    public static int tspGreedy(AdjacencyMatrix matrix, boolean isSwap, boolean isGeometric, boolean isRandom) {
        setStaticAttributes(matrix);

        List<Integer> solution = getInitialSolution(isRandom);
        double initialTemperature = getInitialTemperature(solution, isRandom);
        currentTemperature = initialTemperature;

        long initialTime = System.currentTimeMillis();
        for (int i = 0; System.currentTimeMillis() - initialTime < timeLimit; i++) {
            for (int j = 0; j < size / 2; j++) {
                List<Integer> permutation = Neighbourhood.get(solution, isSwap);
                solution = evaluatePermutation(permutation, solution);
            }
            currentTemperature = CoolingSchedule.get(initialTemperature, i, isGeometric);
        }
        return getCost(solution);
    }

    public static int tspSteepest(AdjacencyMatrix matrix, boolean isSwap, boolean isGeometric, boolean isRandom) {
        setStaticAttributes(matrix);

        List<Integer> solution = getInitialSolution(isRandom);
        double initialTemperature = getInitialTemperature(solution, isRandom);
        currentTemperature = initialTemperature;

        long initialTime = System.currentTimeMillis();
        for (int i = 0; System.currentTimeMillis() - initialTime < timeLimit; i++) {
            Map<Integer, List<Integer>> solutionsInNeighbourhood = new HashMap<>();
            List<Integer> permutation;
            for (int j = 0; j < size / 2; j++) {
                permutation = Neighbourhood.get(solution, isSwap);
                solutionsInNeighbourhood.put(getCost(permutation), permutation);
            }
            permutation = solutionsInNeighbourhood.get(Collections.min(solutionsInNeighbourhood.keySet()));
            solution = evaluatePermutation(permutation, solution);
            currentTemperature = CoolingSchedule.get(initialTemperature, i, isGeometric);
        }
        return getCost(solution);
    }

    private static int getCost(List<Integer> list) {
        int cost = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            cost += matrix.getData(list.get(i), list.get(i + 1));
        }
        return cost;
    }

    private static long getTimeLimit() {
        return size * 880L + 71689;
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

    private static double getInitialTemperature(List<Integer> solution, boolean isRandom) {
        double multiplier = isRandom ? 1.5 : 2.0;
        return multiplier * getCost(solution);
    }

    private static List<Integer> getInitialSolution(boolean isRandom) {
        if (isRandom) {
            return getRandomPath(matrix.getSize());
        }
        return NearestNeighbour.get(matrix);
    }

    private static List<Integer> evaluatePermutation(List<Integer> permutation, List<Integer> solution) {
        double difference = getCost(permutation) - getCost(solution);
        if (difference < 0) {
            System.out.println(getCost(permutation));
            return permutation;
        } else if (Math.random() < Math.pow(Math.E, -difference / currentTemperature)) {
            return permutation;
        }
        return solution;
    }

    private static void setStaticAttributes(AdjacencyMatrix matrix) {
        SimulatedAnnealing.matrix = matrix;
        SimulatedAnnealing.size = matrix.getSize();
        SimulatedAnnealing.timeLimit = getTimeLimit();
    }
}
