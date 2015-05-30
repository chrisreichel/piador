package br.tur.reservafacil.piador.pio;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
	Usuario usuario = new Usuario(resultSet.getString("username"), resultSet.getString("password"));
	usuario.setId(resultSet.getInt("id"));
	usuario.setEmail(resultSet.getString("email"));
	usuario.setNome(resultSet.getString("nome"));
	usuario.setSobrenome(resultSet.getString("sobrenome"));
	return usuario;
    }
}
