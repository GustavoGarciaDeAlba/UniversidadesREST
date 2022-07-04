package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Pabellon;

@DataJpaTest
public class PabellonRepositoryTest {
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@Test
	@DisplayName("Test: Buscar pabellones por localidad")
	void buscarPabellonesPorLocalidad() {
		//Given
		pabellonRepository.saveAll(
				Arrays.asList(
						DatosDummy.pabellon01(),
						DatosDummy.pabellon02())
		);
		
		//When
		String localidad = "Santa Anita";
		List<Pabellon> expected = (List<Pabellon>) pabellonRepository.buscarPabellonesPorLocalidad(localidad);
		
		//Then
		assertThat(expected.size() == 0).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar Pabellones por nombre")
	void buscarPabellonesPorNombre() {
		//Given
		pabellonRepository.saveAll(
				Arrays.asList(
						DatosDummy.pabellon01(),
						DatosDummy.pabellon02())
		);
		
		//When
		List<Pabellon> expected = (List<Pabellon>) pabellonRepository.buscarPabellonesPorNombre("pabellon 1");
		
		//Then
		assertThat(expected.size() == 1).isTrue();
	}
}
