package br.tur.reservafacil.piador.pio;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class UsuarioRepositoryDefaultImpl implements UsuarioRepository {

    private static Map<String, List<String>> seguidoresRepos = new HashMap<>();

    public UsuarioRepositoryDefaultImpl() {
	initSeguidores();
    }

    @Override
    public void addSeguidor(String username, String seguidor) {
	if (!seguidoresRepos.containsKey(username)) {
	    seguidoresRepos.put(username, new ArrayList<>());
	}
	seguidoresRepos.get(username).add(seguidor);
    }

    @Override
    public List<String> findSeguidoresByUsername(String username) {
	return seguidoresRepos.get(username);
    }

    public void initSeguidores() {
	usuariosPadrao().stream()
			.forEach(user -> {
			    seguidoresRepos.put(user, usuariosPadrao().stream()
			    					.filter(seg -> !seg.equals(user))
								.collect(toList()));
			});
    }

    private static List<String> usuariosPadrao() {
	return Arrays.asList("@camilla", "@paula", "@danielle", "@aline");
    }
}