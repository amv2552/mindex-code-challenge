package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.*;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @AutoWired
    private CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Uses a helper function to calculate the number of reports and creates a new ReportingStructure
     * @param id  the ID
     * @return    new ReportingStructure
     */
    @Override
    public ReportingStructure reportStruct(String id){
        Employee employee = employeeRepository.findByEmployeeId(id);
        // emsures the employee exists
        if (employee == null) {
            throw new RuntimeException("Invalid employeeID: " + id);
        }
        // calculates the reports and creates a new reporting structure
        int numberOfReports = calculateNumOfRep(employee);
        return new ReportingStructure(employee, numberOfReports);
    }

    /**
     * Recursively goes through the direct reports of the given employee to get all who report to them
     * @param employee  Whose reports we're looking for
     * @return          number of reports
     */
    private int calculateNumOfRep(Employee employee) {
        int count = 0;
        List<Employee> directReports = employee.getDirectReports();

        if (directReports != null && !directReports.isEmpty()){
            for (Employee dReport : directReports){
                count += 1 + calculateNumOfRep(dReport);
            }
        }

        return count;
    }

    @Override
    public Compensation createComp(String id, int salary, String effectiveDate) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employee id: " + id);
        }
        if (salary < 0) {
            throw new RuntimeException("Salary cannot be negative");
        }

        Compensation compensation = new Compensation(employee, salary, effectiveDate);
        return compensationRepository.insert(compensation);
    }

    @Override
    public Compensation readComp(String id) {
        Compensation comp = compensationRepository.findById(id);
        if (comp == null) {
            throw new RuntimeException("No compensation for employer with id: " + id);
        }

        return comp;
    }

}