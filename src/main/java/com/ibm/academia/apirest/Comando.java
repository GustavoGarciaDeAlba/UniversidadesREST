package com.ibm.academia.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@Component
public class Comando implements CommandLineRunner{
	
	@Autowired
	private CarreraDAO carreraDAO;
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO personaDAO;
	
	@Override
	public void run(String... args) throws Exception {
//		Carrera finanzas = new Carrera(null, "Ingenieria en finanzas", 20, 3);
//		Carrera carreraGuardada = carreraDAO.guardar(finanzas);
//		System.out.println(carreraGuardada.toString());
//		
//		Carrera carrera = null;
//		
//		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(1);
//		if(oCarrera.isPresent()) {
//			carrera = oCarrera.get();
//			System.out.println(carrera.toString());
//		} else {
//			System.out.println("Carrera no encotrada");
//		}
//		
//		carrera.setCantidadAnios(7);
//		carrera.setCantidadMaterias(66);
//		carreraDAO.guardar(carrera);
//		
//		System.out.println(carreraDAO.buscarPorId(7).orElse(new Carrera()).toString());
//		
//		carreraDAO.eliminarPorId(1);
		
//		Carrera ingSistemas = new Carrera(null, "Ingenieria en Sistemas", 60, 5);
//		Carrera ingIndustrial = new Carrera(null, "Ingenieria Industrial", 55, 5);
//		Carrera ingAlimentos = new Carrera(null, "Ingenieria en Alimentos", 53, 5);
//		Carrera ingElectronica = new Carrera(null, "Ingenieria Electronica", 45, 5);
//		Carrera licSistemas = new Carrera(null, "Licenciatura en Sistemas", 40, 4);
//		Carrera licTurismo = new Carrera(null, "Licenciatura en Turismo", 42, 4);
//		Carrera licYoga = new Carrera(null, "Licenciatura en Yoga", 25, 3);
//		Carrera licRecursos = new Carrera(null, "Licenciatura en Recursos Humanos - RRHH", 33, 3);
//		
//		carreraDAO.guardar(ingSistemas);
//		carreraDAO.guardar(ingIndustrial);
//		carreraDAO.guardar(ingAlimentos);
//		carreraDAO.guardar(ingElectronica);
//		carreraDAO.guardar(licSistemas);
//		carreraDAO.guardar(licTurismo);
//		carreraDAO.guardar(licYoga);
//		carreraDAO.guardar(licRecursos);
		
		//Asignar a todos las personas a la carrera con el id 2
//		Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(2);
//		
//		Iterable<Persona> alumnos = personaDAO.buscarTodos();
//		
//		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()));
//		alumnos.forEach(alumno -> personaDAO.guardar(alumno));
		
		//Busca a todos los alumnos de la carrera dada
//		Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(2);
//		Iterable<Persona> alumnosCarrera = ((AlumnoDAO)personaDAO).buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
//		alumnosCarrera.forEach(System.out::println);
		
		//Busca las carreras que contengan "sistemas" en su nombre
//		List<Carrera> carreras = (List<Carrera>)carreraDAO.findCarrerasByNombreContains("Sistemas");
//		carreras.forEach(System.out::println);
		
		//Busca las carreras que tengan sistemas en su nombre sin importar el case de las letras
//		List<Carrera> carreraIgnoreCase1 = (List<Carrera>)carreraDAO.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
//		List<Carrera> carreraIgnoreCase2 = (List<Carrera>)carreraDAO.findCarrerasByNombreContainsIgnoreCase("sistemas");
//		carreraIgnoreCase1.forEach(System.out::println);
//		carreraIgnoreCase2.forEach(System.out::println);
		
		
		//Busca las carreras que tengan mas de 3 anios
//		List<Carrera> carrerasPorAnio = (List<Carrera>)carreraDAO.findCarrerasByCantidadAniosAfter(3);
//		carrerasPorAnio.forEach(System.out::println);
		
		//Buscar por id usando query nativo
//		Optional<Persona> persona = personaDAO.buscarPorId(1);
//		System.out.println(persona.toString());
		
		//BUSCAR CARRERAS POR NOMBRE DE PROFESOR
//		Optional<Persona> profesor = personaDAO.buscarPorId(4);
//		List<Carrera> carrerasProfesor = (List<Carrera>)carreraDAO.buscarCarrerasPorProfesorNombreYApellido(profesor.get().getNombre(), profesor.get().getApellido());
//		carrerasProfesor.forEach(System.out::println);
	}
	
}
