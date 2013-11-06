package com.prodyna.pac.conference.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Monitored
@Interceptor
public class MonitoringInterceptor {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
            log.info("Call to {}.{}{} took {} ms.", className, methodName, parameterList, (System
                    .currentTimeMillis() - startTime));
        }
        return result;
    }

    private String buildParameterList(InvocationContext context) {
        final StringBuilder parameterListBuilder = new StringBuilder("(");
        final Object[] parameters = context.getParameters();
        if (parameters != null) {
            for (int i = 0, length = parameters.length; i < length; i++) {
                parameterListBuilder.append(parameters[i].toString());
                if (length > 1 && i < length) {
                    parameterListBuilder.append(", ");
                }
            }
        }
        return parameterListBuilder.append(")").toString();
    }

}
