package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO{
	
	@Autowired
	public PabellonDAOImpl(PabellonRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad) {
		return repository.buscarPabellonesPorLocalidad(localidad);
	}

	@Override
	public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre) {
		return repository.buscarPabellonesPorNombre(nombre);
	}
}
