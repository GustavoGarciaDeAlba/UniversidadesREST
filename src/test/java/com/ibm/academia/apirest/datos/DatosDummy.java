package com.ibm.academia.apirest.datos;

import java.math.BigDecimal;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

public class DatosDummy {
	
	public static Carrera carrera01() {
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5);
	}
	
	public static Carrera carrera02() {
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
	}
	
	public static Carrera carrera03() {
		return new Carrera(null, "Ingenieria Industrial", 60, 5);
	}
	
	public static Pabellon pabellon01() {
		return new Pabellon(null, 50.00, "pabellon 1", new Direccion());
	}
	
	public static Pabellon pabellon02() {
		return new Pabellon(null, 60.00, "pabellon 2", new Direccion());
	}
	
	public static Aula aula01() {
		Aula aula01 = new Aula(null, 01, "20x20", 20, Pizarron.PIZZARRA_BLANCA);
		aula01.setPabellon(pabellon01());
		return aula01;
	}
	
	public static Aula aula02() {
		return new Aula(null, 02, "30x30", 30, Pizarron.PIZZARRA_TIZA);
	}
	
	public static Aula aula03() {
		return new Aula(null, 03, "40x40", 40, Pizarron.PIZZARRA_BLANCA);
	}
	
	public static Persona empleado01() {
		return new Empleado(null, "Lautaro", "Lopez", "25174036", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona empleado02() {
		return new Empleado(null, "Lenadro", "Lopez", "25174030", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}
	
	public static Persona empleado03() {
		return new Empleado(null, "Jorge", "Nava", "25174040", new Direccion(), new BigDecimal("90000.00"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona profesor01() {
		return new Profesor(null, "Martin", "Lugune", "33908461", new Direccion(), new BigDecimal("60000.00"));
	}
	
	public static Persona profesor02() {
		return new Profesor(null, "Cesar", "Cueva", "33908465", new Direccion(), new BigDecimal("70000.00"));
	}
	
	public static Persona alumno01() {
		return new Alumno(null, "Jhon", "Benitez", "452337715", new Direccion());
	}
	
	public static Persona alumno02() {
		return new Alumno(null, "Fer", "Perez", "452337713", new Direccion());
	}
	
	public static Persona alumno03() {
		return new Alumno(null, "Cesar", "Padilla", "452337711", new Direccion());
	}
}
