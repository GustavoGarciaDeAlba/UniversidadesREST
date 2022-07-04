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
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	/**
	 * Endpoint para crear un objeto Persona de tipo profesor
	 * @param profesor Objeto profesor con la informacion a crear
	 * @return Retorna un objeto Persona de tipo profesor con codigo httpstatus 201
	 * @author GGdAE - 06/14/2022
	 */
	@PostMapping
	public ResponseEntity<?> crearProfesor(@RequestBody Persona profesor){
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar profesors
	 * @return Retorna lista de profesores
	 * @author GGdaE - 06/14/2022
	 */
	@GetMapping("/profesores/lista")
	public ResponseEntity<?> obtenerTodos(){
		
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		
		if(profesores.isEmpty()) {
			throw new NotFoundException("No existen profesores");
		}
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener a un profesor por su ID
	 * @param profesorId
	 * @return Retorna un objeto de tipo Profesor
	 */
	@GetMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer profesorId){
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		if(!oProfesor.isPresent()) {
			throw new NotFoundException(String.format("El profesor con el ID %d no existe", profesorId));
		}
		return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar a una Persona de tipo Profesor
	 * @param profesorId
	 * @param profesor
	 * @return Retorna el objeto profesor actualizado
	 */
	@PutMapping("/upd/profesorId/{profesorId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Persona profesor){
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		if(!oProfesor.isPresent()) {
			throw new NotFoundException(String.format("El profesor con el ID %d no existe", profesorId));
		}
		Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(oProfesor.get(), profesor);
		
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}
	
	/**
	 * EndPoint para eliminar a una Persona de tipo profesor
	 * @param profesorId
	 * @return Retorna la respuesta de que la persona ha sido eliminada
	 */
	@DeleteMapping("/del/profesorId/{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Integer profesorId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Persona> profesor = profesorDao.buscarPorId(profesorId);
		if(!profesor.isPresent()){
			throw new NotFoundException(String.format("El profesor con ID: %d no existe", profesorId));
		}
		profesorDao.eliminarPorId(profesorId);
		respuesta.put("OK", "Profesor ID: " + profesorId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
}
