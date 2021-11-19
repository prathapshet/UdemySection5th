package com.zkteco.employee.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private String empId;

	@NotNull(message = "Please Add Valid Employee Name")
	private String empName;

	@NotNull(message = "Please Add Valid Employee Age")
	private String empAge;

	@NotNull(message = "Please Add Valid Employee Address")
	private String empAddress;

	@NotNull(message = "Please Add Valid Employee Salary")
	private String empSalary;

	@NotNull(message = "Please Add Valid Employee Department")
	private String empDept;

	@NotNull(message = "Please Add Valid Employee Designation")
	private String empDesignation;

	@NotNull(message = "Please Add Valid Employee Contact Number")
	private String empContactNumber;

	@NotNull(message = "Please Add Valid Employee Email")
	private String empEmail;
	
	private Date createdAt = new Date();

	private Date updatedAt = new Date();

}
