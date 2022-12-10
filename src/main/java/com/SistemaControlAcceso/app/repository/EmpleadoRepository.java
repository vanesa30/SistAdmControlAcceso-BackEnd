package com.SistemaControlAcceso.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SistemaControlAcceso.app.model.Empleado;
import com.SistemaControlAcceso.app.model.TipoEmpleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

	@Query("from TipoEmpleado")
	public List<TipoEmpleado> findAllTipoEmpleados();
	
}
