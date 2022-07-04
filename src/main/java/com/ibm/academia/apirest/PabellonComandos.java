package com.ibm.academia.apirest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

@Component
public class PabellonComandos implements CommandLineRunner{
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Guardar pabellon
//		Direccion dir = new Direccion("Calle 6","44", "46780", null, null, "Santa Anita");
//		Pabellon pabellon = new Pabellon(null, 50.00, "pabellon 1", dir);
//		
//		Pabellon pabellonGuardar = pabellonDao.guardar(pabellon);
//		System.out.println(pabellonGuardar);
		
		//Buscar Pabellon por nombre
//		Iterable<Pabellon> pabellonesNombre = pabellonDao.buscarPabellonesPorNombre("pabellon 1");
//		pabellonesNombre.forEach(System.out::println);
		
		//Buscar Pabellon por localidad
//		Iterable<Pabellon> pabellonesLocalidad = pabellonDao.buscarPabellonesPorLocalidad("Santa Anita");
//		pabellonesLocalidad.forEach(System.out::println);
	}
}
