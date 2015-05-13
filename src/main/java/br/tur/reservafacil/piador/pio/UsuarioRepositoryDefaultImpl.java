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
    public void insert(Usuario usuario) {
	checkNotNull(usuario);
        if(usuariosRepos.contains(usuario)){
	    throw new IllegalArgumentException("Usuario ja existe");
        }
	usuariosRepos.add(usuario);
    }

    @Override public void update(Usuario usuario) {
        checkNotNull(usuario);
        if(!usuariosRepos.contains(usuario)){
            throw new IllegalArgumentException("Usuario nao existe");
        }
        usuariosRepos.add(usuario);
    }

    @Override
    public Optional<Usuario> findUsuarioByPasswordAuthentication(PasswordAuthentication passwordAuthentication) {
	return usuariosRepos.stream()
			.filter(user -> comparePasswordAuthentication(user.getAuthentication(), passwordAuthentication))
			.findFirst();
    }

    @Override public Optional<Usuario> findUsuarioByLogin(String userName) {
        return usuariosRepos.stream()
                        .filter(user -> (user.getAuthentication().getUserName().equalsIgnoreCase(userName))).findFirst();
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
			.forEach(this::insert);
    }

    private Usuario createUsuario(String user) {
        final String template = user.replace("@", "");
	PasswordAuthentication passwordAuthentication = new PasswordAuthentication(template, "123".toCharArray());
	Usuario u = new Usuario(passwordAuthentication, new ArrayList<>(), new ArrayList<>());
        u.setEmail(template + "@gmail.com");
        u.setNome(template.split("[.]")[0]);
        u.setSobrenome(template.split("[.]")[1]);
        return u;
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