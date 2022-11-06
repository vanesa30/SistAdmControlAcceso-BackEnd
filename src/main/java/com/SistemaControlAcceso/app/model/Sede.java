package com.SistemaControlAcceso.app.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import javax.validation.constraints.NotNull;
@Entity
@Table(name="sede")
public class Sede {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)	
   private int idSede;

   @Column(name = "Direccion", length = 150, nullable = false)
   private String direccion;

   @Column(name = "Estado", length = 20, nullable = false)
   private String estado;
   
   @ManyToOne(fetch = FetchType.LAZY,optional = false)
   @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   @JoinColumn(name = "idEmpresa", nullable = false)
   private Empresa empresa;
        
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro", nullable = false)
	private Calendar fechaRegistro;
	

	@PrePersist
	private void Presist(){
		
	     Calendar dateActual = Calendar.getInstance();
	     dateActual.getTime();
	     setFechaRegistro(dateActual);
	}

		public int getIdSede() {
			return idSede;
		}
		
		public void setIdSede(int idSede) {
			this.idSede = idSede;
		}
		
		public String getDireccion() {
			return direccion;
		}
		
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		
		public String getEstado() {
			return estado;
		}
		
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		public Empresa getEmpresa() {
			return empresa;
		}
		
		public void setEmpresa(Empresa empresa) {
			this.empresa = empresa;
		}
		
		public Calendar getFechaRegistro() {
			return fechaRegistro;
		}
		
		public void setFechaRegistro(Calendar fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
   
   
   
}
