package com.test.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature( type= Executor.class,  method = "query", args ={
        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
})})
//@Intercepts({@Signature( type= StatementHandler.class,  method = "update", args ={Statement.class})})
public class ExecutorPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("ExecutorPlugin...intercept:\n\t\t" + invocation.getMethod());
        Object[] args = invocation.getArgs();
        MappedStatement ms= (MappedStatement) args[0];
        return invocation.proceed();
    }

    // new4大对象的时候调用，所以4大对象都会被代理到Plugin
    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor) {
            // 我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
            System.out.println("ExecutorPlugin...plugin:\n\t\tmybatis将要包装的对象" + target);
            // 返回为当前target创建的动态代理
            return Plugin.wrap(target, this);
        }
        return target;
    }

    // 加载的时候调用， 设置属性初始化
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        System.out.println("ExecutorPlugin插件配置的信息：" + properties);
    }

}