package com.SistemaControlAcceso.app.restController;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/empresa/{id}")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable(value = "id") int empresaId){
		
		if (serviceEmpresa.obtenerEmpresabyID(empresaId) == null){
			  ResponseEntity.notFound();
		}		

		return ResponseEntity.ok(serviceEmpresa.obtenerEmpresabyID(empresaId));
	}
	
	@PostMapping("/empresa")
	public ResponseEntity<Empresa> guardarEmpresa(@RequestBody Empresa empresa){
		return new ResponseEntity<>(serviceEmpresa.guardarEmpresa(empresa),HttpStatus.CREATED);
	}
	

	@PutMapping("/empresa/{id}")
	public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable(value = "id") int empresaId,@Validated @RequestBody Empresa empresa){
		Empresa empresaActualizado = serviceEmpresa.actualizarEmpresa(empresa, empresaId);		
		return new ResponseEntity<>(empresaActualizado,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/empresa/{id}")
	public ResponseEntity<String> eliminarEmpresa(@PathVariable(value = "id") int empresaId){		
		serviceEmpresa.eliminarEmpresa(empresaId);
		
		
		return new ResponseEntity<>("Empresa eliminado con exito", HttpStatus.OK);
	
	}
	


}
