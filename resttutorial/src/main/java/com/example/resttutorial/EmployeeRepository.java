package com.example.resttutorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Create new Employees
    // Update existing ones
    // Delete Employees
    // Find Employees(one, all, or search by simple or complex properties)

    
}
