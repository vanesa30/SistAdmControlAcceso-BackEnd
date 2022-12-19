package com.SistemaControlAcceso.app.restController;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaControlAcceso.app.model.Empleado;
import com.SistemaControlAcceso.app.model.Empresa;
import com.SistemaControlAcceso.app.model.TipoEmpleado;
import com.SistemaControlAcceso.app.service.IServicioEmpleado;
import com.SistemaControlAcceso.app.service.IServicioEmpresa;





@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {
	
	@Autowired
	private IServicioEmpleado serviceEmpleado;
	
	@Autowired
	private IServicioEmpresa serviceEmpresa;
	
	@GetMapping("/empleado")
	public List<Empleado> listarEmpleados(){
		return serviceEmpleado.obtenerEmpleados();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/empleado/{id}")
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable(value = "id") Long empleadoId){
		
	
		Empleado empleado = null;
		Map<String,Object> response = new HashMap<>();
				
		try {
			
			empleado = serviceEmpleado.obtenerEmpleadobyID(empleadoId);				
			
		}	
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}		
		if (empleado == null) {
			response.put("mensaje", "El empleado ID: " + empleadoId + " No existe." );
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/empleado")
	public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Empleado empleado , BindingResult result){
		
		Empleado empleadoNew = null;
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
			
			
			/*Empresa empresa =serviceEmpresa.obtenerEmpresabyID(1);
			empleado.setEmpresa(empresa);	
			
			TipoEmpleado tipoEmpleado =serviceEmpleado.findAllTipoEmpleados().get(0);
			empleado.setEmpresa(empresa);	
			empleado.setTipo_empleado(tipoEmpleado);*/
			empleadoNew = serviceEmpleado.guardarEmpleado(empleado);
			
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		response.put("mensaje","El empleado ha sido creada con éxito");
		response.put("empresa",empleadoNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody Empleado empleado,BindingResult result,@PathVariable(value = "id") Long empleadoId){

		
		Empleado empleado_act = serviceEmpleado.obtenerEmpleadobyID(empleadoId);
		
		Map<String,Object> response = new HashMap<>();	
		Empleado empleadoActualizado = null;
		
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if (empleado_act == null) {
			response.put("mensaje", "Error: no se pudo editar,el empleado ID: " + empleadoId +" no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			empleado_act.setNombre(empleado.getNombre());
			empleado_act.setApellido(empleado.getApellido());
			empleado_act.setEmail(empleado.getEmail());
			//empleado_act.set		 
			empleado_act.setTipo_empleado(empleado.getTipo_empleado());			
			empleado_act.setNroIdentificacion(empleado.getNroIdentificacion());
            
			empleadoActualizado = serviceEmpleado.guardarEmpleado(empleado_act);			 
			 
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
     	response.put("mensaje","El empleado ha sido actualizada con éxito");
		response.put("empleado",empleadoActualizado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/empleado/inactivo/{id}")
	public ResponseEntity<?> descativarEmpleado(@PathVariable(value = "id") Long empleadoId){

		
		Empleado empleado_act = serviceEmpleado.obtenerEmpleadobyID(empleadoId);
		
		Map<String,Object> response = new HashMap<>();	
		Empleado empleadoActualizado = null;
		
		

		
		if (empleado_act == null) {
			response.put("mensaje", "Error: no se pudo desactivar,el empleado ID: " + empleadoId +" no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			Calendar calendar = Calendar.getInstance();
		
			
			empleado_act.setEstado(false);
			empleado_act.setFechaInactivo(calendar.getTime());
			
			empleadoActualizado = serviceEmpleado.guardarEmpleado(empleado_act);
			 
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos" );
			response.put("error", ex.getMessage().concat(" :").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
     	response.put("mensaje","Se ha desactivado al empleado " + empleado_act.getNombre()  + " con éxito");
		//response.put("empleado",empleadoActualizado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable(value = "id") Long empleadoId){		
		
		Empleado empleado =null;
		Map<String, Object> response = new HashMap<>();
		
		try {

			serviceEmpleado.eliminarEmpleado(empleadoId);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar al empleado de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
	
	}
	
	
	@GetMapping("/empleado/tipoEmpleados")
	public List<TipoEmpleado> listarRegiones(){
		return serviceEmpleado.findAllTipoEmpleados();
	}
	


}
