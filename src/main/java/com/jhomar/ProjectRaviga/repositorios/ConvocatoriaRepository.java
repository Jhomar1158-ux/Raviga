package com.jhomar.ProjectRaviga.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhomar.ProjectRaviga.models.Convocatoria;

@Repository
public interface ConvocatoriaRepository extends CrudRepository<Convocatoria, Long> {
	List<Convocatoria> findByUniversidad(String universidad);
	
	List<Convocatoria> findByUniversidadIsNot(String universidad);
}
