package br.tur.reservafacil.piador.pio;

import java.util.List;

public class Usuario {

    private String        username;
    private List<Pio>     pios;
    private List<Usuario> followers;

    public Usuario(String username, List<Pio> pios, List<Usuario> followers) {
	this.username = username;
	this.pios = pios;
	this.followers = followers;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public List<Pio> getPios() {
	return pios;
    }

    public void setPios(List<Pio> pios) {
	this.pios = pios;
    }

    public List<Usuario> getFollowers() {
	return followers;
    }

    public void setFollowers(List<Usuario> followers) {
	this.followers = followers;
    }

}