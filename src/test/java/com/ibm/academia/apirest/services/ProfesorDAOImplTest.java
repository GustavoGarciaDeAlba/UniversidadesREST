package com.ibm.academia.apirest.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.apirest.repositories.ProfesorRepository;

@SpringBootTest
public class ProfesorDAOImplTest {
	
	@MockBean
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private ProfesorDAO profesorDao;
	
	@Test
	@DisplayName("Test: Buscar profesor por Carrera")
	void findProfesoresByCarreras() {
		//When
		profesorDao.findProfesoresByCarreras(anyString());
		
		//Then
		verify(profesorRepository).findProfesoresByCarreras(anyString());
	}
}
