package br.tur.reservafacil.piador.postagem;

import br.tur.reservafacil.piador.pio.Pio;

import java.util.List;

public interface PostagemDomain {

    void fazPostagem(Pio pio);

    List<Pio> listarPosts(String username);
}