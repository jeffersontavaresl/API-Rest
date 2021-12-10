package br.com.rest.APIRest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.rest.APIRest.model.Topico;
import br.com.rest.APIRest.repository.TopicoRepository;

//A CLASSE QUE IRÁ RECEBER OS DADOS INFORMADOS PELO USUÁRIO NOS MÉTODOS POST E FAZ A VALIDAÇÃO DAS INFORMAÇÕES

public class AtualizacaoTopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;

	
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

	/** MÉTODO PARA CONVERTER AS INFORMAÇÕES PASSADAS PELO USUÁRIO EM UM OBJETO DO TIPO TOPIC.
	 *  PARA ATUALIZAR O USUÁRIO IRÁ PASSAR O ID DO TÓPICO
	 *  O TOPICO SERÁ VERIFICADO NO BANCO E CASO EXISTE, IRÁ RETORNA UM OBJETO DO TIPO TOPICO 
	 *  COM AS NOVAS INFORMAÇÕES
	 * @param id
	 * @param topicoRepository
	 * @return
	 */
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
	
	
}
