package com.ibm.academia.apirest.services;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@SpringBootTest
public class AlumnoDAOImplTest {
	
	@MockBean
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoDAO alumnoDao;
	
	@Test
	@DisplayName("Test: Buscar alumno por nombre carrera")
	void buscarAlumnoPorNombreCarrera() {		
		//When
		alumnoDao.buscarAlumnoPorNombreCarrera(anyString());
		//Then
		verify(alumnoRepository).buscarAlumnoPorNombreCarrera(anyString());
	}
}
