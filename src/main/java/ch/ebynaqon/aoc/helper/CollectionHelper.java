package ch.ebynaqon.aoc.helper;

import java.util.ArrayList;
import java.util.List;

public class CollectionHelper {
    public static List<Integer> dropValueAtIndex(List<Integer> list, int indexToDrop) {
        List<Integer> listWithLevelRemoved = new ArrayList<>(list.size() - 1);
        for (int j = 0; j < list.size(); j++) {
            if (indexToDrop != j) {
                listWithLevelRemoved.add(list.get(j));
            }
        }
        return listWithLevelRemoved;
    }
}
