package com.SistemaControlAcceso.app.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.SistemaControlAcceso.app.model.Empresa;
import com.SistemaControlAcceso.app.service.IServicioEmpresa;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpresaController {
	
	@Autowired
	private IServicioEmpresa serviceEmpresa;
	
	@GetMapping("/empresa")
	public List<Empresa> listarEmpresas(){
		return serviceEmpresa.obtenerEmpresas();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/empresa/{id}")
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> obtenerEmpresaPorId(@PathVariable(value = "id") int empresaId){
		
	
		Empresa empresa = null;
		Map<String,Object> response = new HashMap<>();
				
		try {
			
			empresa = serviceEmpresa.obtenerEmpresabyID(empresaId);
			
	
			
		}	
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}		
		if (empresa == null) {
			response.put("mensaje", "La empresa ID: " + empresaId + " No existe." );
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
	
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/empresa")
	public ResponseEntity<?> guardarEmpresa(@Valid @RequestBody Empresa empresa , BindingResult result){
		
		Empresa empresaNew = null;
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		try{
			empresaNew = serviceEmpresa.guardarEmpresa(empresa);
			
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		response.put("mensaje","La empresa ha sido creada con ??xito");
		response.put("empresa",empresaNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/empresa/{id}")
	public ResponseEntity<?> actualizarEmpresa(@Valid @RequestBody Empresa empresa,BindingResult result,@PathVariable(value = "id") int empresaId){

		
		Empresa empresa_act = serviceEmpresa.obtenerEmpresabyID(empresaId);
		
		Map<String,Object> response = new HashMap<>();	
		Empresa empresaActualizado = null;
		
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if (empresa_act == null) {
			response.put("mensaje", "Error: no se pudo editar,la empresa ID: " + empresaId +" no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			 		 
			empresa_act.setNroRuc(empresa.getNroRuc());
			empresa_act.setRazonSocial(empresa.getRazonSocial());
			empresa_act.setDireccPrincipal(empresa.getDireccPrincipal());
			empresa_act.setEstadoEmpresa("1");			

		    empresaActualizado = serviceEmpresa.guardarEmpresa(empresa_act);			 
			 
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
     	response.put("mensaje","La empresa ha sido actualizada con ??xito");
		response.put("empresa",empresaActualizado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/empresa/{id}")
	public ResponseEntity<?> eliminarEmpresa(@PathVariable(value = "id") int empresaId){		
		
		Empresa empresa =null;
		Map<String, Object> response = new HashMap<>();
		
		try {
		//	empresa = serviceEmpresa.obtenerEmpresabyID(empresaId);
			
			serviceEmpresa.eliminarEmpresa(empresaId);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar ea la empresa de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con ??xito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
	
	}
	


}
