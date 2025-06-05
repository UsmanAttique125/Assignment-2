package org.seg3103;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class Benchmarking {

    @Param({"10", "1000", "100000"}) // Example list sizes
    public int listSize;

    private ArrayList<Integer> numbers;

    @Setup(Level.Iteration)
    public void setup() {
        numbers = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt()); // Generate random numbers and insert them into the ArrrayList, this is not benchmarked
        }
    }

    @Benchmark
    public List<Integer> sortWithSpecifiedSortingAlgorithm() {
        mergeSort(numbers); // Benchmark only the merge sort operation
        return numbers;
    }

    private static void mergeSort(List<Integer> list) {
        if (list.size() < 2) return;
        int mid = list.size() / 2;
        List<Integer> left = new ArrayList<>(list.subList(0, mid));
        List<Integer> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    private static void merge(List<Integer> list, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}