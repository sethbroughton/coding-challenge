package com.newrelic.codingchallenge.Log;

import java.util.concurrent.atomic.AtomicInteger;

public class FileLogger {
    private AtomicInteger uniqueCounter = new AtomicInteger(0);
    private AtomicInteger duplicateCounter = new AtomicInteger(0);
    private AtomicInteger uniqueTotal = new AtomicInteger(0);

    public AtomicInteger getUniqueCounter() {
        return uniqueCounter;
    }

    public AtomicInteger getDuplicateCounter() {
        return duplicateCounter;
    }

    public AtomicInteger getUniqueTotal() {
        return uniqueTotal;
    }

    public String getMetrics() {
        return "Received " + getUniqueCounter() + " unique numbers, "
        + getDuplicateCounter() + " duplicates. Unique total: "
                + getUniqueTotal();
    }

    public void resetPeriodCounters() {
        getUniqueCounter().set(0);
        getDuplicateCounter().set(0);
    }
}
