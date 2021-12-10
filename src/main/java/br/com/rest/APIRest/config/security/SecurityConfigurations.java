package br.com.rest.APIRest.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rest.APIRest.repository.UsuarioRepository;

@EnableWebSecurity // ANOTAÇÃO PARA ATIVAR AS CONFIGURAÇÕES DE SEGURANÇA
@Configuration // ANOTAÇÃO PARA INFORMAR AO SPRING QUE É UMA CLASSE DE CONFIGURAÇÃO
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

	// INJEÇÃO DO REPOSITÓRIO PARA USAR OS MÉTODOS DO JPAREPOSITORY
	// ANOTAÇÃO INFORMA QUE O SPRING IRÁ GERENCIAR A INJEÇÃO
	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//CONFIGURAÇÃO DE AUTENTICAÇÃO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//CONFIGURAÇÕES DE AUTORIZAÇÕES
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll()
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("MODERADOR")
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}

	// CONFIGURAÇÕES DE RECURSOS ESTÁTICOS(JS, CSS, IMAGENS)
	@Override
	public void configure(WebSecurity web) throws Exception {
	    
	}

}
