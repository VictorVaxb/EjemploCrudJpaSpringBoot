package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Curso;
import com.example.demo.entity.Profesor;
import com.example.demo.service.ICursoService;

@RestController
@RequestMapping("/api/curso")
public class CursoRestController {

	@Autowired
	private ICursoService cursoServ;
	
	@GetMapping("/cursos")
	public ResponseEntity<?> getAllCursos(){
		List<Curso> lstCursos = cursoServ.findAll();
		
		if(lstCursos != null) {
			if(lstCursos.size() > 0) {
				return new ResponseEntity<>(lstCursos, HttpStatus.OK);
			}		
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/crear_curso")
	public ResponseEntity<?> saveCurso(@RequestBody Curso curso){
		if(curso != null) {
			cursoServ.saveCurso(curso);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("cursos_profesor")
	public ResponseEntity<?> getCursosProfesor(@RequestBody Profesor profesor){
		List<Curso> lstCursos = cursoServ.findCursosByProfesorId(profesor.getId());
		if(lstCursos != null) {
			if(lstCursos.size() > 0) {
				return new ResponseEntity<>(lstCursos, HttpStatus.OK);				
			}
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
