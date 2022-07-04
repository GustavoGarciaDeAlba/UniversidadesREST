package com.ibm.academia.apirest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;


@Component
public class ProfesorComandos implements CommandLineRunner{

	@Autowired
	private CarreraDAO carreraDao;
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO personaDao;
	
	@Autowired
	private ProfesorDAO profesorDao;

	@Override
	public void run(String... args) throws Exception {
		
		//GUARDAR PROFESOR
//		Direccion dir = new Direccion("Calle 10","50", "93840", null, null, "Andares");
//		Persona	 profesor1 = new Profesor(null, "David", "Segura", "2857483920", dir,  new BigDecimal("30000"));
//		
//		Persona personaGuardada = profesorDao.guardar(profesor1);
//		System.out.println(personaGuardada.toString());
//		Optional<Persona> profesor = profesorDao.buscarPorId(personaGuardada.getId());
		
		//RELACIONAR A PROFESOR CON CARRERA
//		Optional<Carrera> ingSistemas = carreraDao.buscarPorId(2);
//		Optional<Persona> profesor = personaDao.buscarPorId(4);
//		Set<Carrera> carreras = new HashSet<Carrera>();
//		carreras.add(ingSistemas.get());
//		((Profesor)profesor.get()).setCarreras(carreras);
//		Persona personaGuardada = profesorDao.guardar(profesor.get());
		
//		PROBAR findProfesoresByCarreras
//		Optional<Carrera> ingSistemas = carreraDao.buscarPorId(2);
//		Iterable<Persona> profesoresSistemas = profesorDao.findProfesoresByCarreras(ingSistemas.get().getNombre());
//		profesoresSistemas.forEach(System.out::println);
	}

}
