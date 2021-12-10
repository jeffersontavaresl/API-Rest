package br.com.rest.APIRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.APIRest.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	/** MÉTODO PARA PROCURAR UM USUARIO POR EMAIL
	 * 
	 * @param email
	 * @return -> retorna o objeto usuario 
	 */
	Optional<Usuario> findByEmail(String email);
}
