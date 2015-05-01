package br.tur.reservafacil.piador.postagem;

import java.util.List;

/**
 * Created by enrique on 4/30/15.
 */
public interface SeguidorDomain {

    void seguir(String username, String seguidor);

    List<String> listarSeguidores(String username);
}
