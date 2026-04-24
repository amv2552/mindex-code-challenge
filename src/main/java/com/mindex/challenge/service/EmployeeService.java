package com.mindex.challenge.service;

import com.mindex.challenge.data.*;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    // function for reporting structure
    ReportingStructure reportStruct(String id);
    // creates the compensation for the employee with the given id
    Compensation createComp(String id, int salary, String effectiveDate);
    // reads the compensation for the given employee
    Compensation readComp(String id);
}