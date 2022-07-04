package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{

	public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad);
	public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre);
}
