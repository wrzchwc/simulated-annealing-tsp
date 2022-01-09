package experiment;

import graph.AdjacencyMatrix;
import tsp.annealing.SimulatedAnnealing;

import java.io.*;
import java.util.*;

public class Experiment {
    private static final String SETUP = "src/experiment/setup.txt";
    private static final String RESULTS = "results.txt";
    private static int solution;
    private static AdjacencyMatrix matrix;

    public static void run() {
        Map<String, Integer> setup = getSetup();
        for (String instance : setup.keySet()) {
            solution = setup.get(instance);
            matrix = new AdjacencyMatrix(instance);
            List<Map<Integer, Integer>> results = new ArrayList<>();
            results.add(testCoolingSchedule());
            results.add(testNeighbourhoodSearch());
            results.add(testSolutionChoice());
            results.add(testInitialSolutionAndTemperature());
            saveResults(results, instance);
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

    private static void saveResults(List<Map<Integer, Integer>> list, String instanceName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(RESULTS));
            writer.print(instanceName + " ");
            for (Map<Integer, Integer> map : list) {
                Object key = map.keySet().toArray()[0];
                Object value = map.values().toArray()[0];
                writer.print(key + " " + value + " ");
            }
            writer.println();
            writer.close();
        } catch (IOException e) {
            System.out.println("Data save error has occurred!");
        }
    }

    private static Map<Integer, Integer> testCoolingSchedule() {
        int sumGeometric = 0;
        int sumLinear = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumGeometric += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            } else {
                sumLinear += SimulatedAnnealing.tspSteepest(matrix, false, false, false);
            }
        }

        return Map.of(getError(sumGeometric), getError(sumLinear));
    }

    private static Map<Integer, Integer> testNeighbourhoodSearch() {
        int sumSwap = 0;
        int sumInsert = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumSwap += SimulatedAnnealing.tspSteepest(matrix, true, true, false);
            } else {
                sumInsert += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            }
        }
        return Map.of(getError(sumSwap), getError(sumInsert));
    }

    private static Map<Integer, Integer> testSolutionChoice() {
        int sumGreedy = 0;
        int sumSteepest = 0;

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumGreedy += SimulatedAnnealing.tspGreedy(matrix, false, true, false);
            } else {
                sumSteepest += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            }
        }
        return Map.of(getError(sumGreedy), getError(sumSteepest));
    }

    private static Map<Integer, Integer> testInitialSolutionAndTemperature() {
        int sumNearestNeighbour = 0;
        int sumRandom = 0;
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                sumNearestNeighbour += SimulatedAnnealing.tspSteepest(matrix, false, true, false);
            } else {
                sumRandom += SimulatedAnnealing.tspSteepest(matrix, false, true, true);
            }
        }
        return Map.of(getError(sumNearestNeighbour), getError(sumRandom));
    }

    private static int getError(int sum) {
        return sum / 2 - solution;
    }
}
