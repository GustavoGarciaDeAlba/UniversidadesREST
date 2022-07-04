package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;

public interface AulaDAO extends GenericoDAO<Aula>{
	
	public Iterable<Aula> buscarAulasPorTipoPizarron(Pizarron pizarrron);
	public Iterable<Aula> buscarAulasPorPabellon(String pabellon);
	public Aula buscarAulaPorNumeroAula(Integer numeroAula);
	public Aula actualizar(Aula aulaEncontrada, Aula aula);
}
