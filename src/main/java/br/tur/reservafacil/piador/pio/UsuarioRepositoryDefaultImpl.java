package br.tur.reservafacil.piador.pio;

import java.net.PasswordAuthentication;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

public class UsuarioRepositoryDefaultImpl implements UsuarioRepository {

    private static Map<String, List<String>> seguidoresRepos = new HashMap<>();
    private static Set<Usuario>              usuariosRepos   = new HashSet<>();

    public UsuarioRepositoryDefaultImpl() {
	initSeguidores();
	initUsuarios();
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

    @Override
    public void save(Usuario usuario) {
	checkNotNull(usuario);
	usuariosRepos.add(usuario);
    }

    @Override
    public Optional<Usuario> findUsuarioByPasswordAuthentication(PasswordAuthentication passwordAuthentication) {
	return usuariosRepos.stream()
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

    private void initUsuarios() {
	usuariosPadrao().stream()
			.map(this::createUsuario)
			.forEach(this::save);
    }

    private Usuario createUsuario(String template) {
	PasswordAuthentication passwordAuthentication = new PasswordAuthentication(template, "123".toCharArray());

	String usuarioLimpo = template.replaceAll("@", "");
	Profile profile = new Profile();
	profile.setEmail(usuarioLimpo+"@gmail.com");
	profile.setNome(usuarioLimpo.split("[.]")[0]);
	profile.setSobrenome(usuarioLimpo.split("[.]")[1]);

	return new Usuario(passwordAuthentication, new ArrayList<>(), new ArrayList<>(), profile);
    }

    private void initSeguidores() {
	usuariosPadrao().stream()
			.forEach(user -> {
			    seguidoresRepos.put(user, usuariosPadrao().stream()
			    					.filter(seg -> !seg.equals(user))
								.collect(toList()));
			});
    }

    private List<String> usuariosPadrao() {
	return Arrays.asList("@camilla.navarro", "@paula.dias", "@danielle.miranda", "@aline.gallo");
    }
}