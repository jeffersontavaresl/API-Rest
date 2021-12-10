package br.com.rest.APIRest.dto;

//CLASSE QUE SERÁ RESPONSÁVEL EM LEVAR AS INFORMAÇÕES DO BANCO DE DADOS PARA A VIEW
//POIS NÃO É BOA PRÁTICA LEVAR AS INFORMAÇÕES DA ENTIDADE PARA FRENTE

public class TokenDto {

	// SÓ PRECISO INFORMAR OS ATRIBUTOS QUAIS O USUÁRIO PRECISA RECEBER.
	private String token;
	private String tipo;
	
	// O CONSTRUTOR TEM QUE RECEBER A ENTIDADE E RECEBER OS PARAMETROS DA MESMA (ENTIDADE) COM O GET.
	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
