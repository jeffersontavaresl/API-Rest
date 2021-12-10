package br.com.rest.APIRest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//ANOTAÇÃO PARA INDICAR QUE A CLASSE É UMA ENTIDADE E QUE IRÁ SER CRIADA NO BANCO DE DADOS.
// A ENTIDADE IMPLEMENTAR USERDETAIS PARA USAR O SPRING SECURITY E TER A DIVISÃO DE PERFIS
@Entity
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	// O ID PARA INFORMAR A PRIMARY KEY // GENERATEDVALEU PARA INFORMAR QUE O BANCO IRÁ GERAR O VALOR DE FORMA AUTOMÁTICA
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	/** Anotação para informa que existe uma relação do usuário e o perfil . 
	 *  uma relação de vários usuários que podem ter vários perfis.
	 *  EAGER informa que sempre que o usuário for carregador, as informações do perfil também serão
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	// MÉTODO QUE É SOBRECARREGADO PELO USERDETAILS PARA INFORMAR AS PRIORIDADES DO USUÁRIO
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	// MÉTODO QUE É SOBRECARREGADO PELO USERDETAILS PARA INFORMAR A SENHA
	@Override
	public String getPassword() {
		return this.senha;
	}

	// MÉTODO QUE É SOBRECARREGADO PELO USERDETAILS PARA INFORMAR O USUÁRIO
	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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
		Usuario other = (Usuario) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
