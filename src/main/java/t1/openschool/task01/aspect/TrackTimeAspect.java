package t1.openschool.task01.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import t1.openschool.task01.annotation.TrackAsyncTime;
import t1.openschool.task01.annotation.TrackTime;
import t1.openschool.task01.model.TimeTracker;
import t1.openschool.task01.service.TimeTrackerServiceImpl;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class TrackTimeAspect {

    private final TimeTrackerServiceImpl timeTrackerService;

    @Autowired
    public TrackTimeAspect(TimeTrackerServiceImpl timeTrackerService) {
        this.timeTrackerService = timeTrackerService;
    }

    @Pointcut("@annotation(trackTime)")
    public void pointcut(TrackTime trackTime) {
    }

    @Pointcut("@annotation(trackAsyncTime)")
    public void asyncPointcut(TrackAsyncTime trackAsyncTime) {
    }

    @Around(value = "pointcut(trackTime)", argNames = "proceedingJoinPoint, trackTime")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint, TrackTime trackTime) throws Throwable {
        TimeTracker timeTracker = new TimeTracker();
        long begin = System.currentTimeMillis();
        Object targetMethodResult = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        float executeTime = end - begin;
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        timeTracker.setExecutionTime(executeTime);
        timeTracker.setMethodName(methodName);
        timeTrackerService.save(timeTracker);
        log.info("TrackExecuteTime. Method {} worked by {} ms", methodName, executeTime);
        return targetMethodResult;
    }

    @Around(value = "asyncPointcut(trackAsyncTime)", argNames = "proceedingJoinPoint, trackAsyncTime")
    public Object aroundAsyncMethod(ProceedingJoinPoint proceedingJoinPoint, TrackAsyncTime trackAsyncTime) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeTracker asyncTimeTracker = new TimeTracker();
                long begin = System.currentTimeMillis();
                proceedingJoinPoint.proceed();
                long end = System.currentTimeMillis();
                float executeTime = end - begin;
                String methodName = proceedingJoinPoint.getSignature().toShortString();
                asyncTimeTracker.setExecutionTime(executeTime);
                asyncTimeTracker.setMethodName(methodName);
                timeTrackerService.save(asyncTimeTracker);
                log.info("TrackExecuteTimeAsync. Method {} worked by {} ms", methodName, executeTime);
            } catch (Throwable e) {
                log.error("Throw exception", e);
            }
        });
    }
}
