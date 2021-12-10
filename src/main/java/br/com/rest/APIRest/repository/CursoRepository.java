package br.com.rest.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.APIRest.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

	/** MÉTODO PARA ENCONTRAR UM CURSO PELO NORME
	 * 
	 * @param nomeCurso
	 * @return -> objeto Curso
	 */
	Curso findByNome(String nomeCurso);

}
