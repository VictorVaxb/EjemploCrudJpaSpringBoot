package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IProfesorDao;
import com.example.demo.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorDao profeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Profesor> findAll() {
		return (List<Profesor>) profeDao.findAll();  
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findProfesor(Profesor profesor) {
		return profeDao.findByEmail(profesor.getEmail());
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor checkProfesorLogin(Profesor profesor) {
		return profeDao.findByEmailAndPasswword(profesor.getEmail(), profesor.getPassword());
	}

	@Override
	@Transactional
	public void deleteProfesor(Profesor profesor) {
		profeDao.deleteById(profesor.getId());
	}

	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return profeDao.save(profesor);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Profesor> findProfesorById(Long id) {
		return profeDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteProfesor(Long id) {
		profeDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findById(Long id) {
		return profeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findByIdSql(Long id) {
		return profeDao.findByIdSql(id);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profeDao.save(profesor);
	}

	
	
}
