package br.tur.reservafacil.piador.pio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.net.PasswordAuthentication;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

@Repository
public class UsuarioRepositoryDefaultImpl implements UsuarioRepository {

    private static Map<String, List<String>> seguidoresRepos = new HashMap<>();
    private static Set<Usuario>              usuariosRepos   = new HashSet<>();

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert seguirInsertHandler;

    public UsuarioRepositoryDefaultImpl() {}

    @Autowired
    public UsuarioRepositoryDefaultImpl(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	seguirInsertHandler = new SimpleJdbcInsert(jdbcTemplate).withTableName("seguidores").usingGeneratedKeyColumns("id");
    }

    @Override
    public void addSeguidor(String username, String seguidor) {
	final Optional<Usuario> usuario = findUsuarioByLogin(username);
	final Optional<Usuario> quemSeraSeguido = findUsuarioByLogin(seguidor);
	if(usuario.isPresent() && quemSeraSeguido.isPresent()){
	    final SqlParameterSource namedParameters = new MapSqlParameterSource("usuario_id", usuario.get().getId())
			    .addValue("seguidor_id", quemSeraSeguido.get().getId());
	    seguirInsertHandler.execute(namedParameters);
	}
	else {
	    throw new IllegalArgumentException("Nao foi possivel establelecer uma recacao entre: " + username + " e " + seguidor);
	}
    }

    @Override
    public List<String> findSeguidoresByUsername(String username) {
	final String sql = "";
    }

    @Override
    public void insert(Usuario usuario) {
	checkNotNull(usuario);

	SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("usuario")
			.usingGeneratedKeyColumns("id");

	final SqlParameterSource namedParameters = new MapSqlParameterSource("nome", usuario.getNome())
			.addValue("sobrenome", usuario.getSobrenome())
			.addValue("email", usuario.getEmail())
			.addValue("username", usuario.getAuthentication().getUserName())
			.addValue("password", new String((usuario.getAuthentication().getPassword())));

	usuario.setId(simpleJdbcInsert.executeAndReturnKey(namedParameters).intValue());
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
	String[] args = {passwordAuthentication.getUserName(), new String(passwordAuthentication.getPassword())};
	final Usuario usuario =
			jdbcTemplate.queryForObject("select * from usuario where username = ? and password = ?", args,
						    new UsuarioRowMapper());

	return Optional.ofNullable(usuario);
    }

    @Override public Optional<Usuario> findUsuarioByLogin(String userName) {
	final Usuario usuario =
			jdbcTemplate.queryForObject("select * from usuario where username = ?",
						    new UsuarioRowMapper(), userName);

	return Optional.ofNullable(usuario);
    }

    @Override public Collection<Usuario> findAllUsuarios() {
        final String sql = "select * from usuario";
	return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }
}