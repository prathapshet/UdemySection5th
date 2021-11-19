package com.zkteco.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.employee.dto.EmployeeDTO;
import com.zkteco.employee.dto.ResultDTO;
import com.zkteco.employee.exception.EmployeeNotFoundException;
import com.zkteco.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/employee")
@Api(value = "Employee Application", description = "Show Employee Data")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws EmployeeNotFoundException
	 */

	@GetMapping
	@ApiOperation(value = "Returns Employee Data By List")
	public ResultDTO findPaginated(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "5") int pageSize) throws EmployeeNotFoundException {
		return employeeService.getAllEmployeeData(pageNumber, pageSize);
	}

	/**
	 * 
	 * @param empId
	 * @return
	 * @throws EmployeeNotFoundException
	 */

	@GetMapping("{id}")
	@ApiOperation(value = "Returns Employee Data By Id")
	public ResultDTO fetchEmployeeById(@PathVariable("id") String empId) throws EmployeeNotFoundException {

		return employeeService.fetchEmployeeById(empId);
	}

	/**
	 * 
	 * @param employeeDTO
	 * @return
	 * @throws EmployeeNotFoundException 
	 */

	@PostMapping
	@ApiOperation(value = "Fetch the Employee Data")
	public ResultDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException {

		return employeeService.saveEmployee(employeeDTO);
	}

	/**
	 * 
	 * @param empId
	 * @param dto
	 * @return
	 * @throws EmployeeNotFoundException
	 */

	@PutMapping("{id}")
	@ApiOperation(value = "Update the Employee Data By Id")
	public ResultDTO updateEmployee(@PathVariable("id") String empId, @RequestBody EmployeeDTO dto)
			throws EmployeeNotFoundException {

		return employeeService.updateEmployee(empId, dto);
	}

	/**
	 * 
	 * @param empId
	 * @return
	 * @throws EmployeeNotFoundException
	 */

	@DeleteMapping("{id}")
	@ApiOperation(value = "Delete the Employee Data By Id")
	public ResultDTO deleteEmployeeById(@PathVariable("id") String empId) throws EmployeeNotFoundException {

		return employeeService.deleteEmployeeById(empId);
	}

	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param empId
	 * @param empName
	 * @param empAddress
	 * @param empAge
	 * @param empSalary
	 * @param empDept
	 * @param empDesignation
	 * @param empContactNumber
	 * @param empEmail
	 * @param createdAt
	 * @param updatedAt
	 * @return
	 * @throws EmployeeNotFoundException
	 */

	@GetMapping("/filter")
	@ApiOperation(value = "Returns Employee Data By Filter")
	public ResultDTO findPaginated(@RequestParam(defaultValue = "1") int pageNumber,
			@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "") String search,
			@RequestParam(required = false, defaultValue = "") String empId,
			@RequestParam(required = false, defaultValue = "") String empName,
			@RequestParam(required = false, defaultValue = "") String empAddress,
			@RequestParam(required = false, defaultValue = "") String empAge,
			@RequestParam(required = false, defaultValue = "") String empSalary,
			@RequestParam(required = false, defaultValue = "") String empDept,
			@RequestParam(required = false, defaultValue = "") String empDesignation,
			@RequestParam(required = false, defaultValue = "") String empContactNumber,
			@RequestParam(required = false, defaultValue = "") String empEmail)
		//	@RequestParam(required = false, defaultValue = "") String createdAt,
	   //		@RequestParam(required = false, defaultValue = "") String updatedAt) 
	throws EmployeeNotFoundException {

		return employeeService.getAllEmployeeFilter(pageNumber, pageSize, search, empId, empName, empAddress, empAge,
				empSalary, empDept, empDesignation, empContactNumber, empEmail);
	}

}