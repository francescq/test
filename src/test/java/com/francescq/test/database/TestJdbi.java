package com.francescq.test.database;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Assert;
import org.junit.Test;
import org.skife.jdbi.v2.BeanMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.francescq.test.database.dao.TokenDao;
import com.francescq.test.pojo.Token;

public class TestJdbi {

    @Test
    public void testSqlStyle() {
	// using in-memory H2 database
	DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test", "username", "password");
	DBI dbi = new DBI(ds);
	Handle h = dbi.open();
	h.execute("create table Token (id int primary key, type varchar(100), content varchar(100), date_expiry timestamp)");

	h.execute("insert into Token (id, type, content, date_expiry) values (?, ?, ?, ?)", 1, "Brian", "{randomContent1}", new Timestamp(new Date().getTime()));

	Query<Map<String, Object>> result = h.createQuery("select id,type,content,date_expiry from Token where id = :id").bind("id", 1);
	ResultSetMapper<Token> t = new BeanMapper<Token>(Token.class);
	Token token = result.map(t).list().iterator().next();

	Assert.assertEquals("Brian", token.getType());

	// Assert.assertEquals(name, as"Brian");

	h.close();
    }

    @Test
    public void testObjectStyle() {
	// using in-memory H2 database via a pooled DataSource
	JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:mem:test2", "username", "password");
	DBI dbi = new DBI(ds);

	TokenDao dao = dbi.open(TokenDao.class);

	dao.createSomethingTable();

	dao.insert(2, "Compare", "{thisIsARandomTokenContent}", new Timestamp(new Date().getTime()));

	Token token = dao.getById(2);

	Assert.assertEquals("Compare", token.getType());

	dao.close();
	ds.dispose();
    }
}