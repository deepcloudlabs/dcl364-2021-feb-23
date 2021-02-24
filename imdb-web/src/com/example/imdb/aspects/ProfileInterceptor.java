package com.example.imdb.aspects;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Profile
public class ProfileInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var start = System.nanoTime();
		var result = ic.proceed();
		var stop = System.nanoTime();
		String methodName = ic.getMethod().getName();
		System.err.println(methodName + " runs " + (stop-start) + " ns.");
		return result;
	}
}
