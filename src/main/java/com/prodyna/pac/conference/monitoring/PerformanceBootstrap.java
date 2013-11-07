package com.prodyna.pac.conference.monitoring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Monitored
@Startup
@Singleton
public class PerformanceBootstrap {

    @Inject
    private PerformanceMXBean performanceMxBean;

    @PostConstruct
    public void registerPerformanceMxBean() {
        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(performanceMxBean, getObjectName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void unregisterPerformanceMxBean() {
        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.unregisterMBean(getObjectName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectName getObjectName() throws MalformedObjectNameException {
        return new ObjectName("com.prodyna.pac.conference:type=Performance");
    }

}
