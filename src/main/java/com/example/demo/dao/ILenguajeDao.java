package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Lenguaje;

public interface ILenguajeDao extends CrudRepository<Lenguaje, Long> {

	@Query("select l from lenguaje l where l.id=?1")
	public Lenguaje findByIdSql(Long id);
	
}
