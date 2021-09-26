package com.omkar.mysqltocsv.batch;

import com.omkar.mysqltocsv.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {


    @Override
    public Employee process(Employee employee) {
        return employee;
    }
}