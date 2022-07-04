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
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	/**
	 * Endpoint para crear un objeto Persona de tipo empleado
	 * @param empleado Objeto empleado con la informacion a crear
	 * @return Retorna un objeto Persona de tipo empleado con codigo httpstatus 201
	 * @author GGdAE - 06/14/2022
	 */
	@PostMapping
	public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado){
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar empleados
	 * @return Retorna lista de empleados
	 * @author GGdaE - 06/14/2022
	 */
	@GetMapping("/empleados/lista")
	public ResponseEntity<?> obtenerTodos(){
		
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		
		if(empleados.isEmpty()) {
			throw new NotFoundException("No existen empleados");
		}
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener a un empleado por su ID
	 * @param empleadoId
	 * @return Retorna un objeto de tipo Empleado
	 */
	@GetMapping("/empleadoId/{empleadoId}")
	public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer empleadoId){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		if(!oEmpleado.isPresent()) {
			throw new NotFoundException(String.format("El empleado con el ID %d no existe", empleadoId));
		}
		return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para actualizar a una Persona de tipo Empleado
	 * @param empleadoId
	 * @param empleado
	 * @return Retorna el objeto empleado actualizado
	 */
	@PutMapping("/upd/empleadoId/{empleadoId}")
	public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer empleadoId, @RequestBody Persona empleado){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		if(!oEmpleado.isPresent()) {
			throw new NotFoundException(String.format("El empleado con el ID %d no existe", empleadoId));
		}
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(oEmpleado.get(), empleado);
		
		return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	}
	
	/**
	 * EndPoint para eliminar a una Persona de tipo empleado
	 * @param empleadoId
	 * @return Retorna la respuesta de que la persona ha sido eliminada
	 */
	@DeleteMapping("/del/empleadoId/{empleadoId}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Persona> empleado = empleadoDao.buscarPorId(empleadoId);
		if(!empleado.isPresent()){
			throw new NotFoundException(String.format("El empleado con ID: %d no existe", empleadoId));
		}
		empleadoDao.eliminarPorId(empleadoId);
		respuesta.put("OK", "Empleado ID: " + empleadoId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
}
