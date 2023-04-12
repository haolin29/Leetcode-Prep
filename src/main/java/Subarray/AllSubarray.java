package Subarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubarray {

    public static List<List<Integer>> findAllSubarray(List<Integer> arr) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                res.add(arr.subList(i, j + 1));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1,2,3,4,5);

        for (List<Integer> list : findAllSubarray(input)) {
            for (int num : list) {
                System.out.print(num + " ");
            }

            System.out.println();
        }
    }
}
