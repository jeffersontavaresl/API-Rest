package br.com.rest.APIRest.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.rest.APIRest.model.Usuario;
import br.com.rest.APIRest.repository.UsuarioRepository;
// CLASSE PARA AUTENTICAR O USUÁRIO NO SISTEMA PELO TOKEN
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	// essa classe não recebe injeção de dependencias
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	// por isso criamos um construtor para ela que recebe o usuariorepository e o tokenservice
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	/** método para fazer o filtro do sistema pelo token do usuário
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// string para salvar o token recuperado no http
		String token = recurarToken(request);
		// verifica se o token é válido
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			// caso o token seja válido o usuário é liberado para usar o sistema
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
	}

	/** método para autenticar o usuário pelo token
	 * 
	 * @param token
	 */
	private void autenticarCliente(String token) {
		// pega o id do usuario que é passado por baixo dos panos pelo token
		Long idUsuario = tokenService.getIdUsuario(token);
		
		// com o id do usuário é criado um objeto do tipo usuário
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		// cria uma atenticacao única para esse usuário com usuario e as permissões que o usuario possui
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/** método para recuperar o token gerado no header do http
	 * 
	 * @param request
	 * @return
	 */
	private String recurarToken(HttpServletRequest request) {
		// string para salvar o token recuperado no header na área de authorization
		String token = request.getHeader("Authorization");
		
		// caso o token não seja válido, retorna nulo
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		// retorna o token caso seja válido
		return token.substring(7, token.length());
	}

}
