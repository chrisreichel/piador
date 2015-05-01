package br.tur.reservafacil.piador.postagem;

import br.tur.reservafacil.piador.pio.Pio;

import java.util.List;

/**
 * Created by enrique on 4/30/15.
 */
public interface PostagemDomain {

    void fazPostagem(Pio pio);

    List<Pio> listarPosts(String username);
}
