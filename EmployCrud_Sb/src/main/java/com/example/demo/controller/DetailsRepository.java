package com.example.demo.controller;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Employdetails, Integer> {
	List<Employdetails> findByEmpid(int empid);
}