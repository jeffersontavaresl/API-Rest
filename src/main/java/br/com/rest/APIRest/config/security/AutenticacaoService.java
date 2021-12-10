package br.com.rest.APIRest.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rest.APIRest.model.Usuario;
import br.com.rest.APIRest.repository.UsuarioRepository;

@Service // ANOTAÇÃO PARA INFORMA QUE É UMA CLASSE DE SERVIÇO 
public class AutenticacaoService implements UserDetailsService{

	// INJEÇÃO DO REPOSITÓRIO PARA USAR OS MÉTODOS DO JPAREPOSITORY
	// ANOTAÇÃO INFORMA QUE O SPRING IRÁ GERENCIAR A INJEÇÃO
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/** método para verificar se o usuário existe através do email
	 *  
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username); // irá procurar o usuario no banco através do email
		if(usuario.isPresent()) {
			return usuario.get(); // se o usuário existe irá retornar o usuário
		}
		
		throw new UsernameNotFoundException("Dados Inválidos");
	}
	
	
}
