package com.francescq.test.database;

import java.util.Map;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Assert;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

public class TJdbi {
    // using in-memory H2 database
    public void start() {
	DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test", "username", "password");
	DBI dbi = new DBI(ds);
	Handle h = dbi.open();
	h.execute("create table something (id int primary key, name varchar(100))");

	h.execute("insert into something (id, name) values (?, ?)", 1, "Brian");

	Query<Map<String, Object>> result = h.createQuery("select name from something where id = :id").bind("id", 1);

	String name = null;
	result.bind("name", name);

	Assert.assertEquals(name, equals("Brian"));

	h.close();
    }

    public static void main(String[] args) {
	TJdbi t = new TJdbi();
	t.start();
    }
}