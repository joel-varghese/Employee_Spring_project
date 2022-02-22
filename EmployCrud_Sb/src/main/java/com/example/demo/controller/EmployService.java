package com.example.demo.controller;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EmployService {

	@Autowired
    private EmployRepository repo;
	
	 @Autowired
	 private DetailsRepository emprep;
	
	@Autowired  
    JdbcTemplate jdbc; 
	
	public String delete(int empno) {
		Employ emp = repo.getById(empno);
		if(emp == null) {
			throw new RuntimeException("Employee not found");
		}
		repo.deleteById(empno);
		return "Deleted : " + emp.getName();
	}
	public List<Employ> showEmploy(String keyword) {
		if(keyword != null) {
			return repo.pattern(keyword);
		}
		return repo.findAll();
	}
	
	public void saveEmploy(Employ employvo) {
		 repo.save(employvo);
	}
	public List<Employdetails> showDetails(int empid) {
		return emprep.findByEmpid(empid);
	}
	public void savedet(Employdetails detail) {
		 emprep.save(detail);
	}
	public Employdetails searchdet(int eid) {
		Employdetails detail = emprep.getById(eid);
		if(detail == null) {
			throw new RuntimeException("Employee Details not found");
		}
		return detail;
	}
	public String deldetail(int eid) {
		Employdetails det = emprep.getById(eid);
		if(det == null) {
			throw new RuntimeException("Employee not found");
		}
		emprep.deleteById(eid);
		return "Deleted Record : ";
	}
	/*
	public List<Employ> ordid(){
		return repo.findAll(sortByIdAsc());
	}
    private Sort sortByIdAsc() {
        return Sort.by(Sort.Direction.ASC, "Empno");
    }
    
	public List<Employ> ordname(){
		return repo.findAll(sortByNameAsc());
	}
    private Sort sortByNameAsc() {
        return Sort.by(Sort.Direction.ASC, "name");
    }
    
	public List<Employ> pattern(String name){
		String cmd = "SELECT * FROM employ WHERE name,desig,dept LIKE '"+name+"%';";
		List<Employ> emplist=null;
		emplist = jdbc.query(cmd, new RowMapper<Employ>() {
			
			@Override
			public Employ mapRow(ResultSet rs,int rowNum) throws SQLException {
				Employ employ = new Employ();
				employ.setEmpno(rs.getInt("empno"));
				employ.setName(rs.getString("name"));
				employ.setGender(Gender.valueOf(rs.getString("gender")));
				employ.setDesig(rs.getString("desig"));
				employ.setDept(rs.getString("dept"));
				employ.setBasic(rs.getInt("basic"));
				return employ;
			}
		});
		return emplist;
	}
	*/
}
