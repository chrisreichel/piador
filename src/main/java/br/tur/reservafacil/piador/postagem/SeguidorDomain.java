package br.tur.reservafacil.piador.postagem;

import java.util.List;

public interface SeguidorDomain {

    void seguir(String username, String seguidor);

    List<String> listarSeguidores(String username);
}
