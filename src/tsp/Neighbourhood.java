package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Neighbourhood {
    static List<Integer> swap(List<Integer> list, int firstIndex, int secondIndex){
        List<Integer> swappedList = new ArrayList<>(list);
        Collections.swap(swappedList, firstIndex, secondIndex);
        return swappedList;
    }
}
