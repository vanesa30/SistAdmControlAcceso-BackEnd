package com.SistemaControlAcceso.app.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override

	/*
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers(HttpMethod.GET, "/api/empresa").permitAll()
		//.antMatchers(HttpMethod.GET, "/api/empresa").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.GET, "/api/empresa").permitAll()
		//.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/clientes/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/empresa/**").permitAll()
		
		/*.antMatchers("/api/empresa/**").hasAnyRole("USER","ADMIN")
	
		.antMatchers(HttpMethod.GET, "/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/empresa/**").permitAll()
		
		*/
		
		//"/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
		/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()

		.anyRequest().authenticated()

		.and().cors().configurationSource(corsConfigurationSource());
	}*/
	
	
	public void configure(HttpSecurity http) throws Exception {

		/*http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/empresa").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empresa/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/empresa").hasAnyRole("USER", "ADMIN")
		.antMatchers("/api/empresa/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/empleado").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empleado/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/empleado/tipoEmpleados").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/empleado").hasAnyRole("USER", "ADMIN")
		.antMatchers("/api/empleado/**").hasAnyRole("USER", "ADMIN")*/
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/empresa").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empresa/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/empresa").permitAll()
		.antMatchers("/api/empresa/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empleado").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empleado/{id}").permitAll()
		.antMatchers(HttpMethod.GET, "/api/empleado/tipoEmpleados").permitAll()
		.antMatchers(HttpMethod.POST, "/api/empleado").permitAll()
		.antMatchers("/api/empleado/**").permitAll()
		
		/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")

		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")

		.antMatchers("/api/clientes/**").hasRole("ADMIN")*/

		.anyRequest().authenticated()

		.and().cors().configurationSource(corsConfigurationSource());

		}


	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
	