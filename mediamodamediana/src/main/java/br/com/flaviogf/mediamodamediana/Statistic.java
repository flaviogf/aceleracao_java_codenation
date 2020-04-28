package br.com.flaviogf.mediamodamediana;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    public static int average(int[] numbers) {
        return (int) Arrays.stream(numbers).average().orElse(0);
    }

    public static int mode(int[] numbers) {
        Map<Integer, Integer> counts = new HashMap<>();

        Arrays.stream(numbers).forEach(it -> counts.merge(it, 1, Integer::sum));

        return counts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public static int median(int[] numbers) {
        int[] sorted = Arrays.stream(numbers).sorted().toArray();

        if (numbers.length % 2 != 0) {
            return sorted[numbers.length / 2];
        }

        int from = numbers.length / 2 - 1;

        int to = numbers.length / 2 + 1;

        return average(Arrays.copyOfRange(sorted, from, to));
    }
}
