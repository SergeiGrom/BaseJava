package com.topjava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    public static void main(String[] args) {
        int[] randInts = new Random().ints(10, 1, 10).toArray();
        System.out.println("Values: " + Arrays.toString(randInts).replaceAll("[\\[\\],]", ""));
        System.out.println("MinValue: " + minvalue(randInts));
        System.out.println("Odd or Even: " + oddOrEven(Arrays.stream(randInts).boxed().toList())
                .toString().replaceAll("[\\[\\],]", ""));
    }

    private static int minvalue(int[] values) {
        int[] uniqueValues = Arrays.stream(values).distinct().sorted().toArray();
        int len = uniqueValues.length;
        return IntStream.range(0, len)
                .map(i ->uniqueValues[i] * (int) Math.pow(10, len - 1 - i))
                .sum();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        boolean oddOrEvenSum = integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0;
        return integers.stream()
                .filter(i -> oddOrEvenSum == (i % 2 == 0))
                .collect(Collectors.toList());
    }
}
