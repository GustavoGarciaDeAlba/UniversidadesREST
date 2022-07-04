package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO{
	
	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesores")PersonaRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Persona> findProfesoresByCarreras(String carrera) {
		return ((ProfesorRepository)repository).findProfesoresByCarreras(carrera);
	}

	@Override
	public Persona actualizar(Persona profesorEncontrado, Persona profesor) {
		Persona profesorActualizado = null;
		profesorEncontrado.setNombre(profesor.getNombre());
		profesorEncontrado.setApellido(profesor.getApellido());
		profesorEncontrado.setDireccion(profesor.getDireccion());
		profesorActualizado = repository.save(profesorEncontrado);
		return profesorActualizado;
	}

}
