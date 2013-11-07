package com.prodyna.pac.conference.monitoring;

import java.io.Serializable;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class PerformanceKey implements Serializable {

    private String className;
    private String methodName;

    public PerformanceKey(String className, String methodName, long elapsedTime) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformanceKey that = (PerformanceKey) o;

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
