package br.com.rest.APIRest.dto;

//CLASSE QUE SERÁ RESPONSÁVEL EM LEVAR AS INFORMAÇÕES DO BANCO DE DADOS PARA A VIEW
//POIS NÃO É BOA PRÁTICA LEVAR AS INFORMAÇÕES DA ENTIDADE PARA FRENTE 

public class ErroDeFormularioDto {
	
	// SÓ PRECISO INFORMAR OS ATRIBUTOS QUAIS O USUÁRIO PRECISA RECEBER.
	private String campo;
	private String erro;
	
	// O CONSTRUTOR TEM QUE RECEBER A ENTIDADE E RECEBER OS PARAMETROS DA MESMA (ENTIDADE) COM O GET.
	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}
