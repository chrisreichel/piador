package spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml"})
public class Sample {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testeSelect() throws Exception{
        jdbcTemplate.queryForObject(sql,)
    }

    //-------------------

    @Before
    public void setUp() throws Exception{
	jdbcTemplate.execute("drop table teste");
	jdbcTemplate.execute("create table teste(id bigint auto_increment, name varchar(255))");
	jdbcTemplate.execute("insert into teste(name) values('hello')");
	jdbcTemplate.execute("insert into teste(name) values('world')");
    }

}
