package com.SistemaControlAcceso.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name ="empleado")
public class Empleado implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Empleado")
	private Long id;
	
	
	@NotEmpty
	@Size(min = 5, max = 8)
	@Column(length=8,nullable = false,unique=true)
	private String codigo;
	
	@NotEmpty
	@Size(min = 5, max = 100)
	@Column(length = 100, nullable = false)
	private String nombre;
	
	@NotEmpty
	@Size(min = 7, max = 100)
	@Column(length = 100, nullable = false)
	private String apellido;
	
	@NotNull()
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Empresa empresa;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_empleado", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoEmpleado tipo_empleado;
	
	
	@Column(name="nro_identificacion", unique=true)
	private Long nroIdentificacion;
	
	@Email
	@Column(length=100, unique=true)
	private String email;
	
	@NotNull
	@Column(name="fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@NotNull
	private boolean estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inactivo")
	private Date fechaInactivo;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(Long nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaInactivo() {
		return fechaInactivo;
	}

	public void setFechaInactivo(Date fechaInactivo) {
		this.fechaInactivo = fechaInactivo;
	}

	public TipoEmpleado getTipo_empleado() {
		return tipo_empleado;
	}



	public void setTipo_empleado(TipoEmpleado tipo_empleado) {
		this.tipo_empleado = tipo_empleado;
	}
	
	
}
