package com.SistemaControlAcceso.app.restController;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaControlAcceso.app.model.Sede;
import com.SistemaControlAcceso.app.service.IServicioSede;

@RestController
@RequestMapping("/api")
public class SedeController {

	@Autowired
	private IServicioSede serviceSede;
	
	@GetMapping("/sede/empresa/{empresaId}")
	public List<Sede> listarSedesPorEmpresaId(@PathVariable(value = "empresaId") int id){
		return serviceSede.obtenerSedesporEmpresa(id);
	}
		
/*	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId){
		return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
	}*/
	
	@GetMapping("/sede/{id}")
	public ResponseEntity<Sede> obtenerSedePorId(@PathVariable(value = "id") int sedeId){
		return ResponseEntity.ok(serviceSede.obtenerSedebyID(sedeId));
	}
	
	@PostMapping("/sede/{empresaId}")
	public ResponseEntity<Sede> guardarSede(@PathVariable(value = "empresaId") int empresaId,
											@Validated @RequestBody Sede sede){
		
		
		return new ResponseEntity<>(serviceSede.guardarSede(empresaId,sede),HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/sede/{id}")
	public ResponseEntity<Sede> actualizarSede(@PathVariable(value = "id") int sedeId,@Validated @RequestBody Sede sede){
		Sede sedeActualizado = serviceSede.actualizarSede(sede, sedeId);		
		return new ResponseEntity<>(sedeActualizado,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/sede/{id}")
	public ResponseEntity<String> eliminarSede(@PathVariable(value = "id") int sedeId){
		
		serviceSede.eliminarSede(sedeId);
		return new ResponseEntity<>("Sede eliminado con exito", HttpStatus.OK);
	
	}
	

	
}
