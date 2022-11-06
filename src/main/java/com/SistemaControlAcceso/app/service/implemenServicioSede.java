package com.SistemaControlAcceso.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SistemaControlAcceso.app.model.Empresa;
import com.SistemaControlAcceso.app.model.Sede;
import com.SistemaControlAcceso.app.repository.EmpresaRepository;
import com.SistemaControlAcceso.app.repository.SedeRepository;




@Service
public class implemenServicioSede implements IServicioSede {

		@Autowired
		private SedeRepository sedeRepository;
		
		@Autowired 
		EmpresaRepository empresaRepository;
	
	@Override
	public Sede guardarSede(int empresaId,Sede sede) {
		// TODO Auto-generated method stub
		
		Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(null);
		//Optional<Empresa> empresa = empresaRepository.findById(sede.getEmpresa().getIdEmpresa());
		
		sede.setEmpresa(empresa);
		
		Sede nuevaSede = sedeRepository.save(sede);
			
		return nuevaSede;
		
	}
	

	
	

	@Override
	public Sede actualizarSede(Sede sede, int idSede) {
		
		Sede sedeactual = sedeRepository.findById(idSede).orElseThrow(null);
		
		//Optional<Empresa> empresa = empresaRepository.findById(sede.getEmpresa().getIdEmpresa());
		//Empresa empresa = empresaRepository.findById(sede.getEmpresa().getIdEmpresa()).orElseThrow(null);
		
		sedeactual.setEmpresa(sede.getEmpresa());
		sedeactual.setDireccion(sede.getDireccion());
		sedeactual.setEstado(sede.getEstado());
	
		// TODO Auto-generated method stub
	
		
		Sede nuevaSede = sedeRepository.save(sedeactual);
		
		return nuevaSede;			
		
		
	}

	@Override
	public Sede obtenerSedebyID(int idSede) {
		// TODO Auto-generated method stub
		Sede sede = sedeRepository.findById(idSede).orElseThrow(null);
		
		return sede;
		
		
		
	}
	
	

	@Override
	public List<Sede> obtenerSedesporEmpresa(int id) {
		// TODO Auto-generated method stub
		
		List<Sede> sedes = sedeRepository.findByEmpresaId(id) ;
		return sedes;
		
	}
	
	/*@Override
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {
		List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
	}
*/
	

	@Override
	public void eliminarSede(int idSede) {
		// TODO Auto-generated method stub
		
		Sede sede = sedeRepository.findById(idSede).orElseThrow(null);
		
		sedeRepository.delete(sede);
		
		
	}





	@Override
	public List<Sede> obtenerSedes() {
		// TODO Auto-generated method stub
		return null;
	}






}
