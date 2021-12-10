package br.com.rest.APIRest.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.rest.APIRest.model.Topico;

// CLASSE QUE SERÁ RESPONSÁVEL EM LEVAR AS INFORMAÇÕES DO BANCO DE DADOS PARA A VIEW
// POIS NÃO É BOA PRÁTICA LEVAR AS INFORMAÇÕES DA ENTIDADE PARA FRENTE 

public class TopicoDto {

	// SÓ PRECISO INFORMAR OS ATRIBUTOS QUAIS O USUÁRIO PRECISA RECEBER.
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	
	// O CONSTRUTOR TEM QUE RECEBER A ENTIDADE E RECEBER OS PARAMETROS DA MESMA (ENTIDADE) COM O GET.
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	// MÉTODO RESPONSÁVEL PARA CONVERTER AS INFORMAÇÕES PARA QUE POSSAM SER LEVADAS AO USUÁRIO.
	public static Page<TopicoDto> converter(Page<Topico> topicos){
		return topicos.map(TopicoDto::new);
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
