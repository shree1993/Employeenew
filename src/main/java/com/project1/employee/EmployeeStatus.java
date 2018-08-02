package com.project1.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class EmployeeStatus {

	String statusMsg;
	String msg;
	List<EmployeeDetails> data;
	Optional<EmployeeDetails> empDetails;

	public EmployeeStatus(String statusMsg, String msg, List<EmployeeDetails> data, Optional<EmployeeDetails> empDetails) {
		super();
		this.statusMsg = statusMsg;
		this.msg = msg;
		this.data = data;
		this.empDetails = empDetails;
	}

	public EmployeeStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<EmployeeDetails> getData() {
		return data;
	}

	public void setData(List<EmployeeDetails> data) {
		this.data = data;
	}
	
	public void setData(Optional<EmployeeDetails> empDetails) {
		this.empDetails = empDetails;
	}
}
