package com.jhomar.ProjectRaviga.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhomar.ProjectRaviga.models.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

	List<Categoria> findAll();
	
}
