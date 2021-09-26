package com.omkar.mysqltocsv.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Employee {
    @Id
    private Integer emp_no;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birth_date;
    private Date hire_date;

    public Employee() {
    }

    public Employee(Integer emp_no, String first_name, String last_name, String gender, Date birth_date, Date hire_date) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
    }

    public Integer getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", birth_date=" + birth_date +
                ", hire_date=" + hire_date +
                '}';
    }
}
