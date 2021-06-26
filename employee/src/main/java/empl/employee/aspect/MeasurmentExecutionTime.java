package empl.employee.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class MeasurmentExecutionTime {

    Logger log = Logger.getLogger(MeasurmentExecutionTime.class.getName());

    /**
     * Measures execution time of metods in service package
     */
      @Around("execution(public * com.capgemini.jstk.employee.service..*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Measruing time in class" + joinPoint.getSignature().getDeclaringTypeName() + ", In mehtod: " + joinPoint.getSignature().getName() + ". " +
                "Measured time: " + (end - start) + "ms");
        return object;
    }
}
