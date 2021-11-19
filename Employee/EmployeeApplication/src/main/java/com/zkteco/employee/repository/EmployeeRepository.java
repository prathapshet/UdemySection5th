package com.zkteco.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.zkteco.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Query(value = "Select t from Employee t where t.empName like ?1 OR t.empAddress like ?1 OR t.empAge like ?1 OR t.empSalary like?1 OR t.empDept like ?1 OR t.empDesignation like ?1 OR t.empContactNumber like ?1 OR t.empEmail like ?1")
	Page<Employee> employeeContaining(String search, Pageable pageable);

	
}
