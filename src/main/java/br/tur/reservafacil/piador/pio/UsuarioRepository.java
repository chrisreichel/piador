package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    void addSeguidor(String username, String seguidor);

    List<String> findSeguidoresByUsername(String username);

    void save(Usuario usuario);

    Optional<Usuario> findUsuarioByPasswordAuthentication(PasswordAuthentication passwordAuthentication);
}
