package org.seg3103;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class BubbleSort {

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
        bubbleSort(numbers); // Benchmark only the merge sort operation
        return numbers;
    }

    private static void bubbleSort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Swap elements
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}