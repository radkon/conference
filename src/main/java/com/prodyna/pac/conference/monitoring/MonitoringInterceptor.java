package com.prodyna.pac.conference.monitoring;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Monitored
@Interceptor
public class MonitoringInterceptor {

    private final Logger logger = Logger.getLogger(MonitoringInterceptor.class.getName());

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
            logger.info("Call to " + className + "." + methodName + parameterList + " took " + (System
                    .currentTimeMillis() - startTime) + " ms.");
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
