package com.ibm.academia.apirest;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@Component
public class EmpleadoComandos implements CommandLineRunner {
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Override
	public void run(String... args) throws Exception {
//		Direccion dir = new Direccion("Calle 2","32", "34050", null, null, "Providencia");
//		Persona	 empleado1 = new Empleado(null, "Pepe", "Cueva", "1285647839", dir, new BigDecimal(15000), TipoEmpleado.ADMINISTRATIVO);
//		
//		Persona personaGuardada = empleadoDao.guardar(empleado1);
//		System.out.println(personaGuardada.toString());		
		
//		Iterable<Persona> empleadosTipoEmpleado = empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
//		empleadosTipoEmpleado.forEach(System.out::println);	
	}

}
