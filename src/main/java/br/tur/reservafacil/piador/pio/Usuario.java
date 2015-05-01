package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.List;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.Profile;

/**
 * Created by enrique on 4/30/15.
 */
public class Usuario {

	private PasswordAuthentication authentication;
	private List<Pio> conteudo;
	private List<Usuario> followers;
	private Profile profile;

	public Usuario(PasswordAuthentication authentication, List<Pio> conteudo, List<Usuario> followers, Profile profile) {
		this.authentication = authentication;
		this.conteudo = conteudo;
		this.followers = followers;
		this.profile = profile;
	}
	

	public List<Pio> getConteudo() {
		return conteudo;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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

	public PasswordAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(PasswordAuthentication authentication) {
		this.authentication = authentication;
	}
	
}
