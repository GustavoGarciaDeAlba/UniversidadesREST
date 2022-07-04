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
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController {
	
	@Autowired
	private AulaDAO aulaDao;
	
	@GetMapping("/lista/aulas")
	public ResponseEntity<?> buscarTodas(){
		
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();		
		if(aulas.isEmpty()) {
			throw new BadRequestException("No existen aulas");
		}
		
		return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
	}
	
	@GetMapping("/id/{aulaId}")
	public ResponseEntity<?> buscarAulaPorId(@PathVariable(value = "aulaId") Integer aulaId) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if(!oAula.isPresent()) {
			throw new BadRequestException(String.format("El aula con ID: %d no existe", aulaId));
		}
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para crear un objeto Aula
	 * @param aula Objeto Aula con la informacion a crear
	 * @return Retorna un objeto Aula
	 * @author GGdAE - 06/14/2022
	 */
	@PostMapping
	public ResponseEntity<?> guardarAula(@Valid @RequestBody Aula aula, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista de Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Aula aulaGuardada = aulaDao.guardar(aula);
		
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para actualizar un objeto de tipo aula
	 * @param aulaId Parametro para buscar la aula
	 * @param aula Objeto con la informacion a modificar
	 * @return Retorna un objeto de tipo aula con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto
	 * @author GGdAE - 06/14/2022
	 */
	@PutMapping("/upd/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent()){
			throw new NotFoundException(String.format("La aula con ID: %d no existe", aulaId));
		}
		
		Aula aulaActualizada = aulaDao.actualizar(oAula.get(), aula);
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	@DeleteMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Aula> aula = aulaDao.buscarPorId(aulaId);
		
		if(!aula.isPresent()){
			throw new NotFoundException(String.format("La aula con ID: %d no existe", aulaId));
		}
		
		aulaDao.eliminarPorId(aulaId);
		respuesta.put("OK", "Aula ID: " + aulaId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
}
