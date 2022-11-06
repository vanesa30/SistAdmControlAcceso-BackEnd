package com.SistemaControlAcceso.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaControlAcceso.app.model.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Integer>{
	public List<Sede> findByEmpresaId(int empresaId); 
}
