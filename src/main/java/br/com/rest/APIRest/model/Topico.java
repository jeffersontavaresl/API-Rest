package br.com.rest.APIRest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//ANOTAÇÃO PARA INDICAR QUE A CLASSE É UMA ENTIDADE E QUE IRÁ SER CRIADA NO BANCO DE DADOS.
@Entity
public class Topico {

	// O ID PARA INFORMAR A PRIMARY KEY // GENERATEDVALEU PARA INFORMAR QUE O BANCO IRÁ GERAR O VALOR DE FORMA AUTOMÁTICA
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	// ANOTAÇÃO PARA INFORMAR AO SPRING QUE NO BANCO DE DADOS O VALOR QUE IRÁ SER GERADOR É A STRING DO ENUM.
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	
	/** Anotação para informa que existe uma relação do tópico e o usuário. 
	 *  uma relação de vários tópicos que podem ter um autor.
	 */
	@ManyToOne
	private Usuario autor;
	
	/** Anotação para informa que existe uma relação do tópico e o curso. 
	 *  uma relação de vários tópicos que podem ter um curso.
	 */
	@ManyToOne
	private Curso curso;
	
	/** Anotação para informa que existe uma relação da resposta e o tópico. 
	 *  uma relação de um tópico para várias respostas.
	 *  o mapeamento é informado pelo nome do atributo na classe resposta.
	 */
	@OneToMany(mappedBy = "topico")
	private List<Resposta> respostas = new ArrayList<>();

	public Topico() {
	}
		
	public Topico(String titulo, String mensagem, Curso curso) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

}
