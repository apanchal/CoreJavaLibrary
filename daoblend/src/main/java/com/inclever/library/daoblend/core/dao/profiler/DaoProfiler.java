package com.inclever.library.daoblend.core.dao.profiler;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.inclever.library.logging.LogManagerFactory;

@Component
@Aspect
public class DaoProfiler {

    private static final Logger logger = LogManagerFactory.getInstance().getLogger(DaoProfiler.class);

    private static boolean isAuditOn = LogManagerFactory.getInstance().getCoreConfiguration().isAuditOn();

    public DaoProfiler() {
        super();

    }

    @Pointcut("execution(* com.nividous.library.daoblend.core.dao.impl.GenericJPADAOImpl.*(..))")
    public void daoMethods() {
    }

    @Before("daoMethods()")
    public void beforOperation(JoinPoint joinPoint) throws Throwable {
        logger.trace("Entered in {} method.", joinPoint.getSignature().getName());
    }

    @Around("daoMethods()")
    public Object profileOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = 0;
        long elapsedTime;
        if (isAuditOn) {
            startTime = System.nanoTime();
        }
        logger.trace("is Transaction Active? {}.", TransactionSynchronizationManager.isActualTransactionActive());
        Object output = proceedingJoinPoint.proceed();
        if (isAuditOn) {
            elapsedTime = System.nanoTime() - startTime;
            logger.trace("\nMethod Execution Time: {} ms.", (MILLISECONDS.convert(elapsedTime, NANOSECONDS)));
        }
        return output;

    }

    @After("daoMethods()")
    public void afterOperation(JoinPoint joinPoint) throws Throwable {
        logger.trace("Exiting from {} method.", joinPoint.getSignature().getName());
    }

}
