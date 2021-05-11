package com.test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import com.test.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.test.pojo.Employee;
import org.junit.Test;


public class TestMybatis {

    @Test
    public void test1() throws IOException {

        // 1、根据mybatis全局配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        // 使用MyBatis提供的Resources类加载mybatis的配置文件，获取输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSession的工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2、从SqlSession工厂中，获取sqlsession，用来执行sql
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 查询selectOne
            // @param statement Unique identifier matching the statement to use.      一个唯一标识
            // @param parameter A parameter object to pass to the statement.        参数
            Employee employee = (Employee) session.selectOne("com.test.mapper.EmployeeMapper.getEmployeeById", 1);

            Employee employee2  = session.getMapper(EmployeeMapper.class).getEmployeeById(1);
            // 输出信息
            System.out.println(employee);
            System.out.println(employee2);
        } finally {
            // 关闭session
            session.close();
        }
    }


    @Test
    public void test2() throws IOException {
        // 1、根据mybatis全局配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        // 使用MyBatis提供的Resources类加载mybatis的配置文件，获取输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSession的工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2、从SqlSession工厂中，获取sqlsession，用来执行sql
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现对象
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById(1);
            // 输出信息
            System.out.println(mapper);
            System.out.println(employee);
        } finally {
            // 关闭session
            session.close();
        }
    }

}