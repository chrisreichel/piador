package br.tur.reservafacil.piador.pio;

import java.util.List;

/**
 * Created by enrique on 4/30/15.
 */
public class Usuario {

	private String username;
	private List<Pio> conteudo;
	private List<Usuario> followers;

	public Usuario(String username, List<Pio> conteudo, List<Usuario> followers) {
		this.username = username;
		this.conteudo = conteudo;
		this.followers = followers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Pio> getConteudo() {
		return conteudo;
	}

	public void setConteudo(List<Pio> conteudo) {
		this.conteudo = conteudo;
	}

	public List<Usuario> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Usuario> followers) {
		this.followers = followers;
	}

}
