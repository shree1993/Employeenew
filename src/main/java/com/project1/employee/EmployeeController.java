package com.project1.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project1.employee.exception.EmployeeNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/EmpMgt")
@Api(value = "EmployeeDetails", description = "CRUD operations on employee details")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	@Autowired
	EmployeeStatus empStatus;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee added sucessfully"),
//			@ApiResponse(code = 404, message = "Employee not found") })
//	@PostMapping(value = "/addEmp")
	public ResponseEntity<EmployeeStatus> addEmployee(@RequestBody EmployeeDetails empDetails)
			throws EmployeeNotFoundException {
		LOGGER.info("Adding Employee details");
		try {
			empStatus = empService.addEmployeeDetails(empDetails);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.OK);
	}

	@PutMapping(value = "/deleteEmp/{empId}")
	public ResponseEntity<EmployeeStatus> deleteEmployee(@PathVariable Long empId) throws EmployeeNotFoundException {
		LOGGER.info("Delete Employee details by ID");
		try {
			empStatus = empService.deleteEmployee(empId);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllEmpDetails")
	public ResponseEntity<EmployeeStatus> getAllEmployeeDetails() throws EmployeeNotFoundException {
		LOGGER.info("get All Employee details");
		try {
			empStatus = empService.getAllEmployees();
		} catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
			return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.OK);
	}

	@GetMapping(value = "/getByEmpId/{empId}")
	public ResponseEntity<EmployeeStatus> getEmployeeById(@PathVariable Long empId) throws EmployeeNotFoundException {
		LOGGER.info("get Employee details by ID");
		try {
			empStatus = empService.getEmployeeById(empId);
		} catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
			return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmployeeStatus>(empStatus, HttpStatus.OK);
	}

	@PutMapping(value = "/checkLogin/{uesrName}/{password}")
	public void validateEmployee(@PathVariable String userName, @PathVariable String password)
			throws EmployeeNotFoundException {
		LOGGER.info("validate employee username and password");
		empService.validateEmployee(userName, password);
	}

}
