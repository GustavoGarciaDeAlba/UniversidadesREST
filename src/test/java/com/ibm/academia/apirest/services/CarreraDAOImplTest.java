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
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

public class CarreraDAOImplTest {
	
	CarreraDAO carreraDao;
	CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp() {
		carreraRepository = mock(CarreraRepository.class);
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar carrera por nombre")
	void findCarrerasByNombreContains() {
		//Given
		String nombre = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContains(nombre))
		.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//When
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombre);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContains(nombre);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre No case Sensitive")
	void findCarrerasByNombreContainsIgnoreCase() {
		//Given
		String nombre = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre))
		.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//When
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombre);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras despues de N años")
	void findCarrerasByCantidadAniosAfter() {
		//Given
		Integer cantidad = 4;
		when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad))
		.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//When
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidad);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
		
		verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);
	}
}
