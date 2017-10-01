package com.mjkim.springmvc.step3.service;

import java.util.List;

import com.mjkim.springmvc.step3.model.Employee;

public interface EmployeeService {
	Employee findById(int id);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeBySsn(String ssn);

	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(String ssn);

	boolean isEmployeeSsnUnique(Integer id, String ssn);
}
