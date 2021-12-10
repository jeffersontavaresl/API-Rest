package br.com.rest.APIRest.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rest.APIRest.model.StatusTopico;
import br.com.rest.APIRest.model.Topico;

//CLASSE QUE SERÁ RESPONSÁVEL EM LEVAR AS INFORMAÇÕES DO BANCO DE DADOS PARA A VIEW
//POIS NÃO É BOA PRÁTICA LEVAR AS INFORMAÇÕES DA ENTIDADE PARA FRENTE 

public class DetalhesDoTopicoDto {
	
	// SÓ PRECISO INFORMAR OS ATRIBUTOS QUAIS O USUÁRIO PRECISA RECEBER.
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	
	// O CONSTRUTOR TEM QUE RECEBER A ENTIDADE E RECEBER OS PARAMETROS DA MESMA (ENTIDADE) COM O GET.
	public DetalhesDoTopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
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

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
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

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public void setRespostas(List<RespostaDto> respostas) {
		this.respostas = respostas;
	}
	
}
