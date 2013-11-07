package com.prodyna.pac.conference.monitoring;

import java.io.Serializable;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class PerformanceValue implements Serializable {

    private long numberOfCalls;
    private long averageTime;
    private long totalTime;

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(long numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
