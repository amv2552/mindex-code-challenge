package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Holds the compensatino of each employee and can be searched by the employees id
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findByEmployeeId(String employeeId);
}