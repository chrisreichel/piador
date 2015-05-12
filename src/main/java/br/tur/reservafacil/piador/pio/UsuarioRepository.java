package br.tur.reservafacil.piador.pio;

import java.util.List;

public interface UsuarioRepository {

    void addSeguidor(String username, String seguidor);

    List<String> findSeguidoresByUsername(String username);
}
