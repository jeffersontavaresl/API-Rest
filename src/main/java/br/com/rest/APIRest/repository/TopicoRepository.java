package br.com.rest.APIRest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.APIRest.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	/** MÉTODO PARA BUSCAR O CURSO PELO NOME
	 * 
	 * @param nomeCurso
	 * @param paginacao 
	 * @return
	 */
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

}
