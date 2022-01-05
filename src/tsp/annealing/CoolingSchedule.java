package tsp.annealing;

public class CoolingSchedule {
    static double geometric(double temperature, int exponent) {
        return temperature * Math.pow(0.99999, exponent);
    }

    static double linear(double temperature, int cycle) {
        return temperature / (1 + 0.001 * cycle);
    }
}
