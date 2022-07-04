package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesorRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	@DisplayName("Test: Buscar profesores por nombre carrera")
	void findProfesoresByCarreras() {
		Iterable<Persona> personas = profesorRepository.saveAll(
				Arrays.asList(
						DatosDummy.profesor01(),
						DatosDummy.profesor02())
		);
		
		Set<Carrera> carreras = new HashSet<>();
		
		carreras.add(DatosDummy.carrera01());	
		carreras.add(DatosDummy.carrera02());
		carreras.add(DatosDummy.carrera03());
		
		personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carreras));
		profesorRepository.saveAll(personas);
		
		//When
		String carreraNombre = "Ingenieria en Sistemas";
		List<Persona> expected = (List<Persona>) ((ProfesorRepository)profesorRepository).findProfesoresByCarreras(carreraNombre);
		
		//Then
		assertThat(expected.size() == 2).isTrue();
	}
}
