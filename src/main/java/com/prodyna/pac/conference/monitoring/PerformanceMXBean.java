package com.prodyna.pac.conference.monitoring;

import javax.management.MXBean;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@MXBean
public interface PerformanceMXBean {

    List<PerformanceEntry> getAllEntries();

    void logInvocation(String className, String methodName, long elapsedTime);


}
