package experiment;

import graph.AdjacencyMatrix;
import tsp.annealing.SimulatedAnnealing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Experiment {
    private static final String SETUP = "src/experiment/setup.txt";
    private static final String RESULTS = "results.txt";

    public static void run() {
        Map<String, Integer> setup = getSetup();
        for (String instance : setup.keySet()) {
            int solution = setup.get(instance);
            AdjacencyMatrix matrix = new AdjacencyMatrix(instance);
            List<Map<Integer, Integer>> results = new ArrayList<>();
            results.add(testCoolingSchedule(matrix, solution));
            results.add(testNeighbourhoodSearch(matrix, solution));
            results.add(testSolutionChoice(matrix, solution));
            results.add(testInitialSolutionAndTemperature(matrix, solution));
        }

    }

    private static Map<String, Integer> getSetup() {
        Map<String, Integer> setup = new HashMap<>();
        File file = new File(SETUP);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String[] line = input.nextLine().split(" ");
                setup.put(line[0], Integer.parseInt(line[1]));
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Setup file not found!");
        }
        return setup;
    }

    private static void saveResults() {
    }

    private static Map<Integer, Integer> testCoolingSchedule(AdjacencyMatrix matrix, int solution) {
        int sumGeometric = 0;
        int sumLinear = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumGeometric += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            } else {
                sumLinear += SimulatedAnnealing.tspSteepest(matrix, false, false, false);
            }
        }

        return Map.of(getError(sumGeometric, solution), getError(sumLinear, solution));
    }

    private static Map<Integer, Integer> testNeighbourhoodSearch(AdjacencyMatrix matrix, int solution) {
        int sumSwap = 0;
        int sumInsert = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumSwap += SimulatedAnnealing.tspSteepest(matrix, true, true, false);
            } else {
                sumInsert += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            }
        }
        return Map.of(getError(sumSwap, solution), getError(sumInsert, solution));
    }

    private static Map<Integer, Integer> testSolutionChoice(AdjacencyMatrix matrix, int solution) {
        int sumGreedy = 0;
        int sumSteepest = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumGreedy += SimulatedAnnealing.tspGreedy(matrix, false, true, false);
            } else {
                sumSteepest += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            }
        }
        return Map.of(getError(sumGreedy, solution), getError(sumSteepest, solution));
    }

    private static Map<Integer, Integer> testInitialSolutionAndTemperature(AdjacencyMatrix matrix, int solution) {
        int sumNearestNeighbour = 0;
        int sumRandom = 0;
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumNearestNeighbour += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            } else {
                sumRandom += SimulatedAnnealing.tspSteepest(matrix, false, true, true);
            }
        }
        return Map.of(getError(sumNearestNeighbour, solution), getError(sumRandom, solution));
    }

    private static int getError(int sum, int solution) {
        return sum / 2 - solution;
    }
}
