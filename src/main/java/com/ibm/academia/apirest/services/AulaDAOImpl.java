package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{
	
	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Aula> buscarAulasPorTipoPizarron(Pizarron pizarrron) {
		return repository.buscarAulasPorTipoPizarron(pizarrron);
	}

	@Override
	public Iterable<Aula> buscarAulasPorPabellon(String pabellon) {
		return repository.buscarAulasPorPabellon(pabellon);
	}

	@Override
	public Aula buscarAulaPorNumeroAula(Integer numeroAula) {
		return repository.buscarAulaPorNumeroAula(numeroAula);
	}
}
