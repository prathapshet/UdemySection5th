package com.zkteco.employee.service;

import java.util.List;

import com.zkteco.employee.dto.EmployeeDTO;
import com.zkteco.employee.dto.ResultDTO;
import com.zkteco.employee.entity.Employee;
import com.zkteco.employee.exception.EmployeeNotFoundException;

public interface EmployeeService {

	public EmployeeDTO entityToDto(Employee employee);

	public List<EmployeeDTO> entityToDto(List<Employee> employee);

	public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO);

	public Employee dtoToEntity(EmployeeDTO employeeDTO);

	public ResultDTO getAllEmployeeData(int pageNumber, int pageSize) throws EmployeeNotFoundException;

	public ResultDTO fetchEmployeeById(String empId) throws EmployeeNotFoundException;

	public ResultDTO saveEmployee(EmployeeDTO employeeDTO) throws EmployeeNotFoundException;

	public ResultDTO updateEmployee(String empId, EmployeeDTO dto) throws EmployeeNotFoundException;

	public ResultDTO deleteEmployeeById(String empId) throws EmployeeNotFoundException;

	public ResultDTO getAllEmployeeFilter(int pageNumber, int pageSize, String search, String empId, String empName,
			String empAddress, String empAge, String empSalary, String empDept, String empDesignation,
			String empContactNumber, String empEmail)
			throws EmployeeNotFoundException;

}