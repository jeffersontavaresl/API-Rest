package br.com.rest.APIRest.form;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.rest.APIRest.model.Curso;
import br.com.rest.APIRest.model.Topico;
import br.com.rest.APIRest.repository.CursoRepository;

// A CLASSE QUE IRÁ RECEBER OS DADOS INFORMADOS PELO USUÁRIO NOS MÉTODOS POST E FAZ A VALIDAÇÃO DAS INFORMAÇÕES

public class TopicoForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	/** MÉTODO NECESSÁRIO PARA CONVERTER AS INFORMAÇÕES PASSADAS PELO USUÁRIO
	 *  EM UMA ENTIDADE PARA QUE POSSAR SER SALVO NO BANCO
	 *  O TÓPICO PRECISA RECEBER UM CURSO EM QUE SERÁ INSERIDO, ONDE SERÁ CHAMADO O MÉTODO DE
	 *  BUSCAR UM CURSO NO BANCO E SERÁ RETORNADO UM OBJETO DO TIPO TOPICO COM 
	 *  AS INFORMAÇÕES PASSADAS PELO USUÁRIO
	 *  
	 * @param cursoRepository
	 * @return
	 */
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
}
