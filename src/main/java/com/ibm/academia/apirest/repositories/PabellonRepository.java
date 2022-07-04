package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer>{
	
	@Query("select p from Pabellon p where p.direccion.localidad = ?1")
	public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad);
	
	@Query("select p from Pabellon p where p.nombre = ?1")
	public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre);
}
