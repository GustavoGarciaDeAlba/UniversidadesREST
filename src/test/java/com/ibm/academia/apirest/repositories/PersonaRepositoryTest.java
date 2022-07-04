package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@DataJpaTest
public class PersonaRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioAlumnos")
	private PersonaRepository alumnoRepository;
	
	@Autowired
	@Qualifier("repositorioEmpleados")
	private PersonaRepository empleadoRepository;
	
	@Autowired 
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesorRepository;
	
	@Test
	@DisplayName("Test: Buscar por nombre y apellido")
	void buscarPorNombreYApellido() {
		//Given
		Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());
		
		//When
		Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(), DatosDummy.empleado01().getApellido());
	
		//Then
		assertThat(expected.get()).isInstanceOf(Empleado.class);
		assertThat(expected.get()).isEqualTo(personaEmpleado);
	}
	
	@Test
	@DisplayName("Test: Buscar persona por DNI")
	void buscarPorDni() {
		//Given
		Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());
		
		//When
		Optional<Persona> expected = profesorRepository.buscarPorDni(DatosDummy.profesor01().getDni());
	
		//Then
		assertThat(expected.get()).isInstanceOf(Profesor.class);
		assertThat(expected.get()).isEqualTo(personaProfesor);
		assertThat(expected.get().getDni()).isEqualTo(personaProfesor.getDni());
	}
	
	@Test
	@DisplayName("Test: Buscar persona por apellido")
	void buscarPersonaPorApellido() {
		//Given
		Iterable<Persona> personas = alumnoRepository.saveAll(
				Arrays.asList(
						DatosDummy.alumno01(),
						DatosDummy.alumno02(),
						DatosDummy.alumno03())
		);
		
		//When
		String apellido = "Padilla";
		List<Persona> expected = (List<Persona>) profesorRepository.buscarPersonaPorApellido(apellido);
	
		//Then
		assertThat(expected.size() == 1).isTrue();

	}
}
