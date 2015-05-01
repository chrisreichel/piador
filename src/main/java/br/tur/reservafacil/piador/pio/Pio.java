package br.tur.reservafacil.piador.pio;

/**
 * Created by enrique on 4/30/15.
 */
public class Pio {

    private String username;
    private String conteudo;

    public Pio(String username, String conteudo) {
		super();
		this.username = username;
		this.setConteudo(conteudo);
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

    @Override public String toString() {
	return "Pio{" +
			"username='" + username + '\'' +
			", conteudo='" + conteudo + '\'' +
			'}';
    }
}
