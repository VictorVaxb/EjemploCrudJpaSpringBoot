package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Profesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping("/api/profesor")
public class ProfesorRestController {

	@Autowired
	private IProfesorService profeServ;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllProfesores(){
		List<Profesor> lstProfes = profeServ.findAll();
		return new ResponseEntity<>(lstProfes, HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ResponseEntity<Void> createProfesor(@RequestBody Profesor profesor){
		if(profeServ.findById(profesor.getId()) == null) {
			profeServ.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}	
	
}
