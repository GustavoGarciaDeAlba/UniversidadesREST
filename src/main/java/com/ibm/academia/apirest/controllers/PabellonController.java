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
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {
	@Autowired
	private PabellonDAO pabellonDao;
	
	@GetMapping("/lista/pabellones")
	public ResponseEntity<?> buscarTodas(){
		
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();		
		if(pabellones.isEmpty()) {
			throw new BadRequestException("No existen pabellones");
		}
		
		return new ResponseEntity<List<Pabellon>>(pabellones,HttpStatus.OK);
	}
	
	@GetMapping("/id/{pabellonId}")
	public ResponseEntity<?> buscarPabellonPorId(@PathVariable(value = "pabellonId") Integer pabellonId) {
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		if(!oPabellon.isPresent()) {
			throw new BadRequestException(String.format("El pabellon con ID: %d no existe", pabellonId));
		}
		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para crear un objeto Pabellon
	 * @param pabellon Objeto Pabellon con la informacion a crear
	 * @return Retorna un objeto Pabellon
	 * @author GGdAE - 06/14/2022
	 */
	@PostMapping
	public ResponseEntity<?> guardarPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista de Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Pabellon pabellonGuardada = pabellonDao.guardar(pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para actualizar un objeto de tipo pabellon
	 * @param pabellonId Parametro para buscar la pabellon
	 * @param pabellon Objeto con la informacion a modificar
	 * @return Retorna un objeto de tipo pabellon con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto
	 * @author GGdAE - 06/14/2022
	 */
	@PutMapping("/upd/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent()){
			throw new NotFoundException(String.format("La pabellon con ID: %d no existe", pabellonId));
		}
		
		Pabellon pabellonActualizada = pabellonDao.actualizar(oPabellon.get(), pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonActualizada, HttpStatus.OK);
	}
	
	@DeleteMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Pabellon> pabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!pabellon.isPresent()){
			throw new NotFoundException(String.format("La pabellon con ID: %d no existe", pabellonId));
		}
		
		pabellonDao.eliminarPorId(pabellonId);
		respuesta.put("OK", "Pabellon ID: " + pabellonId + " eliminado existosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
}
