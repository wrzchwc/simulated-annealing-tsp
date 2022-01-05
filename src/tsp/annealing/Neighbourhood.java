package tsp.annealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Neighbourhood {
    static List<Integer> swap(List<Integer> list) {
        List<Integer> permutation = new ArrayList<>(list);
        int upperBound = getUpperBound(list);
        int firstIndex = getRandomIndex(upperBound, 1);
        int secondIndex = getRandomIndex(upperBound, 1);
        while (firstIndex == secondIndex) {
            secondIndex = getRandomIndex(upperBound, 1);
        }
        Collections.swap(permutation, firstIndex, secondIndex);
        return permutation;
    }

    private static int getRandomIndex(int upperBound, int lowerBound) {
        return (int) (Math.random() * upperBound + lowerBound);
    }

    private static int getUpperBound(List<Integer> list) {
        return list.size() - 3;
    }

    static List<Integer> insert(List<Integer> list) {
        List<Integer> permutation = new ArrayList<>(list);
        int upperBound = getUpperBound(list);
        int firstIndex = getRandomIndex(upperBound, 2);
        int secondIndex = getRandomIndex(upperBound, 2);
        while (firstIndex == secondIndex) {
            secondIndex = getRandomIndex(upperBound, 2);
        }
        permutation.add(firstIndex, permutation.remove(secondIndex));
        return permutation;
    }
}
