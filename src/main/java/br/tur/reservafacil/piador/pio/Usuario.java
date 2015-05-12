package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.List;

public class Usuario {

    private PasswordAuthentication authentication;
    private List<Pio>              pios;
    private List<Usuario>          followers;
    private Profile                profile;

    public Usuario(PasswordAuthentication authentication, List<Pio> pios, List<Usuario> followers, Profile profile) {
	this.authentication = authentication;
	this.pios = pios;
	this.followers = followers;
	this.profile = profile;
    }

    public List<Pio> getPios() {
	return pios;
    }

    public Profile getProfile() {
	return profile;
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
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