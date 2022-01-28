package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployController {

	 @Autowired
	 private EmployService service;
	 
	 @Autowired
	 private EmployRepository repo;
	 
	 @RequestMapping("/employ/{empno}")
	  public Employ show(@PathVariable int empno) {
			return service.search(empno);
	}
	 

		@PostMapping("/addEmploy")
		public void saveEmploy(@RequestBody Employ employ) {
			service.saveEmploy(employ);
		}
	@RequestMapping("/name/{name}")
	public List<Employ> names(@PathVariable String name) {
		return repo.findByName(name);
	}
	@GetMapping("/")
	public List<Employ> list() {
	    return service.showEmploy();
	}
	@RequestMapping("/update")
	public String update(@PathVariable Employ employ) {
		return service.updatemp(employ);
	}
	@RequestMapping("/delete/{empno}")
	public String deletemp(@PathVariable int empno) {
		return service.delete(empno);
	}
	
	
	@GetMapping("/details")
	public List<Employdetails> listall() {
	    return service.showDetails();
	}
	@PostMapping("/addDetails")
	public void saveDetails(@RequestBody Employdetails detail) {
		service.savedet(detail);
	}
}