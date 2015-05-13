package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    void addSeguidor(String username, String seguidor);

    List<String> findSeguidoresByUsername(String username);

    void insert(Usuario usuario);

    void update(Usuario usuario);

    Optional<Usuario> findUsuarioByPasswordAuthentication(PasswordAuthentication passwordAuthentication);

    Optional<Usuario> findUsuarioByLogin(String userName);

    Collection<Usuario> findAllUsuarios();
}
