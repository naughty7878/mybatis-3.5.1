package com.test.mapper;

import com.test.pojo.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    
    public Employee getEmployeeById(@Param("id") Integer id);

}