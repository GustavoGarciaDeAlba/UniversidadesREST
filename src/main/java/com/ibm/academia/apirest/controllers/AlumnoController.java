package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	/**
	 * Endpoint para crear un objeto Persona de tipo Alumno
	 * @param alumno Objeto alumno con la informacion a crear
	 * @return Retorna un objeto Persona de tipo alumno con codigo httpstatus 201
	 * @author GGdAE - 06/14/2022
	 */
	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno){
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar alumnos
	 * @return Retorna lista de alumnos
	 * @author GGdaE - 06/14/2022
	 */
	@GetMapping("/alumnos/lista")
	public ResponseEntity<?> obtenerTodos(){
		
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		
		if(alumnos.isEmpty()) {
			throw new NotFoundException("No existen alumnos");
		}
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener a un alumno por su ID
	 * @param alumnoId
	 * @returnRetorna un objeto de tipo Alumno
	 */
	@GetMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if(!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con el ID %d no existe", alumnoId));
		}
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar a una Persona de tipo Alumno
	 * @param alumnoId
	 * @param alumno
	 * @return Retorna el objeto alumno actualizado
	 */
	@PutMapping("/upd/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if(!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con el ID %d no existe", alumnoId));
		}
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
		
		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}
	
	/**
	 * EndPoint para eliminar a una Persona de tipo alumno
	 * @param alumnoId
	 * @return Retorna la respuesta de que la persona ha sido eliminada
	 */
	@DeleteMapping("/del/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Persona> alumno = alumnoDao.buscarPorId(alumnoId);
		
		if(!alumno.isPresent()){
			throw new NotFoundException(String.format("El alumno con ID: %d no existe", alumnoId));
		}
		
		alumnoDao.eliminarPorId(alumnoId);
		respuesta.put("OK", "Alumno ID: " + alumnoId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId){
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if(!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con el ID %d no existe", alumnoId));
		}
		
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		if(!oCarrera.isPresent()){
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
		}
		
		Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
		
		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}
}
