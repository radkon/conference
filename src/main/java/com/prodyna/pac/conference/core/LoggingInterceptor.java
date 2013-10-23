package com.prodyna.pac.conference.core;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Logged
@Interceptor
public class LoggingInterceptor {

    private final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        logger.info("ENTER: " + methodName);
        try {
            return context.proceed();
        } finally {
            logger.info("LEAVE: " + methodName);
        }
    }

}
