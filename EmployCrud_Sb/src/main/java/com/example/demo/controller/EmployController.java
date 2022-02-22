package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployController {

	 @Autowired
	 private EmployService service;
	 
	 @Autowired
	 private EmployRepository repo;
	 
	@PostMapping("/addEmploy")
	public Employ employpage(@RequestBody Employ employ) {
		return repo.save(employ);
	}
	@GetMapping("/employ/{id}")
	public ResponseEntity<Employ> getempid(@PathVariable int id){
		Employ employ = repo.findById(id).get();
		 return ResponseEntity.ok(employ);
	}
	@GetMapping("/")
	public List<Employ> list() {
		return repo.findAll();
	}
	@PutMapping("/update/{empno}")
	public ResponseEntity<Employ> update(@PathVariable int empno,@RequestBody Employ empup) {
		Employ employ = repo.findById(empno).get();
		employ.setName(empup.getName());
		employ.setGender(empup.getGender());
		employ.setDept(empup.getDept());
		employ.setDesig(empup.getDesig());
		employ.setBasic(empup.getBasic());
		Employ updatedemp = repo.save(employ);
		return ResponseEntity.ok(updatedemp);
	}
	@DeleteMapping("/delete/{empno}")
	public void deletemp(@PathVariable int empno) {
		Employ employ = repo.findById(empno).get();
		repo.delete(employ);
	}
	
	
	@GetMapping("/details/{empid}")
	public String listall(Model model,@PathVariable int empid) {
		List<Employdetails> empdetails = service.showDetails(empid);
		model.addAttribute("empdetails",empdetails);
		return "emp_details";
	}
	@RequestMapping(value = "/savedetail",method = RequestMethod.POST)
	public String saveDetail(@ModelAttribute("detail") Employdetails detail) {
		service.savedet(detail);
		return "redirect:/";
	}
	@PostMapping("/addDetails")
	public String saveDetails(Model model) {
		Employdetails detail = new Employdetails();
		model.addAttribute("detail",detail);
		return "new_detail";
	}
	@RequestMapping("/editdetail/{eid}")
	public String editDetails(Model model,@PathVariable int eid) {
		Employdetails detail = service.searchdet(eid);
		model.addAttribute("detail",detail);
		return "update_detail";
	}
	@RequestMapping("/deldet/{eid}")
	public String deletdetail(@PathVariable(name = "eid") int eid,Model model) {
		
		service.deldetail(eid);
		return "redirect:/";
	}
}