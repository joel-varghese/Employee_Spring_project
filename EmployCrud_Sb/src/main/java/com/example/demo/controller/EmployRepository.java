package com.example.demo.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployRepository extends JpaRepository<Employ, Integer> {

	public List<Employ> findByName(String name);
	
	@Query("SELECT e FROM Employ e WHERE CONCAT(e.name,e.desig,e.dept) LIKE ?1%")
	public List<Employ> pattern(String keyword);
}