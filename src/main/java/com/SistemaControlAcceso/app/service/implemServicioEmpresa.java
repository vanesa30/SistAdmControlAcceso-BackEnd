package com.SistemaControlAcceso.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.SistemaControlAcceso.app.model.Empresa;
import com.SistemaControlAcceso.app.repository.EmpresaRepository;

//import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.SistemaControlAcceso.app.exceptions.ResourceNotFoundException;

@Service
public class implemServicioEmpresa implements IServicioEmpresa{

	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Override
	public Empresa guardarEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		empresa.setEstadoEmpresa("1");
		return empresaRepo.save(empresa);
	}

	@Override
	public Empresa actualizarEmpresa(Empresa empresa, int idEmpresa) {
		// TODO Auto-generated method stub
		
		Empresa empresa_act = empresaRepo.findById(idEmpresa).orElseThrow(null);
		
		empresa_act.setNroRuc(empresa.getNroRuc());
		empresa_act.setRazonSocial(empresa.getRazonSocial());
		empresa_act.setDireccPrincipal(empresa.getDireccPrincipal());
		empresa_act.setEstadoEmpresa(empresa.getEstadoEmpresa());
		
		
		Empresa nuevaEmpresa = empresaRepo.save(empresa_act);		
		return nuevaEmpresa;
				
	}

	@Override
	public Empresa obtenerEmpresabyID(int idEmpresa) {
		// TODO Auto-generated method stub
		
		Empresa empresa = empresaRepo.findById(idEmpresa).orElseThrow(null);
			//	.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", idEmpresa));	
		
	
		/*	if (empresa == null){
				throw  new ResourceNotFoundException("Comentario", "id", idEmpresa);
			}*/
	//	Comentario comentario = comentarioRepositorio.findById(comentarioId)
		//		.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
		
		
		//.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
		return empresa;
		
		
	}

	@Override
	public List<Empresa> obtenerEmpresas() {
		// TODO Auto-generated method stub
		List<Empresa> empresas = empresaRepo.findAll() ;		
		return empresas;
	}

	@Override
	public void eliminarEmpresa(int idEmpresa) {
		// TODO Auto-generated method stub
		
			Empresa empresa = empresaRepo.findById(idEmpresa).orElseThrow(null);		
			empresaRepo.delete(empresa);
		
		
	}

}
