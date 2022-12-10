package com.SistemaControlAcceso.app.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.SistemaControlAcceso.app.model.Usuario;
import com.SistemaControlAcceso.app.service.IUsuarioService;
import com.SistemaControlAcceso.app.service.UsuarioService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
//	@Autowired
	//private UsuarioService usuarioService;
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		
		info.put("nombre", usuario.getNombre());
	    info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
	
	

}
