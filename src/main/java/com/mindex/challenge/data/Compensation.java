package com.mindex.challenge.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Connects a salary and effective date with each employee
 * Provides a get and set method for each field
 *
 * @author Alexis Vega
 */
public class Compensation {
    @Id
    private String id;
    // sets it to this employee
    private String employeeId;
    // the yearly salary should stay an int with no other characters
    private int salary;
    // a string with the format "mm-dd-yyyy"
    private String effectiveDate;

    public Compensation() {
    }

    public Compensation(String employeeId, int salary, String effectiveDate){
        this.employeeId = employeeId;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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