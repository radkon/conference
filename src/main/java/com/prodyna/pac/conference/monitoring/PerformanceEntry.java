package com.prodyna.pac.conference.monitoring;

import java.io.Serializable;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class PerformanceEntry implements Serializable {

    private final String className;
    private final String methodName;

    private final long numberOfCalls;
    private final long averageTime;
    private final long totalTime;

    public PerformanceEntry(String className, String methodName, long elapsedTime) {
        this.className = className;
        this.methodName = methodName;
        this.totalTime = elapsedTime;
        this.numberOfCalls = 1;
        this.averageTime = elapsedTime;
    }

    public PerformanceEntry(PerformanceEntry existingEntry, long elapsedTime) {
        this.className = existingEntry.getClassName();
        this.methodName = existingEntry.getMethodName();
        this.totalTime = (existingEntry.getTotalTime() + elapsedTime);
        this.numberOfCalls = existingEntry.getNumberOfCalls() + 1;
        this.averageTime = totalTime / numberOfCalls;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformanceEntry that = (PerformanceEntry) o;

        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        return result;
    }
}
