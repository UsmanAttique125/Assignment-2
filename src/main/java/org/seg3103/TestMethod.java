package org.seg3103;
import org.openjdk.jmh.*;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)

public class TestMethod {

    @Param({"10"})
    public int listSize;

    private ArrayList<Integer> numbers;

    @Setup(Level.Iteration)
    public void setup() {
        // Hardcoding a specific list of numbers for consistent benchmarking
        numbers = new ArrayList<>(List.of(7, 3, 9, 1, 5, 2, 8, 4, 10, 6));
    }

    @Benchmark
    public List<Integer> sortRandomNumbers() {
        Collections.sort(numbers);
        return numbers;
    }
}
