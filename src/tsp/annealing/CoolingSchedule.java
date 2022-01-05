package tsp.annealing;

public class CoolingSchedule {
    static double get(double temperature, int cycle, boolean isGeometric) {
        if (isGeometric) {
            return geometric(temperature, cycle);
        }
        return linear(temperature, cycle);
    }

    private static double geometric(double temperature, int exponent) {
        return temperature * Math.pow(0.99999, exponent);
    }

    private static double linear(double temperature, int cycle) {
        return temperature / (1 + 0.001 * cycle);
    }
}
