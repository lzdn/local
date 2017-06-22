package org.lzdn.platform.filter;

import java.lang.reflect.Method;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RunTimeLogAdvice implements MethodInterceptor {

	private static final Log logger = LogFactory.getLog(RunTimeLogAdvice.class);

	private long minTime;

	public RunTimeLogAdvice() {
		this.minTime = 100L;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = invocation.proceed();
		long time = System.currentTimeMillis() - start;
		if ((time > this.minTime) && (logger.isInfoEnabled())) {
			StringBuilder builder = new StringBuilder();
			builder.append("ElapsedTime=");
			builder.append(time).append(" Method=");
			builder.append(methodToString(invocation.getMethod()));
			if (logger.isDebugEnabled()) {
				builder.append(" Args={");
				Object[] args = invocation.getArguments();
				if (args != null)
					for (int i = 0; i < args.length; ++i) {
						Object arg = args[i];
						if (arg == null)
							builder.append("null");
						else if ((arg.getClass().isPrimitive()) || (arg instanceof Number) || (arg instanceof String)
								|| (arg instanceof Date) || (arg instanceof Boolean))
							builder.append(arg);
						else
							builder.append(ToStringBuilder.reflectionToString(arg));

						if (i < args.length - 1)
							builder.append(',');
					}

				builder.append('}');
				logger.debug(builder.toString());
			} else {
				logger.info(builder.toString());
			}
		}
		return result;
	}

	private static String getTypeName(Class type) {
		if (type.isArray())
			try {
				Class cl = type;
				int dimensions = 0;
				while (cl.isArray()) {
					++dimensions;
					cl = cl.getComponentType();
				}
				StringBuilder sb = new StringBuilder();
				sb.append(cl.getName());
				for (int i = 0; i < dimensions; ++i)
					sb.append("[]");

				return sb.toString();
			} catch (Throwable e) {
				logger.warn(e);
			}

		return type.getName();
	}

	private String methodToString(Method method) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(getTypeName(method.getDeclaringClass())).append('.');
			sb.append(method.getName()).append('(');
			Class[] params = method.getParameterTypes();
			for (int j = 0; j < params.length; ++j) {
				sb.append(getTypeName(params[j]));
				if (j < params.length - 1)
					sb.append(',');
			}

			sb.append(')');
			return sb.toString();
		} catch (Exception e) {
			return "<" + e + ">";
		}

	}

}
