package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@Repository("repositorioProfesores")
public interface ProfesorRepository  extends PersonaRepository{
	
	@Query("select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
	public Iterable<Persona> findProfesoresByCarreras(String carrera);
}
