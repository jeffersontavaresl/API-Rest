package br.com.rest.APIRest.dto;

import java.time.LocalDateTime;

import br.com.rest.APIRest.model.Resposta;

//CLASSE QUE SERÁ RESPONSÁVEL EM LEVAR AS INFORMAÇÕES DO BANCO DE DADOS PARA A VIEW
//POIS NÃO É BOA PRÁTICA LEVAR AS INFORMAÇÕES DA ENTIDADE PARA FRENTE 

public class RespostaDto {

	// SÓ PRECISO INFORMAR OS ATRIBUTOS QUAIS O USUÁRIO PRECISA RECEBER.
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	
	// O CONSTRUTOR TEM QUE RECEBER A ENTIDADE E RECEBER OS PARAMETROS DA MESMA (ENTIDADE) COM O GET.
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	
}
