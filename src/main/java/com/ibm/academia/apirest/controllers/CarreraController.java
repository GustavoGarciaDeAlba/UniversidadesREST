package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.CarreraMapper;
import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.CarreraDAO;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraDAO carreraDao;
	
	@GetMapping("/lista/carreras")
	public List<Carrera> buscarTodas(){
		
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();		
		if(carreras.isEmpty()) {
			throw new BadRequestException("No existen carreras");
		}
		
		return carreras;
	}
	
	@GetMapping("/id/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable(value = "carreraId") Integer carreraId) {
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		if(!oCarrera.isPresent()) {
			throw new BadRequestException(String.format("La carrera con ID: %d no existe", carreraId));
		}
		return oCarrera.get();
	}
	
	@PostMapping
	public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista de Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Carrera carreraGuardada = carreraDao.guardar(carrera);
		
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para actualizar un objeto de tipo carrera
	 * @param carreraId Parametro para buscar la carrera
	 * @param carrera Objeto con la informacion a modificar
	 * @return Retorna un objeto de tipo carrera con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto
	 * @author GGdAE - 06/14/2022
	 */
	@PutMapping("/upd/carreraId/{carreraId}")
	public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera){
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		
		if(!oCarrera.isPresent()){
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
		}
		
		Carrera carreraActualizada = carreraDao.actualizar(oCarrera.get(), carrera);
		
		return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK);
	}
	
	@DeleteMapping("/carreraId/{carreraId}")
	public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Carrera> carrera = carreraDao.buscarPorId(carreraId);
		
		if(!carrera.isPresent()){
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
		}
		
		carreraDao.eliminarPorId(carreraId);
		respuesta.put("OK", "Carrera ID: " + carreraId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
	/**
	 * Endpoint para consultar todas las carreras
	 * @return Retorna una lista de carreras en DTO
	 * @NotfoundException En caso de no encuentre ningun elemento en la base de datos
	 * @author GGdAE - 07/16/2022
	 */ 

	@GetMapping("/carreras/dto")
	public ResponseEntity<?> obtenerCarrerasDTO(){
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		
		if(carreras.isEmpty()) {
			throw new NotFoundException("No existen carreras en la base de datos");
		}
		List<CarreraDTO> listaCarreras = carreras.stream().map(CarreraMapper::mapCarrera).collect(Collectors.toList());
		
		return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
	}
}
