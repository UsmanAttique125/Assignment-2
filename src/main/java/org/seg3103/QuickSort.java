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
public class QuickSort {

    @Param({"10", "1000", "10000"}) // Example list sizes
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
        quickSort(numbers, 0, numbers.size() - 1); // Benchmark only the merge sort operation
        return numbers;
    }

    static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {

            int pi = partition(list, low, high);

            // Recursion calls for smaller elements
            // and greater or equals elements
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}