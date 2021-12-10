package br.com.rest.APIRest.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.rest.APIRest.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	// valor gerado para informar quanto tempo o token ficará válido
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	// senha aleatória para realizar a configuração do token
	@Value("${forum.jwt.secret}")
	private String secret;
	
	/** método para gerar o token para que usuário possa realizar as operações no sistema
	 * 
	 * @param authentication
	 * @return
	 */
	public String gerarToken(Authentication authentication) {
		// objeto para pegar o usuário ques está logado
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		// criação do token com o tempo de expiração
		return Jwts.builder()
				.setIssuer("API do Forum da Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	/** método para verificar se o token é válido
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/** método para pegar o id do usuário pelo token
	 *  
	 * @param token
	 * @return
	 */
	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
}
