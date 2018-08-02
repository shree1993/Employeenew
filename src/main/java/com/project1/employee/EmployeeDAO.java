package com.project1.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeDetails, Long> {

	@Query(value = "SELECT * FROM Employee e WHERE e.username = ?1 and e.password= ?2", nativeQuery = true)
	public List<EmployeeDetails> findByTitle(String userName, String password);

}
