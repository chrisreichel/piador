package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Usuario {

    private PasswordAuthentication authentication;
    private List<Pio>              pios;
    private List<Usuario>          followers;
    private String                 nome;
    private String                 sobrenome;
    private String                 email;

    public Usuario(String login, String senha) {
        checkNotNull(login);
        checkNotNull(senha);
        this.authentication = new PasswordAuthentication(login.replace("@",""), senha.toCharArray());
    }

    public Usuario(String login, String senha, String email) {
        checkNotNull(login);
        checkNotNull(senha);
        checkNotNull(email);
        this.authentication = new PasswordAuthentication(login.replace("@",""), senha.toCharArray());
        this.email = email;
    }

    public Usuario(PasswordAuthentication authentication) {
        this.authentication = authentication;
    }

    public Usuario(PasswordAuthentication authentication, List<Pio> pios, List<Usuario> followers) {
	this.authentication = authentication;
	this.pios = pios;
	this.followers = followers;
    }

    public PasswordAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(PasswordAuthentication authentication) {
        this.authentication = authentication;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Usuario> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Usuario> followers) {
        this.followers = followers;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pio> getPios() {
        return pios;
    }

    public void setPios(List<Pio> pios) {
        this.pios = pios;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override public String toString() {
        return "Usuario{" +
                        "authentication=" + authentication +
                        ", pios=" + pios +
                        ", followers=" + followers +
                        ", nome='" + nome + '\'' +
                        ", sobrenome='" + sobrenome + '\'' +
                        ", email='" + email + '\'' +
                        '}';
    }

    @Override public boolean equals(Object o) {
        Usuario usuario = (Usuario)o;

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass() || authentication.getUserName() == null || usuario.authentication == null)
            return false;

        return authentication.getUserName().equals(usuario.authentication.getUserName());

    }

    @Override public int hashCode() {
        return authentication.hashCode();
    }
}