package com.xncoding.benchmark.sum.calc;

/**
 * Calculator
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
public interface Calculator {
    /**
     * calculate sum of an integer array
     *
     * @param numbers
     * @return
     */
    public long sum(int[] numbers);

    /**
     * shutdown pool or reclaim any related resources
     */
    public void shutdown();
}
