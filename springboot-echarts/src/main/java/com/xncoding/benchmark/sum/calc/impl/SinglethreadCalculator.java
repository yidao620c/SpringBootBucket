package com.xncoding.benchmark.sum.calc.impl;

import com.xncoding.benchmark.sum.calc.Calculator;

/**
 * SinglethreadCalculator
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
public class SinglethreadCalculator implements Calculator {
    public long sum(int[] numbers) {
        long total = 0L;
        for (int i : numbers) {
            total += i;
        }
        return total;
    }

    @Override
    public void shutdown() {
        // nothing to do
    }
}
