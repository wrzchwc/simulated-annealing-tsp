package tsp.annealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Neighbourhood {
    static List<Integer> swap(List<Integer> list) {
        List<Integer> swappedList = new ArrayList<>(list);
        int upperBound = list.size() - 3;
        int firstIndex = getRandomIndex(upperBound);
        int secondIndex = getRandomIndex(upperBound);
        while (firstIndex == secondIndex) {
            secondIndex = getRandomIndex(upperBound);
        }
        Collections.swap(swappedList, firstIndex, secondIndex);
        return swappedList;
    }

    private static int getRandomIndex(int upperBound) {
        return (int) (Math.random() * upperBound + 1);
    }
}
