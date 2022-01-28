package com.example.demo.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployRepository extends JpaRepository<Employ, Integer> {

	public List<Employ> findByName(String name);
}