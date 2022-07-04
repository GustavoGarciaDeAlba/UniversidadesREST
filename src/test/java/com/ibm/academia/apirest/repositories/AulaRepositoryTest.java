package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;

@DataJpaTest
public class AulaRepositoryTest {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@Test
	@DisplayName("Test: Buscar aulas por tipo de pizarron")
	void buscarAulasPorTipoPizarron() {
		
		//Given
		aulaRepository.saveAll(
				Arrays.asList(
						DatosDummy.aula01(),
						DatosDummy.aula02(),
						DatosDummy.aula03())
				);
		
		//When
		Iterable<Aula> expected = aulaRepository.buscarAulasPorTipoPizarron(Pizarron.PIZZARRA_BLANCA);
		
		//Then
		assertThat(((List<Aula>)expected).size() == 2).isTrue(); 
	}
	
	@Test
	@DisplayName("Test: Buscar aulas por pabellon")
	void buscarAulasPorPabellon() {
		//Given
		Iterable<Aula> aulas = aulaRepository.saveAll(
				Arrays.asList(
						DatosDummy.aula01(),
						DatosDummy.aula02(),
						DatosDummy.aula03())
				);
		
		Pabellon pabellon = pabellonRepository.save(DatosDummy.pabellon01());
		aulas.forEach(aula -> aula.setPabellon(pabellon));
				
		//When
		Iterable<Aula> expected = aulaRepository.buscarAulasPorPabellon(pabellon.getNombre());
		
		//Then
		assertThat(((List<Aula>)expected).size() == 3).isTrue(); 
	}
	
	@Test
	@DisplayName("Test: Buscar aulas por numero del aula")
	void buscarAulaPorNumeroAula() {
		//Given
		aulaRepository.saveAll(
				Arrays.asList(
						DatosDummy.aula01(),
						DatosDummy.aula02(),
						DatosDummy.aula03())
				);
		
		//When
		Aula expected = aulaRepository.buscarAulaPorNumeroAula(3);
		
		//Then
		assertThat(expected).isInstanceOf(Aula.class);
		assertThat(expected.getNumeroAula()).isEqualTo(DatosDummy.aula03().getNumeroAula());
	}
}
