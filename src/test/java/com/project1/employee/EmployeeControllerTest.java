package com.project1.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeStatus empStatus;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void getAllEmployees() throws Exception {
		EmployeeDetails emp = new EmployeeDetails(new Long(0l), "Shweta", "123", "Shweta Shree", "shweta@gmail.com",
				"25/05/1993", "F", "abc", "fgfg");
		List<EmployeeDetails> myList = new ArrayList<>();
		myList.add(emp);
		empStatus.setData(myList);
		empStatus.setStatusMsg("Employee added sucessfully");
		empStatus.setMsg("Success");
		Mockito.when(employeeService.getAllEmployees()).thenReturn(empStatus);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/EmpMgt/getAllEmpDetails")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("ABCD" + result.getResponse().getContentAsString());
		String s = "{\"statusMsg\":\"Employee added sucessfully\",\"msg\":\"Success\",\"data\":[{\"userName\":\"Shweta\",\"passWord\":\"123\",\"fullName\":\"Shweta Shree\",\"emailId\":\"shweta@gmail.com\",\"dateOfBirth\":\"25/05/1993\",\"gender\":\"F\",\"securityQuestion\":\"abc\",\"securityAnswer\":\"fgfg\"}]}";
		JSONAssert.assertEquals(s, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void deleteEmployee() throws Exception {
		List<EmployeeDetails> myList = Collections.<EmployeeDetails> emptyList();
		empStatus.setData(myList);
		empStatus.setStatusMsg("Employee deleted sucessfully");
		empStatus.setMsg("Success");
		Mockito.when(employeeService.deleteEmployee(1l)).thenReturn(empStatus);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/EmpMgt/deleteEmp/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("ABCDdelete" + result.getResponse().getContentAsString());
		String expected = "{\"statusMsg\":\"Employee deleted sucessfully\",\"msg\":\"Success\",\"data\":[]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getEmployeeBySpecificId() throws Exception {
		EmployeeDetails emp = new EmployeeDetails(new Long(0l), "Shweta", "123", "Shweta Shree", "shweta@gmail.com",
				"25/05/1993", "F", "abc", "fgfg");
		List<EmployeeDetails> myList = new ArrayList<>();
		myList.add(emp);
		empStatus.setData(myList);
		empStatus.setStatusMsg("Employee retreived sucessfully");
		empStatus.setMsg("Success");
		Mockito.when(employeeService.getEmployeeById(1l)).thenReturn(empStatus);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("ABCD" + result.getResponse().getContentAsString());
		String expected = "{\"statusMsg\":\"Employee retreived sucessfully\",\"msg\":\"Success\",\"data\":[{\"userName\":\"Shweta\",\"passWord\":\"123\",\"fullName\":\"Shweta Shree\",\"emailId\":\"shweta@gmail.com\",\"dateOfBirth\":\"25/05/1993\",\"gender\":\"F\",\"securityQuestion\":\"abc\",\"securityAnswer\":\"fgfg\"}]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

//	@Test
//	public void insertEmployeeDetails() throws Exception {
//		List<EmployeeDetails> myList = Collections.<EmployeeDetails> emptyList();
//		EmployeeDetails emp = new EmployeeDetails(new Long(0l), "Shweta", "123", "Shweta Shree", "shweta@gmail.com",
//				"25/05/1993", "F", "abc", "fgfg");
//		EmployeeDetails emp1 = new EmployeeDetails(new Long(0l), "Shweta", "123", "Shweta Shree", "shweta@gmail.com",
//				"25/05/1993", "F", "abc", "fgfg");
//		JSONObject obj = new JSONObject();
//		obj.put("statusMsg", "Employee inserted sucessfully");
//		obj.put("msg", "Success");
//		obj.put("data", emp);		
//		//List<EmployeeDetails> myList = Collections.<EmployeeDetails> emptyList();
//		empStatus.setData(myList);
//		empStatus.setStatusMsg("Employee inserted sucessfully");
//		empStatus.setMsg("Success");
//		Mockito.when(employeeService.addEmployeeDetails(emp)).thenReturn(empStatus);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/EmpMgt/addEmp").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println("ABCDinsert" + result.getResponse().getContentAsString());
//		String expected = "{\"statusMsg\":\"Employee inserted sucessfully\",\"msg\":\"Success\",\"data\":[]}";
//		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//	}
	
//	 @Test
//	 public void validateUser() {
//	
//	 }
}
