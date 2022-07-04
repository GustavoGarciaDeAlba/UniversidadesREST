package com.ibm.academia.apirest.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;

@SpringBootTest
public class EmpleadoDAOImplTest {
	
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Test
	@DisplayName("Test: Buscar Empleado por Tipo de empleado")
	void findEmpleadoByTipoEmpleado() {
		//When
		empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		
		//Then
		verify(empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);;
	}
}
