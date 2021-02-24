package com.example.imdb.aspects;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Audit
public class AuditInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		Date now = new Date();
		String methodName = ic.getMethod().getName();
		System.err.println(methodName + " is called at " + now);
		System.err.println("Parameters are " + Arrays.toString(ic.getParameters()));
		var result = ic.proceed();
		System.err.println(methodName + " returns " + result);
		return result;
	}
}
