package com.prodyna.pac.conference.monitoring;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@ApplicationScoped
public class Performance implements PerformanceMXBean {

    final Map<String, PerformanceEntry> entries = new HashMap<>();

    @Override
    public List<PerformanceEntry> getAllEntries() {
        return new ArrayList<>(entries.values());
    }

    @Override
    public void logInvocation(String className, String methodName, long elapsedTime) {
        final String key = className + methodName;
        final PerformanceEntry existingEntry = entries.get(key);
        if (existingEntry == null) {
            entries.put(key, new PerformanceEntry(className, methodName, elapsedTime));
        } else {
            entries.put(key, new PerformanceEntry(existingEntry, elapsedTime));
        }
    }
}
