package com.example.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployService {

	@Autowired
    private EmployRepository repo;
	
	@Autowired
    private EmployService service;
	
	 @Autowired
	 private DetailsRepository emprep;
	
	@Autowired  
    JdbcTemplate jdbc; 
	
	public Employ search(int empno) {
		return repo.findById(empno).get();
	}
	public String delete(int empno) {
		repo.deleteById(empno);
		return "Record deleted";
	}
	public List<Employ> showEmploy() {
		return repo.findAll();
	}
	
	public void saveEmploy(Employ employvo) {
		 repo.save(employvo);
		 //service.save(employvo);
	}
	
	public String updatemp(Employ employ) {
		String cmd = "Update Employ set name=?,gender=?,dept=?,desig=?,basic=? where empno=?";
		jdbc.update(cmd, new Object[] {employ.getName(),employ.getGender(),employ.getDept(),employ.getDesig(),employ.getBasic(),employ.getEmpno()});
		return "Employee details updated";
	}
	
	public List<Employdetails> showDetails() {
		return emprep.findAll();
	}
	public void savedet(Employdetails detail) {
		 emprep.save(detail);
	}
}
