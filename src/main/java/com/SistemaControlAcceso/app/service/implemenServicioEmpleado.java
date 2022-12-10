package com.SistemaControlAcceso.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SistemaControlAcceso.app.model.Empleado;
import com.SistemaControlAcceso.app.model.TipoEmpleado;
import com.SistemaControlAcceso.app.repository.EmpleadoRepository;

@Service
public class implemenServicioEmpleado implements IServicioEmpleado{

	
	@Autowired 
	EmpleadoRepository empleadoRepo;



	@Override
	public List<Empleado> obtenerEmpleados() {
		// TODO Auto-generated method stub
		List<Empleado> empleados = empleadoRepo.findAll() ;		
		return empleados;
	}

	@Override
	public void eliminarEmpleado(Long idEmpleado) {

		Empleado empleado =this.obtenerEmpleadobyID(idEmpleado);
		
		if(empleado != null) {
			
			empleadoRepo.delete(empleado);
		}			
	
	}

	@Override
	public Empleado guardarEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoRepo.save(empleado);
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado, int idEmpleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado obtenerEmpleadobyID(Long idEmpleado) {
		// TODO Auto-generated method stub
		Empleado empleado = empleadoRepo.findById(idEmpleado).orElse(null);
		return empleado;
	}

	@Override
	public Empleado desactivarEmpleado(Empleado empleado) {
		
		// TODO Auto-generated method stub		
		
		return empleadoRepo.save(empleado);
	}

	@Override
	public List<TipoEmpleado> findAllTipoEmpleados() {
		// TODO Auto-generated method stub
		return empleadoRepo.findAllTipoEmpleados();
	}
	


}
