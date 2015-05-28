package spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml"})
@ActiveProfiles("test")
public class SampleTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Simples busca
     * @throws Exception
     */
    @Test
    public void testeSelect() throws Exception{
        //Given
        final String sql = "select * from teste";
        //When
        final List<Teste> lista = jdbcTemplate.query(sql, new TesteMapper());
        //Then
        assertEquals(2, lista.size());
    }

    /**
     * Busca com parametros
     * @throws Exception
     */
    @Test
    public void testeBuscaComParametro() throws Exception{
        //Given
        final String sql = "select * from teste where id = ?";
        //When
        final Teste actual = jdbcTemplate.queryForObject(sql, new TesteMapper(), 2);
        //Then
        assertEquals("world", actual.getName());
    }

    /**
     * Busca com named parameters
     * @throws Exception
     */
    @Test
    public void testeBuscaComNamedParams() throws Exception{
        //Given
        final String sql = "select * from teste where id = :id";
        //When
        final SqlParameterSource namedParameters = new MapSqlParameterSource("id", 2);
        final Teste actual = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TesteMapper());
        //Then
        assertEquals("world", actual.getName());
    }

    /**
     * Busca com named parameters, mas sem construir um map dos parametros (usando um objeto como referencia)
     * @throws Exception
     */
    @Test
    public void testeBuscaComNamedParamUsandoBean() throws Exception{
        //Given
        final String sql = "select * from teste where id = :id";
        //When
        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(new Teste(2));
        final Teste actual = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TesteMapper());
        //Then
        assertEquals("world", actual.getName());
    }

    /**
     *  Busca com named parameters, mas sem construir um map dos parametros (usando um objeto como referencia),
     *  e para o mapeamento dos atributos do Bean <-> colunas faz automaticamente
     * @throws Exception
     */
    @Test
    public void testeBuscaComNamedParamUsandoBeanSemMapper() throws Exception{
        //Given
        final String sql = "select * from teste where id = :id";
        //When
        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(new Teste(2));
        final Teste actual = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<Teste>(Teste.class));
        //Then
        assertEquals("world", actual.getName());
    }

    /**
     * Faz a inserção usando um HashMap com chaves e valor (chave representa a coluna)
     * @throws Exception
     */
    @Test
    public void testeDeSimpleInsertViaMap() throws Exception{
        //Given
        final String name = "Mario";
        final SqlParameterSource namedParameters = new MapSqlParameterSource("name", name);
        //When
        final Number newId = simpleJdbcInsert.executeAndReturnKey(namedParameters);
        //Then
        assertTrue(newId.intValue() > 2);
        final Teste actual = jdbcTemplate.queryForObject("select * from teste where id = ?", new TesteMapper(), newId);
        assertEquals(name, actual.getName());
    }

    /**
     * Faz a inserção usando o próprio objeto (as colunas da tabela e os nomes dos atributos devem ser os mesmos).
     * @throws Exception
     */
    @Test
    public void testeDeSimpleInsertViaBean() throws Exception{
        //Given
        final String name = "Joana D'Arc";
        final Teste novoObjeto = new Teste(name);
        //When
        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(novoObjeto);
        final Number newId = simpleJdbcInsert.executeAndReturnKey(namedParameters);
        //Then
        assertTrue(newId.intValue() > 2);
        final Teste actual = jdbcTemplate.queryForObject("select * from teste where id = ?", new TesteMapper(), newId);
        assertEquals(name, actual.getName());
    }


    /**
     * Batch update
     * @throws Exception
     */
    @Test
    public void testeBatchUpdate() throws Exception{
        //Given
        final List<Teste> valores = new ArrayList<>();
        valores.add(new Teste(1, "VALOR_1"));
        valores.add(new Teste(2, "VALOR_2"));
        //When
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(valores.toArray());
        final int[] updateCounts = namedParameterJdbcTemplate.batchUpdate(
                        "update teste set name = :name where id = :id",
                        batch);
        //Then
        final List<Teste> testeList = jdbcTemplate.query("select * from teste", new TesteMapper());
        assertEquals(2, updateCounts.length);
        assertThat(testeList).contains(valores.get(0), valores.get(1));
    }

    @Test
    public void testeBatchUpdateFeio() throws Exception{
        //Given
        final List<Teste> valores = new ArrayList<>();
        valores.add(new Teste(1, "VALOR_1"));
        valores.add(new Teste(2, "VALOR_2"));
        final List<Object[]> batch = new ArrayList<>();
        for (Teste t : valores) {
            final Object[] values = new Object[] {t.getName(), t.getId()};
            batch.add(values);
        }
        //When
        final int[] updateCounts = jdbcTemplate.batchUpdate("update teste set name = ? where id = ?", batch);
        //Then
        final List<Teste> testeList = jdbcTemplate.query("select * from teste", new TesteMapper());
        assertEquals(2, updateCounts.length);
        assertThat(testeList).contains(valores.get(0), valores.get(1));
    }

    @Test
    public void testeDeUpdate() throws Exception{
        //Given
        final String sql = "update teste set name = 'hola!' where id is not null";
        //When
        final int totalDeLinhasAfetadas = jdbcTemplate.update(sql);//retorna o número de linahs afetadas
        //Then
        assertEquals(2, totalDeLinhasAfetadas);
    }

    @Test
    public void testeDeDelete() throws Exception{
        //Given
        final String sql = "delete from teste where id > 0";
        //When
        final int totalDeLinhasAfetadas = jdbcTemplate.update(sql);//retorna o número de linahs afetadas
        //Then
        assertEquals(2, totalDeLinhasAfetadas);
    }

    //-------------------

    @Before
    public void setUp() throws Exception{
	jdbcTemplate.execute("drop table teste");
	jdbcTemplate.execute("create table teste(id bigint auto_increment, name varchar(255))");
	jdbcTemplate.execute("insert into teste(name) values('hello')");
	jdbcTemplate.execute("insert into teste(name) values('world')");

        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("teste")
                        .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

}
