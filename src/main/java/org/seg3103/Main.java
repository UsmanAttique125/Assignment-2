package org.seg3103;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(".*" + Benchmarking.class.getSimpleName() + ".*")
                .warmupIterations(2)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();

        Options quickSortOptions = new OptionsBuilder()
                .include(".*" + QuickSort.class.getSimpleName() + ".*")
                .warmupIterations(2)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(quickSortOptions).run();

        Options bubbleSortOptions = new OptionsBuilder()
                .include(".*" + BubbleSort.class.getSimpleName() + ".*")
                .warmupIterations(2)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(bubbleSortOptions).run();
    }
}