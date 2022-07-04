package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer>{
	
	@Query("select a from Aula a where a.pizarron = ?1")
	public Iterable<Aula> buscarAulasPorTipoPizarron(Pizarron pizarrron);
	
	@Query("select a from Aula a join fetch a.pabellon p where p.nombre = ?1")
	public Iterable<Aula> buscarAulasPorPabellon(String pabellon);
	
	@Query("select a from Aula a where a.numeroAula = ?1")
	public Aula buscarAulaPorNumeroAula(Integer numeroAula);
}