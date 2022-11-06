package com.SistemaControlAcceso.app.service;

import java.util.List;

import com.SistemaControlAcceso.app.model.Sede;

public interface IServicioSede {

	
	public Sede guardarSede(int empresaId,Sede sede);	
	
	public Sede actualizarSede(Sede sede, int idSede);
	
	public Sede obtenerSedebyID(int idEmpresa);
	
	public List<Sede> obtenerSedes();
	
	public List<Sede> obtenerSedesporEmpresa(int id);
	
	public void eliminarSede(int idSede);
	
	
	
}
