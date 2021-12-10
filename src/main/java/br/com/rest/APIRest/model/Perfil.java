package br.com.rest.APIRest.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

//ANOTAÇÃO PARA INDICAR QUE A CLASSE É UMA ENTIDADE E QUE IRÁ SER CRIADA NO BANCO DE DADOS.
//A CLASSE É IMPLEMANTADA COM GRANTEDAUTHORITY PARA INFORMAR QUAIS SERÃO OS PRIVILÉGIOS DO USUÁRIO
@Entity
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	// O ID PARA INFORMAR A PRIMARY KEY // GENERATEDVALEU PARA INFORMAR QUE O BANCO IRÁ GERAR O VALOR DE FORMA AUTOMÁTICA
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/** método para retornar o tipo de usuário (moderador, usuário comum e etc..)
	 * 
	 */
	@Override
	public String getAuthority() {
		return nome;
	}
	
	
}
