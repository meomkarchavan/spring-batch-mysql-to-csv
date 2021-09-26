package com.omkar.mysqltocsv.config;

import com.omkar.mysqltocsv.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmp_no(rs.getInt("emp_no"));
        employee.setBirth_date(rs.getDate("birth_date"));
        employee.setFirst_name(rs.getString("first_name"));
        employee.setLast_name(rs.getString("last_name"));
        employee.setGender(rs.getString("gender"));
        employee.setHire_date(rs.getDate("hire_date"));

        return employee;
    }
}
