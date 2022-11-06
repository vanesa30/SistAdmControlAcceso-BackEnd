package com.SistemaControlAcceso.app.service;

import java.util.List;

import com.SistemaControlAcceso.app.model.Empresa;


public interface IServicioEmpresa {

	public Empresa guardarEmpresa(Empresa empresa);	
	
	public Empresa actualizarEmpresa(Empresa empresa, int idEmpresa);
	
	public Empresa obtenerEmpresabyID(int idEmpresa);
	
	public List<Empresa> obtenerEmpresas();
	
	public void eliminarEmpresa(int idEmpresa);
	
}


