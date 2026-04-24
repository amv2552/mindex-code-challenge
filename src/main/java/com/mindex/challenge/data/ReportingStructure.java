package com.mindex.challenge.data;

/**
 * Holds the employee with the corresponding number of reports
 * Allows you to get and set the employee and number of reports
 *
 * @author Alexis Vega
 */
public class ReportingStructure{
    // connects to the specific employee
    private Employee employee;
    // an int of reports
    private int numberOfReports;

    public ReportingStructure(){
    }

    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() { return numberOfReports; }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}