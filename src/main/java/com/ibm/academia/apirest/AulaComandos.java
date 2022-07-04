package com.ibm.academia.apirest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.PabellonDAO;

@Component
public class AulaComandos implements CommandLineRunner{
	
	@Autowired
	private AulaDAO aulaDao;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Override
	public void run(String... args) throws Exception {
		
//		//Guardar aula1 en BD
//		Aula aula1 = new Aula(null, 01, "20x20", 20, Pizarron.PIZZARRA_BLANCA);
//		Aula aulaGuardada = aulaDao.guardar(aula1);
//		System.out.println(aulaGuardada.toString());		
		
//		//Guardar aula2 en BD
//		Aula aula2 = new Aula(null, 02, "30x30", 30, Pizarron.PIZZARRA_TIZA);
//		Aula aulaGuardada2 = aulaDao.guardar(aula2);
//		System.out.println(aulaGuardada2.toString());
		
		//BUSCAR AULA POR NUMERO DE AULA
//		Aula aulaNumero = aulaDao.buscarAulaPorNumeroAula(2);
//		System.out.println(aulaNumero);
		
		//BUSCAR AULA POR NUMERO TIPO DE PIZARRON
//		Iterable<Aula> aulaTipoPizarron = aulaDao.buscarAulasPorTipoPizarron(Pizarron.PIZZARRA_BLANCA);
//		aulaTipoPizarron.forEach(System.out::println);
		
		//BUSCAR AULA POR PABELLON
//		Optional<Aula> aula = aulaDao.buscarPorId(2);
//		Optional<Pabellon> pabellon = pabellonDao.buscarPorId(1);
//		aula.get().setPabellon(pabellon.get());
//		aulaDao.guardar(aula.get());
		
		//Query
//		Optional<Pabellon> pabellon = pabellonDao.buscarPorId(1);
//		Iterable<Aula> aulasPabellon = aulaDao.buscarAulasPorPabellon(pabellon.get().getNombre());
//		aulasPabellon.forEach(System.out::println);
	}
	
}
