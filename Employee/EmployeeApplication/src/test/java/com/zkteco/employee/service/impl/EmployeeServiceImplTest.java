package com.zkteco.employee.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.zkteco.employee.dto.EmployeeDTO;
import com.zkteco.employee.dto.ResultDTO;
import com.zkteco.employee.entity.Employee;
import com.zkteco.employee.exception.EmployeeNotFoundException;
import com.zkteco.employee.repository.EmployeeRepository;
import com.zkteco.employee.service.EmployeeService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	Employee employee;

	@Mock
	ResultDTO resultDTO;

	@Mock
	EmployeeService employeeService;

	@Mock
	EmployeeDTO employeeDTO;

	@BeforeEach
	void setUp() throws Exception {

		employeeDTO.setEmpId("402894c87cdffd54017ce00348f10000");
		employeeDTO.setEmpName("Ramesh");
		employeeDTO.setEmpAddress("Bangalore");
		employeeDTO.setEmpEmail("ramesh@gmail.com");
		employeeDTO.setEmpContactNumber("9986759308");
		employeeDTO.setEmpDept("IT");
		employeeDTO.setEmpDesignation("Software Developer");
		employeeDTO.setEmpSalary("79000");
		employeeDTO.setEmpAge("34");
	}

	@Test
	@DisplayName("Get Employee Data Based On Valid Id")

	void testGetEmployeeById() throws EmployeeNotFoundException {

		String empId = "402894c87cdffd54017ce00348f10000";
		Optional<Employee> emp = Optional.of(employee);
		Mockito.when(employeeRepository.findById(empId)).thenReturn(emp);
		resultDTO = employeeServiceImpl.fetchEmployeeById(empId);
		assertEquals("Employee Data By Id Available", resultDTO.getMessage());
	}

	@Test
	@DisplayName("If Employee Id Does Not Exist")
	void testEmployeeIdDoesNotExist() {

		String empId = "402894c87cdffd54017ce00348f1000099";
		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			employeeServiceImpl.fetchEmployeeById(empId);
		});

		String expectedMessage = "Employee Data Not Available for this Id";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("Delete Employee Data By Valid Id")

	void testDeleteEmployeeById() throws EmployeeNotFoundException {

		String empId = "402894c87d0e7b03017d0e7c19a40000";
		Optional<Employee> emp = Optional.of(employee);

		Mockito.when(employeeRepository.findById(empId)).thenReturn(emp);

		resultDTO = employeeServiceImpl.deleteEmployeeById(empId);
		assertEquals("Employee Data Deleted Successfully", resultDTO.getMessage());
	}

	@Test
	@DisplayName("If Employee Id Does Not Exist For Delete Data")
	void testEmployeeIdDoesNotExistForDeleteData() {

		String empId = "402894c87cdffd54017ce00348f100009955";
		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			employeeServiceImpl.deleteEmployeeById(empId);
		});

		String expectedMessage = "Employee Data Not Available for this Id";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("Update Employee Data By Valid Id")
	void testUpdateEmployee() throws EmployeeNotFoundException {

		String empId = "402894c87cdffd54017ce00348f10000";
		Optional<Employee> emp = Optional.of(employee);
		Mockito.when(employeeRepository.findById(empId)).thenReturn(emp);
		resultDTO = employeeServiceImpl.updateEmployee(empId, employeeDTO);
		assertEquals("Employee Data Updated Successfully", resultDTO.getMessage());

	}

	@Test
	@DisplayName("If Employee Is Not Valid To Update The Data")
	void testEmployeeIdNotValidToUpdateTheData() {

		String empId = "402894c87cdffd54017ce00348f1000055";
		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			employeeServiceImpl.updateEmployee(empId, employeeDTO);
		});

		String expectedMessage = "Employee Data Not Available for this Id";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("Save Employee Data")
	void testSaveEmployee() throws EmployeeNotFoundException {

		Mockito.when(employeeService.dtoToEntity(employeeDTO)).thenReturn(employee);
		resultDTO = employeeServiceImpl.saveEmployee(employeeDTO);
		assertEquals("Employee Data Created Successfully", resultDTO.getMessage());
	}

	@Test
	@DisplayName("If Employee Data Is Not Given")
	void testIfEmployeeDataIsNotGiven() {

		employeeDTO = null;
		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			resultDTO = employeeServiceImpl.saveEmployee(employeeDTO);
		});

		String expectedMessage = "Null Values Are Not Acceptable!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("Emplyoee Data By List")
	void testGetAllEmployeeData() throws EmployeeNotFoundException {

		int pageNumber = 0;
		int pageSize = 5;

		Pageable pageable = PageRequest.of(0, 5);
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee);
		Page<Employee> page = new PageImpl<>(list);
		Mockito.when(employeeRepository.findAll(pageable)).thenReturn(page);
		resultDTO = employeeServiceImpl.getAllEmployeeData(pageNumber, pageSize);
		assertEquals("Employee Data Available for the list below", resultDTO.getMessage());

	}


	@Test
	@DisplayName("Employee Data By Filter")
	void testGetAllEmployeeFilter() throws EmployeeNotFoundException {
		int pageNumber = 0;
		int pageSize = 5;
		String search = null;
		String empId = null;
		String empName = null;
		String empAddress = null;
		String empAge = null;
		String empSalary = null;
		String empDept = null;
		String empDesignation = null;
		String empContactNumber = null;
		String empEmail = null;

		Pageable pageable = PageRequest.of(0, 5);
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee);
		Page<Employee> pageTuts = new PageImpl<>(list);
		Mockito.when(employeeRepository.findAll(pageable)).thenReturn(pageTuts);
		resultDTO = employeeServiceImpl.getAllEmployeeFilter(pageNumber, pageSize, search, empId, empName, empAddress,
				empAge, empSalary, empDept, empDesignation, empContactNumber, empEmail);
		assertEquals("Employee Data Available for the filter below", resultDTO.getMessage());
	}


}
