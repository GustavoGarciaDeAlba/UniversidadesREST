package com.ibm.academia.apirest.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

public class AulaDAOImplTest {
	
	AulaDAO aulaDao;
	AulaRepository aulaRepository;
	
	@BeforeEach
	void setUp() {
		aulaRepository = mock(AulaRepository.class);
		aulaDao = new AulaDAOImpl(aulaRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por Tipo de Pizarron")
	void buscarAulasPorTipoPizarron() {
		//Given
		when(aulaRepository.buscarAulasPorTipoPizarron(Pizarron.PIZZARRA_BLANCA))
		.thenReturn(Arrays.asList(DatosDummy.aula01(), DatosDummy.aula03()));
		
		//When
		List<Aula> expected = (List<Aula>) aulaDao.buscarAulasPorTipoPizarron(Pizarron.PIZZARRA_BLANCA);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(DatosDummy.aula01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.aula03());
		
		verify(aulaRepository).buscarAulasPorTipoPizarron(Pizarron.PIZZARRA_BLANCA);
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por Pabellon")
	void buscarAulasPorPabellon() {
		//Given
		when(aulaRepository.buscarAulasPorPabellon("pabellon 1"))
		.thenReturn(Arrays.asList(DatosDummy.aula01()));
		
		//When
		List<Aula> expected = (List<Aula>) aulaDao.buscarAulasPorPabellon("pabellon 1");
		
		//Then
		assertThat(expected.get(0)).isEqualTo(DatosDummy.aula01());
		
		verify(aulaRepository).buscarAulasPorPabellon("pabellon 1");
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por Numero de Aula")
	void buscarAulaPorNumeroAula() {
		//Given
		when(aulaRepository.buscarAulaPorNumeroAula(02))
		.thenReturn(DatosDummy.aula02());
		
		//When
		Aula expected = aulaDao.buscarAulaPorNumeroAula(02);
		
		//Then
		assertThat(expected).isEqualTo(DatosDummy.aula02());
		
		verify(aulaRepository).buscarAulaPorNumeroAula(02);
	}
}
