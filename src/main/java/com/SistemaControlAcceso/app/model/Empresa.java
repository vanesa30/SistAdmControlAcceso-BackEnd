package com.SistemaControlAcceso.app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="empresa")
public class Empresa {

	@Id
	@Column(name="id_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	@Size(min = 11, max = 20)
	@NotEmpty	
	@Column(name = "nro_ruc", unique=true, length = 20, nullable = false)
	private String nroRuc;
	@NotEmpty
	@Size(min = 5,max = 150)
	@Column(name = "razon_social", length = 150, nullable = false)
	private String razonSocial;
	@NotEmpty
	@Size(min = 10,max = 150)
	@Column(name = "direcc_principal", length = 150, nullable = false)
	private String direccPrincipal;
	@Column(name = "estado_empresa", length = 20, nullable = false)
	private String estadoEmpresa;	
	
	@JsonBackReference
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Sede> sedes = new HashSet<>();
	  
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro", nullable = false)
	private Calendar fechaRegistro;

	@PrePersist
	private void Presist(){
		
	     Calendar dateActual = Calendar.getInstance();
	     dateActual.getTime();
	     setFechaRegistro(dateActual);
	}

	public Empresa(int id, String nroRuc, String razonSocial, String direccPrincipal,String estadoEmpresa) {
		super();
		this.id = id;
		this.nroRuc = nroRuc;
		this.razonSocial = razonSocial;
		this.direccPrincipal = direccPrincipal;
		this.estadoEmpresa = estadoEmpresa;
	}
	public Empresa() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNroRuc() {
		return nroRuc;
	}
	public void setNroRuc(String nroRuc) {
		this.nroRuc = nroRuc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getEstadoEmpresa() {
		return estadoEmpresa;
	}
	public void setEstadoEmpresa(String estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}
	
	public Calendar getFechaRegistro() {
		return fechaRegistro;
		
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Set<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(Set<Sede> sedes) {
		this.sedes = sedes;
	}

	public String getDireccPrincipal() {
		return direccPrincipal;
	}

	public void setDireccPrincipal(String direccPrincipal) {
		this.direccPrincipal = direccPrincipal;
	}
}
