package com.test.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

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
			@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
		})
public class StatementHandlerPlugin implements Interceptor {

	/**
	 * intercept：拦截： 拦截目标对象的目标方法的执行；
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("StatementHandlerPlugin...intercept:\n\t\t" + invocation.getMethod());
		Object target = invocation.getTarget();
		System.out.println("\t\t当前拦截到的对象：" + target);
		// 拿到：StatementHandler==>ParameterHandler===>parameterObject
		// 拿到target的元数据
		MetaObject metaObject = SystemMetaObject.forObject(target);
		Object value = metaObject.getValue("parameterHandler.parameterObject");
		System.out.println("\t\tsql语句用的参数是：" + value);
//		System.out.println("\t\t修改称参数参数是：" + 3);
		// 修改完sql语句要用的参数
//		metaObject.setValue("parameterHandler.parameterObject", 3);
		// 执行目标方法
		Object proceed = invocation.proceed();
		// 返回执行后的返回值
		return proceed;
	}

	/**
	 * plugin： 包装目标对象的：包装：为目标对象创建一个代理对象
	 */
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		if(target instanceof StatementHandler) {
			// 我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
			System.out.println("StatementHandlerPlugin...plugin:\n\t\tmybatis将要包装的对象" + target);
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
		System.out.println("StatementHandlerPlugin插件配置的信息：" + properties);
	}

}
