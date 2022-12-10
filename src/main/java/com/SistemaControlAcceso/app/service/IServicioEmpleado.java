package com.SistemaControlAcceso.app.service;

import java.util.List;

import com.SistemaControlAcceso.app.model.Empleado;
import com.SistemaControlAcceso.app.model.TipoEmpleado;


public interface IServicioEmpleado {

    public Empleado guardarEmpleado(Empleado empleado);	
	
	public Empleado actualizarEmpleado(Empleado empleado, int idEmpleado);
	
	public Empleado obtenerEmpleadobyID(Long idEmpleado);
	
	public List<Empleado> obtenerEmpleados();
	
	public void eliminarEmpleado(Long idEmpleado);
	
	public Empleado desactivarEmpleado(Empleado empleado);
	
	public List<TipoEmpleado> findAllTipoEmpleados();
	
}
