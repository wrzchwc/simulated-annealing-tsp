import experiment.Experiment;
import graph.AdjacencyMatrix;
import tsp.annealing.SimulatedAnnealing;

import java.util.Scanner;

public class Main {
    private static void printHeader() {
        System.out.println("Effective Algorithms Design");
        System.out.println("Project 4 - TSP using Simulated annealing algorithm");
        System.out.println("Jakub Wierzchowiec, January 2022");
    }

    private static int getMode() {
        Scanner input = new Scanner(System.in);
        System.out.println("0 Quit");
        System.out.println("1 Demonstrate");
        System.out.println("2 Execute");
        return input.nextInt();
    }

    private static boolean getAlgorithm() {
        Scanner input = new Scanner(System.in);
        System.out.println("0 Back");
        System.out.println("1 Simulated annealing");
        return input.nextLine().equals("1");
    }

    private static String getFilepath() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter path to file containing instance: ");
        return input.nextLine();
    }

    private static void demonstrate() {
        AdjacencyMatrix matrix = new AdjacencyMatrix(getFilepath());
        System.out.println("Result: " + SimulatedAnnealing.tspSteepest(matrix, false, true, false));
    }

    public static void main(String[] args) {
        printHeader();
        while (true) {
            int mode = getMode();

            if (mode == 0) {
                break;
            } else if (mode == 1) {
                while (true) {
                    if (getAlgorithm()) {
                        demonstrate();
                    } else {
                        break;
                    }
                }
            } else if (mode == 2) {
                Experiment.run();
            }
        }
    }
}
