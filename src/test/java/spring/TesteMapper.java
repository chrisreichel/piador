package spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteMapper implements RowMapper<Teste>{

    @Override public Teste mapRow(ResultSet rs, int rowNum)
		    throws SQLException {
        Teste teste = new Teste();
        teste.setId(rs.getInt("id"));
        teste.setName(rs.getString("name"));
	return teste;
    }
}
