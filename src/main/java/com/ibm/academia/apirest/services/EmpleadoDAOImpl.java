package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO{
	
	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados")PersonaRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

}
