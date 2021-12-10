package br.com.rest.APIRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.APIRest.config.security.TokenService;
import br.com.rest.APIRest.dto.TokenDto;
import br.com.rest.APIRest.form.LoginForm;

@RestController // ANOTAÇÃO PARA INFORMAR QUE ESSA CLASSE É UM RESTCONTROLLER
@RequestMapping("/auth") // ANOTAÇÃO PARA INFORMAR O CAMINHO PARA ACESSA-LA
public class AutenticacaoController {

	// INJEÇÃO DO REPOSITÓRIO PARA USAR OS MÉTODOS DO JPAREPOSITORY
	// ANOTAÇÃO INFORMA QUE O SPRING IRÁ GERENCIAR A INJEÇÃO
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	/** método para realizar a autenticação do usuário
	 * @param form
	 * @return
	 */
	@PostMapping // anotação para informar o protocolo http para acessar esse método
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter(); // irá receber os dados passados pelo usuário (email e senha)
		
		try {
			Authentication authenticate = authenticationManager.authenticate(dadosLogin); // irá pegar os dados que foram passados o dados
			String token = tokenService.gerarToken(authenticate); // vai gerar um token para o usuário com a biblioteca jwst 
			return ResponseEntity.ok(new TokenDto(token, "Bearer")); // vai criar um objeto token para ser passado para frente.
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
