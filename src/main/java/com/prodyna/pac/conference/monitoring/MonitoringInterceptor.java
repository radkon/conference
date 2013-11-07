package com.prodyna.pac.conference.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Monitored
@Interceptor
public class MonitoringInterceptor {

    private final Logger logger = LoggerFactory.getLogger(Performance.class);

    @Inject
    private PerformanceMXBean performance;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        final Method method = context.getMethod();
        final String className = method.getDeclaringClass().getName();
        final String methodName = context.getMethod().getName();
        final String parameterList = buildParameterList(context);
        final long startTime = System.currentTimeMillis();
        final Object result;
        try {
            result = context.proceed();
        } finally {
            final long elapsedTime = System.currentTimeMillis() - startTime;
            performance.logInvocation(className, methodName, elapsedTime);
            logger.info("Call to " + className + "." + methodName + parameterList + " took " + elapsedTime + " ms.");
        }
        return result;
    }

    private String buildParameterList(InvocationContext context) {
        final StringBuilder parameterListBuilder = new StringBuilder("(");
        final Object[] parameters = context.getParameters();
        for (int i = 0, length = parameters.length; i < length; i++) {
            parameterListBuilder.append(parameters[i].toString());
            if (length > 1 && i < length) {
                parameterListBuilder.append(", ");
            }
        }
        return parameterListBuilder.append(")").toString();
    }

}
