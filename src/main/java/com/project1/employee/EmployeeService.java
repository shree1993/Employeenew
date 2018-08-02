package com.project1.employee;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.employee.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO empDAO;
	@Autowired
	EmployeeDetails emp;
	@Autowired
	EmployeeStatus empStatus;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	public EmployeeStatus addEmployeeDetails(EmployeeDetails emp) throws EmployeeNotFoundException{
		LOGGER.info("Inside Service Layer addEmployeeDetails");
		emp = empDAO.save(emp);
		empStatus.setData(Collections.<EmployeeDetails> emptyList());
		if (emp == null) {
			empStatus.setStatusMsg("There is some issue at server side. Please check the log");
			empStatus.setMsg("failure");
		}
		else {
			empStatus.setStatusMsg("Employee added sucessfully");
			empStatus.setMsg("Success");					
		}
		return empStatus;
	}

	public EmployeeStatus deleteEmployee(Long empID) throws EmployeeNotFoundException {
		LOGGER.info("Inside Service Layer deleteEmployee");
		emp = empDAO.findById(empID).get();
		if (emp == null) {
			empStatus.setStatusMsg("There is some issue at server side. Please check the log");
			empStatus.setMsg("failure");
			empStatus.setData(Collections.<EmployeeDetails> emptyList());
			throw new EmployeeNotFoundException("Specified Id " + empID + " Not Present");
		} else {
			empStatus.setStatusMsg("Employee deleted sucessfully");
			empStatus.setMsg("Success");
			empStatus.setData(Collections.<EmployeeDetails> emptyList());
			empDAO.deleteById(empID);
		}
		return empStatus;
	}

	public EmployeeStatus getAllEmployees() throws EmployeeNotFoundException {
		LOGGER.info("Inside Service Layer getAllEmployees");
		List<EmployeeDetails> allEmp = empDAO.findAll();
		if (allEmp == null) {
			empStatus.setStatusMsg("There is some issue at server side. Please check the log");
			empStatus.setMsg("failure");
			empStatus.setData(Collections.<EmployeeDetails> emptyList());
			throw new EmployeeNotFoundException("There is no Employee found in DB");
		} else {
			empStatus.setStatusMsg("Take all your employee details");
			empStatus.setMsg("Success");
			empStatus.setData(allEmp);
		}
		return empStatus;
	}

	public EmployeeStatus getEmployeeById(Long empId) throws EmployeeNotFoundException {
		LOGGER.info("Inside Service Layer getEmployeeById");
		Optional<EmployeeDetails> allEmp = empDAO.findById(empId);
		if (allEmp == null) {
			empStatus.setStatusMsg("There is some issue at server side. Please check the log");
			empStatus.setMsg("failure");
			empStatus.setData(Collections.<EmployeeDetails> emptyList());
			throw new EmployeeNotFoundException("There is no Employee found in DB");
		} else {
			empStatus.setStatusMsg("Take all your employee details");
			empStatus.setMsg("Success");
			empStatus.setData(allEmp);
		}
		return empStatus;
	}

	public void validateEmployee(String userName, String password) throws EmployeeNotFoundException
	{		
		List<EmployeeDetails> emp = empDAO.findByTitle(userName,password);
		if(emp==null)
		{
			throw new EmployeeNotFoundException("There is no Employee found in DB");
		}
	}
}
