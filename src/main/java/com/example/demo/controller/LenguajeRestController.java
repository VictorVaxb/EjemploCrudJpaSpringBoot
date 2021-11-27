package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lenguaje;
import com.example.demo.service.ILenguajeService;

@RestController
@RequestMapping("/api/lenguaje")
public class LenguajeRestController {
	
	@Autowired
	private ILenguajeService lengServ;
	
	@GetMapping("/getall")
	public ResponseEntity<?> GetAllLenguajes(){
		List<Lenguaje> lstLeng = lengServ.findAllLenguaje();
		
		if(lstLeng != null) {
			if(lstLeng.size() > 0) {
				return new ResponseEntity<>(lstLeng, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveLenguaje(@RequestBody Lenguaje lenguaje){
		lengServ.saveLenguaje(lenguaje);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
