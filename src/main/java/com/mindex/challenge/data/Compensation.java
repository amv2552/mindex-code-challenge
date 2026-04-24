package com.mindex.challenge.data;

/**
 * Connects a salary and effective date with each employee
 * Provides a get and set method for each field
 *
 * @author Alexis Vega
 */
public class Compensation {
    // sets it to this employee
    private Employee employee;
    // the yearly salary should stay an int with no other characters
    private int salary;
    // a string with the format "mm-dd-yyyy"
    private String effectiveDate;

    public Compensation() {
    }

    public Compensation(Employee employee, int salary, String effectiveDate){
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() { return salary; }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() { return effectiveDate; }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}