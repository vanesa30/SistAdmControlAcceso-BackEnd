package com.SistemaControlAcceso.app.service;

import com.SistemaControlAcceso.app.model.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
}
