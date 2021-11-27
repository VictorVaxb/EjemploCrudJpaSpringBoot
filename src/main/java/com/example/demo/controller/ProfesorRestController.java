package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Profesor profesor){
		Profesor profe = profeServ.checkProfesorLogin(profesor);
		if(profe != null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProfesor(@PathVariable(value="id") Long id, @RequestBody Profesor profesor ){
		Profesor profeDb = profeServ.findById(id);
		if(profeDb != null) {
			profeDb.setEmail(profesor.getEmail());
			profeDb.setNombre(profesor.getNombre());
			profeServ.save(profeDb);
			return new ResponseEntity<>(profeDb, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProfesor(@PathVariable(value="id") Long id){
		Profesor profeDb = profeServ.findById(id);
		if(profeDb != null) {
			profeServ.deleteProfesor(id);
			return new ResponseEntity<Void>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAllProfesors(){
		profeServ.deleteAllProfesors();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
