package tsp.annealing;

public class CoolingSchedule {
    static double geometric(double temperature, double alpha, int exponent) {
        return temperature * Math.pow(alpha, exponent);
    }
}
