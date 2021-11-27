package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lenguaje;
import com.example.demo.entity.Profesor;
import com.example.demo.entity.ProfesorLenguaje;
import com.example.demo.service.ILenguajeService;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping("/api/profesor_lenguaje")
public class ProfesorLenguajeRestController {

	@Autowired
	private IProfesorService profeServ;
	
	@Autowired
	private ILenguajeService lengServ;
	
	@PostMapping("/lenguajes_profesor")
	public ResponseEntity<?> lstLenguajesProfesor(@RequestBody Profesor profesor){
		Profesor profeDb = profeServ.findById(profesor.getId());
		if(profeDb != null) {
			Collection<Lenguaje> lstLenguajes = profeDb.getLenguajes();
			if(lstLenguajes != null) {
				return new ResponseEntity<>(lstLenguajes, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/save_lenguaje_profesor")
	public ResponseEntity<?> saveLenguajeProfesor(@RequestBody ProfesorLenguaje profeLeng){
		Profesor profeDb = profeServ.findById(profeLeng.getProfesor().getId());
		if(profeDb != null) {
			Lenguaje lengDb = lengServ.findLenguajeById(profeLeng.getLenguaje().getId());
			profeDb.addLenguaje(lengDb);
			profeServ.save(profeDb);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
