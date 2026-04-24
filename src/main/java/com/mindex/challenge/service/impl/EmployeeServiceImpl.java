package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

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
     * 
     * @param id
     * @return
     */
    @Override
    public int reportStruct(String id){
        Employee employee = employeeRepository.findByEmployeeId(id);
        int count = 0;
        // ensures no nonexistent employees are searched for
        if (employee == null) {
            throw new RuntimeException("Invalid employeeID: " + id);
        }

        List<Employee> reports = employee.getDirectReports();
        // will count the number of employees who report to employee entered
        // and search who may report to them
        if (reports != null){
            count += 1;
            for (int i=0; i<reports.size(); i++){
                reportStruct(reports.get(i));
            }
        }

        return count;
    }
}
