package com.zkteco.employee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "emp_Id")
	private String empId;

	@Column(name = "emp_Name")
	private String empName;

	@Column(name = "emp_Address")
	private String empAddress;

	@Column(name = "emp_Age")
	private String empAge;

	@Column(name = "emp_Salary")
	private String empSalary;

	@Column(name = "emp_Dept")
	private String empDept;

	@Column(name = "emp_Designation")
	private String empDesignation;

	@Column(name = "emp_ContactNumber")
	private String empContactNumber;

	@Column(name = "emp_Email")
	private String empEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_At")
	private Date createdAt = new Date();

	@Column(name = "Updated_At")
	private Date updatedAt = new Date();

}
