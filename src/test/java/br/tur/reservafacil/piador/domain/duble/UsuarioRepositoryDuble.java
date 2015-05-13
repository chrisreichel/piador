package br.tur.reservafacil.piador.domain.duble;

import br.tur.reservafacil.piador.pio.Usuario;
import br.tur.reservafacil.piador.pio.UsuarioRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.net.PasswordAuthentication;
import java.util.*;

/**
 * Created by christian on 13/05/15.
 */
public class UsuarioRepositoryDuble
		implements UsuarioRepository {

    public final Set<Usuario> db = new HashSet<>();

    @Override public void addSeguidor(String username, String seguidor) {
	throw new NotImplementedException("no yet");
    }

    @Override public List<String> findSeguidoresByUsername(String username) {
	throw new NotImplementedException("no yet");
    }

    @Override public void insert(Usuario usuario) {
        db.add(usuario);
    }

    @Override public void update(Usuario usuario) {
	throw new NotImplementedException("no yet");
    }

    @Override
    public Optional<Usuario> findUsuarioByPasswordAuthentication(PasswordAuthentication passwordAuthentication) {
        return db.stream()
                        .filter(user -> comparePasswordAuthentication(user.getAuthentication(), passwordAuthentication))
                        .findFirst();
    }

    private boolean comparePasswordAuthentication(PasswordAuthentication authA, PasswordAuthentication authB) {
        if (authA.getPassword() == null || authB.getPassword() == null) {
            return false;
        }

        if (authA.getUserName() == null || authB.getUserName() == null) {
            return false;
        }
        return authA.getUserName().equals(authB.getUserName()) && Arrays.equals(authA.getPassword(), authB.getPassword());
    }

    @Override public Optional<Usuario> findUsuarioByLogin(String userName) {
        return db.stream().filter(user -> (user.getAuthentication().getUserName().equalsIgnoreCase(userName))).findFirst();
    }
}
