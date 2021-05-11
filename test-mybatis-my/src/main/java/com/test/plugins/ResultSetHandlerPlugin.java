package com.test.plugins;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * 完成插件签名：
 *		告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 *	@Intercepts(org.apache.ibatis.plugin.Intercepts)和
 *	签名注解@Signature(org.apache.ibatis.plugin.Signature)，这两个注解用来配置拦截器要拦截的接口的方法。
 *
 *	@Intercepts注解中的属性是一个@Signature（签名）数组，可以在同一个拦截器中同时拦截不同的接口和方法。
 *
 *  @Signature中
 *  	type：设置拦截的接口，可选值是4个：Executor、ParameterHandler、ResultSetHandler、StatementHandler
 *  	method：设置拦截接口中的方法名，需要和接口匹配
 *  	args：设置拦截方法的参数类型数组，通过方法名和参数类型可以确定唯一一个方法
 */
@Intercepts(
		{
			@Signature(type= ResultSetHandler.class,method="handleResultSets",args= Statement.class)
		})
public class ResultSetHandlerPlugin implements Interceptor {

	/**
	 * intercept：拦截： 拦截目标对象的目标方法的执行；
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("ResultSetHandlerPlugin...intercept:\n\t\t" + invocation.getMethod());
		Object target = invocation.getTarget();
		System.out.println("当前拦截到的对象：" + target);
		return invocation.proceed();
	}

	/**
	 * plugin： 包装目标对象的：包装：为目标对象创建一个代理对象
	 */
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		if(target instanceof ResultSetHandler) {
			// 我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
			System.out.println("ResultSetHandlerPlugin...plugin:\n\t\tmybatis将要包装的对象" + target);
			// 返回为当前target创建的动态代理
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/**
	 * setProperties：
	 * 		将插件注册时 的property属性设置进来
	 */
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("ResultSetHandlerPlugin插件配置的信息：" + properties);
	}

}
