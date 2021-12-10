package br.com.rest.APIRest.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

//A CLASSE QUE IRÁ RECEBER OS DADOS INFORMADOS PELO USUÁRIO NOS MÉTODOS POST E FAZ A VALIDAÇÃO DAS INFORMAÇÕES

public class LoginForm {

	private String email;
	private String senha;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	
	/** MÉTODO PARA RECEBER AS INFORMAÇÕES DE LOGIN E IRÁ CONVERTER
	 *  NO TOKEN PARA QUE USUÁRIO POSSA NAVEGAR PELO SISTEMA
	 * @return
	 */
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
	
}
