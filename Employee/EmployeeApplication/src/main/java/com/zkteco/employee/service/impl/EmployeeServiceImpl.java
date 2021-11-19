package com.zkteco.employee.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zkteco.employee.dto.EmployeeDTO;
import com.zkteco.employee.dto.ResultDTO;
import com.zkteco.employee.entity.Employee;
import com.zkteco.employee.exception.EmployeeNotFoundException;
import com.zkteco.employee.repository.EmployeeRepository;
import com.zkteco.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	public EmployeeDTO entityToDto(Employee employee) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmpId(employee.getEmpId());
		employeeDTO.setEmpName(employee.getEmpName());
		employeeDTO.setEmpAge(employee.getEmpAge());
		employeeDTO.setEmpAddress(employee.getEmpAddress());
		employeeDTO.setEmpContactNumber(employee.getEmpContactNumber());
		employeeDTO.setEmpDesignation(employee.getEmpDesignation());
		employeeDTO.setEmpEmail(employee.getEmpEmail());
		employeeDTO.setEmpSalary(employee.getEmpSalary());
		employeeDTO.setEmpDept(employee.getEmpDept());

		return employeeDTO;

	}

	public List<EmployeeDTO> entityToDto(List<Employee> employee) {

		return employee.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	public Employee dtoToEntity(EmployeeDTO employeeDTO) {

		Employee employee = new Employee();
		employee.setEmpId(employeeDTO.getEmpId());
		employee.setEmpName(employeeDTO.getEmpName());
		employee.setEmpAge(employeeDTO.getEmpAge());
		employee.setEmpAddress(employeeDTO.getEmpAddress());
		employee.setEmpContactNumber(employeeDTO.getEmpContactNumber());
		employee.setEmpDept(employeeDTO.getEmpDept());
		employee.setEmpDesignation(employeeDTO.getEmpDesignation());
		employee.setEmpEmail(employeeDTO.getEmpEmail());
		employee.setEmpSalary(employeeDTO.getEmpSalary());

		return employee;

	}

	public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO) {

		return employeeDTO.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

	@Override
	public ResultDTO getAllEmployeeData(int pageNumber, int pageSize) throws EmployeeNotFoundException {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> page = employeeRepository.findAll(pageable);

		List<Employee> list = new ArrayList<>();

		for (Employee employee : page) {
			list.add(employee);
		}
		if (list.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Data Not Available");
		}
		else {
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.setCode("EMP-01");
			resultDTO.setMessage("Employee Data Available for the list below");
			resultDTO.setData(list);
			return resultDTO;
		} 

	}

	@Override
	public ResultDTO getAllEmployeeFilter(int pageNumber, int pageSize, String search, String empId, String empName,
			String empAddress, String empAge, String empSalary, String empDept, String empDesignation,
			String empContactNumber, String empEmail)
			throws EmployeeNotFoundException {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Employee> pageTuts;
		if (search == null) {
			pageTuts = employeeRepository.findAll(pageable);
		} else {
			pageTuts = employeeRepository.employeeContaining(search, pageable);
		}

		List<Employee> list = new ArrayList<>();

		for (Employee emp : pageTuts) {
			list.add(emp);
		}

		if (list.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Data Not Available");
		} else {
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.setCode("EMP-06");
			resultDTO.setMessage("Employee Data Available for the filter below");
			resultDTO.setData(list);
			return resultDTO;
		} 
	}

	@Override
	public ResultDTO fetchEmployeeById(String empId) throws EmployeeNotFoundException {

		Optional<Employee> employee = employeeRepository.findById(empId);

		if (!employee.isPresent()) {

			throw new EmployeeNotFoundException("Employee Data Not Available for this Id");

		}

		Employee emp = employee.get();
		EmployeeDTO employeeDTO = employeeService.entityToDto(emp);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode("EMP-02");
		resultDTO.setMessage("Employee Data By Id Available");
		resultDTO.setData(employeeDTO);
		return resultDTO;

	}

	@Override
	public ResultDTO saveEmployee(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {

		ResultDTO resultDTO = new ResultDTO();
		if (employeeDTO == null) {
			throw new EmployeeNotFoundException("Null Values Are Not Acceptable!");
			
		} else {
			Employee employee = employeeService.dtoToEntity(employeeDTO);
			employee = employeeRepository.save(employee);
			EmployeeDTO dto = employeeService.entityToDto(employee);

			resultDTO.setCode("EMP-03");
			resultDTO.setMessage("Employee Data Created Successfully");
			resultDTO.setData(dto);
			return resultDTO;

		} 
	}

	@Override
	public ResultDTO updateEmployee(String empId, EmployeeDTO dto) throws EmployeeNotFoundException {

		Optional<Employee> employee = employeeRepository.findById(empId);

		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee Data Not Available for this Id");
		}

		Employee emp = employee.get();

		if (Objects.nonNull(dto.getEmpName()) && !"".equalsIgnoreCase(dto.getEmpName())) {
			emp.setEmpName(dto.getEmpName());
		}

		if (Objects.nonNull(dto.getEmpAge()) && !"".equalsIgnoreCase(dto.getEmpAge())) {
			emp.setEmpAge(dto.getEmpAge());
		}

		if (Objects.nonNull(dto.getEmpAddress()) && !"".equalsIgnoreCase(dto.getEmpAddress())) {
			emp.setEmpAddress(dto.getEmpAddress());
		}
		if (Objects.nonNull(dto.getEmpSalary()) && !"".equalsIgnoreCase(dto.getEmpSalary())) {
			emp.setEmpSalary(dto.getEmpSalary());
		}
		if (Objects.nonNull(dto.getEmpDept()) && !"".equalsIgnoreCase(dto.getEmpDept())) {
			emp.setEmpDept(dto.getEmpDept());
		}
		if (Objects.nonNull(dto.getEmpDesignation()) && !"".equalsIgnoreCase(dto.getEmpDesignation())) {
			emp.setEmpDesignation(dto.getEmpDesignation());
		}
		if (Objects.nonNull(dto.getEmpSalary()) && !"".equalsIgnoreCase(dto.getEmpSalary())) {
			emp.setEmpSalary(dto.getEmpSalary());
		}
		if (Objects.nonNull(dto.getEmpContactNumber()) && !"".equalsIgnoreCase(dto.getEmpContactNumber())) {
			emp.setEmpContactNumber(dto.getEmpContactNumber());
		}

		Employee empy = employeeRepository.save(emp);
		EmployeeDTO employeeDTO = employeeService.entityToDto(empy);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode("EMP-04");
		resultDTO.setMessage("Employee Data Updated Successfully");
		resultDTO.setData(employeeDTO);

		return resultDTO;
	}
	
	
	@Override
	public ResultDTO deleteEmployeeById(String empId) throws EmployeeNotFoundException {

		Optional<Employee> employee = employeeRepository.findById(empId);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee Data Not Available for this Id");
		}
		employeeRepository.deleteById(empId);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode("EMP-05");
		resultDTO.setMessage("Employee Data Deleted Successfully");
		return resultDTO;
	} 
	
	
}
