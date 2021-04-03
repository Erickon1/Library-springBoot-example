package mx.erick.library.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger LOGGER =
			LogManager.getLogger(LoggerAspect.class);
	
	@Before("execution(* mx.erick.library.service..*(..))")
	public void before(JoinPoint joinPoint)throws Throwable{
		
		MethodSignature methodSignature = 
				(MethodSignature)joinPoint.getSignature();
		String[] paramNames = methodSignature.getParameterNames();
		Object[] listArgs = joinPoint.getArgs();
		String end = "";
		for (int i = 0; i < listArgs.length; i++) {
			if (i!=0) {
				end += ", ";
			}
			String argEnd = "null"; 
			if (listArgs[i]!=null) {
				argEnd = listArgs[i].toString();
			}
			end += paramNames[i]+": "+argEnd;			
		}
		LOGGER.info( getName(methodSignature)+" << "+end);
		
	}
	
	@SuppressWarnings("unchecked")
	@AfterReturning(
			pointcut = "execution(* mx.erick.library.service..*(..))",
			returning = "objectReturn" )
	public void after( JoinPoint joinPoint,
			Object objectReturn )throws Throwable{
	
		MethodSignature methodSignature = 
				(MethodSignature)joinPoint.getSignature();
				
        if (methodSignature.getReturnType().toString().equals(
        		"interface org.springframework.data.domain.Page")) {
        	objectReturn = ((PageImpl<Object>) objectReturn).getContent();
		}
        LOGGER.info( getName(methodSignature)+ " >> " + objectReturn );       

		
	}
	

	@AfterThrowing(
			pointcut = "execution(* mx.erick.library.service..*(..))",
			throwing = "ex" )
	public void errors( Exception ex )throws Throwable{
		LOGGER.error(ex.getClass()+" ERROR "+ex.getMessage());
	}
	
	private String getName(MethodSignature methodSignature) {
		String className = methodSignature.
				getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();
		return className + "." + methodName;
	}
	
}
