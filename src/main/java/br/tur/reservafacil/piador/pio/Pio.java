package br.tur.reservafacil.piador.pio;

import java.time.LocalDateTime;

public class Pio {

    private String    conteudo;
    private String    username;
    private LocalDateTime dataCriacao;

    public Pio(String username, String conteudo) {
	super();
	this.username = username;
	this.setConteudo(conteudo);
	this.dataCriacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
	return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
	this.dataCriacao = dataCriacao;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getConteudo() {
	return conteudo;
    }

    public void setConteudo(String conteudo) {
	if (conteudo != null && conteudo.length() > 140) {
	    this.conteudo = conteudo.substring(0, 140);
	} else {
	    this.conteudo = conteudo;
	}
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	Pio pio = (Pio)o;

	if (conteudo != null ? !conteudo.equals(pio.conteudo) : pio.conteudo != null)
	    return false;
	if (username != null ? !username.equals(pio.username) : pio.username != null)
	    return false;
	return !(dataCriacao != null ? !dataCriacao.equals(pio.dataCriacao) : pio.dataCriacao != null);

    }

    @Override
    public int hashCode() {
	int result = conteudo != null ? conteudo.hashCode() : 0;
	result = 31 * result + (username != null ? username.hashCode() : 0);
	result = 31 * result + (dataCriacao != null ? dataCriacao.hashCode() : 0);
	return result;
    }

    @Override public String toString() {
	return "Pio{" +
			"conteudo='" + conteudo + '\'' +
			", username='" + username + '\'' +
			", dataCriacao=" + dataCriacao +
			'}';
    }
}